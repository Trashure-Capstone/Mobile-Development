package com.example.trashure.ui.screen.sell

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.trashure.R
import com.example.trashure.data.remote.service.reduceFileImage
import com.example.trashure.di.Injection
import com.example.trashure.model.TrashTypeList
import com.example.trashure.ui.common.UiState
import com.example.trashure.ui.components.sellpage.*
import com.example.trashure.ui.theme.*
import com.example.trashure.utils.ComposeFileProvider
import com.example.trashure.utils.ViewModelFactory
import com.example.trashure.utils.uriToFile
import com.google.accompanist.permissions.*
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.Locale

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun SellTrashScreen(
    navigateBack: () -> Unit,
    navigateToOrder: ()->Unit,
    modifier: Modifier = Modifier,
    viewModel: SellTrashViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(context = LocalContext.current)
        )
    )
) {
    var isLoading by remember{ mutableStateOf(false)}
    viewModel.state.collectAsState(initial = UiState.Empty).value.let{ uiState ->
        when(uiState){
            is UiState.Loading -> {
                isLoading = true
            }
            is UiState.Success -> {
                isLoading = false
                val context = LocalContext.current
                val successMessage = stringResource(id = R.string.sell_trash_success)
                LaunchedEffect(key1 = true){
                    Toast.makeText(context,successMessage, Toast.LENGTH_SHORT).show()
                    navigateToOrder()
                }
            }
            is UiState.Error -> {
                isLoading = false
                Log.d("collectStateTestError", uiState.toString())
            }
            else -> {
            
            }
            
        }
    }
    val state = viewModel.sellTrashUIState
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var currentBottomSheet: BottomSheetType by rememberSaveable{
        mutableStateOf(BottomSheetType.TRASH_TYPE)
    }
    val closeSheet = {
        scope.launch { modalBottomSheetState.hide() }
    }
    
    val openSheet = {
        scope.launch { modalBottomSheetState.show() }
    }
    
    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()
    
    val location = rememberMarkerState()
    var isCameraPermissionGranted: Boolean by remember {
        mutableStateOf(false)
    }
    
    var isCoarseLocationPermissionGranted: Boolean by remember{
        mutableStateOf(false)
    }
    
    var isFineLocationPermissionGranted: Boolean by remember{
        mutableStateOf(false)
    }
    
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    
    val lifeCycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifeCycleOwner,
        effect = {
            val observer = LifecycleEventObserver{ _, event ->
                if(event == Lifecycle.Event.ON_RESUME){
                    permissionState.launchMultiplePermissionRequest()
                }
            }
            lifeCycleOwner.lifecycle.addObserver(observer)
            
            onDispose {
                lifeCycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )
    
    permissionState.permissions.forEach{permission->
        when(permission.permission){
            Manifest.permission.CAMERA -> {
                when{
                    permission.status.isGranted ->{
                        isCameraPermissionGranted = true
                    }
                }
            }
            Manifest.permission.ACCESS_COARSE_LOCATION->{
                when{
                    permission.status.isGranted ->{
                        isCoarseLocationPermissionGranted = true
                    }
                }
            }
            Manifest.permission.ACCESS_FINE_LOCATION->{
                when{
                    permission.status.isGranted ->{
                        isFineLocationPermissionGranted = true
                    }
                }
            }
        }
    }
    
    if(isCameraPermissionGranted && isCoarseLocationPermissionGranted && isFineLocationPermissionGranted){
        val context = LocalContext.current
        
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(-6.914744, 107.609810), 15f)
        }
        
        var hasImage by remember {
            mutableStateOf(false)
        }
        var imageUri by rememberSaveable {
            mutableStateOf<Uri?>(null)
        }
        var showMaps by rememberSaveable{
            mutableStateOf(false)
        }
        
        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { success ->
                hasImage = success
            }
        )
        ModalBottomSheetLayout(
            sheetContent = {
                if (currentBottomSheet==BottomSheetType.TRASH_TYPE){
                    ModalBottomSheet(onClick = {id ->
                        TrashTypeList.find{it.id == id}?.let{
                            viewModel.updateTrashType(it.id)
                        }
                        closeSheet()
                    })
                }else if(currentBottomSheet == BottomSheetType.CONFIRM_SELL){
                    if(state.value.trashType == null ){
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                text = stringResource(id = R.string.choose_trashType),
                                style = Typography.subtitle1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    if(state.value.weight == 0){
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                text = stringResource(id = R.string.invalid_weight),
                                style = Typography.subtitle1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    if(state.value.date == null){
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                text = stringResource(id = R.string.invalid_date),
                                style = Typography.subtitle1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    if(state.value.time == null){
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                text = stringResource(id = R.string.invalid_time),
                                style = Typography.subtitle1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    if(location.position == LatLng(0.0,0.0)){
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                text = stringResource(id = R.string.invalid_location),
                                style = Typography.subtitle1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    if(imageUri==null){
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                text = stringResource(id = R.string.invalid_image),
                                style = Typography.subtitle1,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    if (
                        state.value.trashType != null &&
                        state.value.weight > 0 &&
                        state.value.date != null &&
                        state.value.time != null &&
                        location.position != LatLng(0.0,0.0) &&
                        imageUri != null
                    ){
                        TrashTypeList.find{
                            it.id == state.value.trashType
                        }?.let{
                            state.value.weight*it.pricePerKg}?.let { it ->
                            ModalDetailSelling(
                                price = it,
                                sellTrash = {
                                    closeSheet()
                                    val file = imageUri?.let { uri->uriToFile(uri,context) }?.let { reduceFileImage(it) }
                                    val requestImageFile = file!!.asRequestBody("image/jpeg".toMediaType())
                                    viewModel.sellTrash(
                                        latitude = location.position.latitude.toString().toRequestBody("text/plain".toMediaType()),
                                        longitude = location.position.longitude.toString().toRequestBody("text/plain".toMediaType()),
                                        date = state.value.date.toString().toRequestBody("text/plain".toMediaType()),
                                        time = state.value.time.toString().toRequestBody("text/plain".toMediaType()),
                                        weight = state.value.weight.toString().toRequestBody("text/plain".toMediaType()),
                                        trashType = state.value.trashType!!.toRequestBody("text/plain".toMediaType()),
                                        image = MultipartBody.Part.createFormData(
                                            "image",
                                            file.name,
                                            requestImageFile
                                        )
                                    )
                                    
                                }
                            )
                        }
                    }
                }
                
            },
            sheetState = modalBottomSheetState,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetBackgroundColor = Color.White
        ){
            Scaffold (
                topBar = {
                    SellTrashTopBar(
                        title = stringResource(R.string.sell_page),
                        onClick = navigateBack
                    )
                },
                modifier = modifier
            ){ innerPadding ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    CategorySection(
                        title = "Jenis Sampah",
                        content = {
                            (if (state.value.trashType != null){
                                state.value.trashType
                            } else {stringResource(id = R.string.pick_trashType)})?.let {
                                CategoryItem(
                                    icon = R.drawable.trashsell,
                                    title = TrashTypeList.find { trash-> trash.id == it }?.let{
                                        it.name
                                    } ?: stringResource(id = R.string.pick_trashType),
                                    modifier = modifier
                                        .clickable {
                                            currentBottomSheet = BottomSheetType.TRASH_TYPE
                                            openSheet()
                                        }
                                )
                            }
                        }
                    )
                    CategorySection(
                        title = "Berat Sampah",
                        content = {
                            CategoryWeight(
                                count = state.value.weight,
                                onProductCountChanged = {weight ->
                                    viewModel.updateWeight(weight)
                                    Log.d("counterProduct",weight.toString())
                                }
                            )
                        }
                    )
                    CategorySection(
                        title = "Foto Sampah",
                        content = {
                            CategoryPhoto(
                                onClick = {
                                    val uri = ComposeFileProvider.getImageUri(context)
                                    imageUri = uri
                                    cameraLauncher.launch(uri)
                                }
                            )
                        }
                    )
                    if(imageUri!=null && hasImage){
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            AsyncImage(
                                model = imageUri,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(220.dp)
                                    .border(
                                        border = BorderStroke(2.dp, PrimaryColor),
                                        shape = RoundedCornerShape(18.dp)
                                    ),
                                alignment = Alignment.Center
                            )
                        }
                    }
                    CategorySection(
                        title = "Lokasi Pengambilan",
                        content = {
                            CategoryItem(
                                icon = R.drawable.location,
                                title = "Tentukan lokasimu",
                                modifier = Modifier.clickable {
                                    showMaps = true
                                }
                            )
                        }
                    )
                    if(showMaps){
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            GoogleMap(
                                modifier = Modifier
                                    .size(300.dp),
                                cameraPositionState = cameraPositionState,
                                onMapClick = {
                                    location.position = it
                                }
                            ){
                                Marker(
                                    state = location,
                                    title = stringResource(id = R.string.pick_location),
                                    )
                            }
                        }
                    }
                    CategorySection(
                        title = "Jadwal Pengambilan",
                        content = {
                            (if (state.value.date != null){
                                state.value.date?.let { SimpleDateFormat("dd MMM yyyy").format(it) }
                            } else {"Tentukan tanggal"})?.let {
                                CategoryItem(
                                    icon = R.drawable.calendardatefill,
                                    title = it,
                                    modifier = Modifier.clickable {
                                        dateDialogState.show()
                                    }
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            (if (state.value.time != null){
                                Log.d("TimeCheck",state.value.time.toString())
                                state.value.time?.let {
                                    SimpleDateFormat("HH:mm").format(it)
                                    }
                            } else {"Tentukan waktu"})?.let {
                                CategoryItem(
                                    icon = R.drawable.clockfill,
                                    title = it,
                                    modifier = Modifier.clickable {
                                        Log.d("location",location.position.toString())
                                        timeDialogState.show()
                                    }
                                )
                            }
                        }
                    )
                
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            currentBottomSheet = BottomSheetType.CONFIRM_SELL
                            openSheet()
                        },
                        shape = Shapes_Larger.small,
                        colors = ButtonDefaults.buttonColors(PrimaryColor),
                        modifier = modifier
                            .width(140.dp)
                            .padding(bottom = 20.dp)
                    ) {
                        Text(text = "Jual Sampah")
                    }
                
                    MaterialDialog(
                        dialogState = dateDialogState,
                        buttons = {
                            positiveButton(text = stringResource(id = R.string.ok))
                            negativeButton(text = stringResource(id = R.string.cancel))
                        }
                    ){
                        datepicker(
                            initialDate = if (state.value.date == null){
                                LocalDate.now().plusDays(1)
                            }else{
                                state.value.date!!.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()},
                            title = stringResource(id = R.string.pick_date),
                            allowedDateValidator = {
                                it.isAfter(LocalDate.now())
                            }
                        ){
                            viewModel.updateDate(it)
                        }
                    }
                
                    MaterialDialog(
                        dialogState = timeDialogState,
                        buttons = {
                            positiveButton(text = stringResource(id = R.string.ok))
                            negativeButton(text = stringResource(id = R.string.cancel))
                        }
                    ){
                        timepicker(
                            initialTime = if (state.value.time == null){
                                LocalTime.now()
                            }else{
                                state.value.time!!.toInstant().atZone(ZoneId.systemDefault()).toLocalTime()},
                            title = stringResource(id = R.string.pick_time),
                            colors = TimePickerDefaults.colors(
                                inactiveBackgroundColor = PrimaryBackgroundColor,
                                activeBackgroundColor = SecondaryBackgroundColor,
                                activeTextColor = PrimaryTextColor,
                                selectorColor = PrimaryColor,
                                headerTextColor = PrimaryTextColor,
                                selectorTextColor = Color.White
                            ),
                            is24HourClock = true,
                            timeRange = LocalTime.of(7, 0)..LocalTime.of(21, 0)
                        ){
                            Log.d("TimeChecks",it.toString())
                            viewModel.updateTime(it)
                        }
                    }
                
                }
                if(isLoading){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        CircularProgressIndicator(
                            color = PrimaryColor
                        )
                    }
                }
            }
        }
    }
    else{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text =stringResource(id = R.string.no_permission),
                style = Typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 50.dp)
            )
        }
        
    }
}



@Composable
fun SellTrashTopBar(
    title : String,
    onClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onClick()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu",
                    tint = Color(0xFF1A395A)
                )
            }
        },
        title = {
            Text(text = title, color = Color(0xFF1A395A))
        }
    )
}

@Preview
@Composable
fun SellTrashPreview(){
    TrashureTheme {
        SellTrashScreen({},{})
    }
}

enum class BottomSheetType {
    TRASH_TYPE, CONFIRM_SELL
}