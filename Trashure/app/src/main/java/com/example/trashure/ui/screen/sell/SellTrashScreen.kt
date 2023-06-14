package com.example.trashure.ui.screen.sell

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.trashure.R
import com.example.trashure.di.Injection
import com.example.trashure.model.TrashTypeList
import com.example.trashure.ui.components.sellpage.CategoryItem
import com.example.trashure.ui.components.sellpage.CategoryPhoto
import com.example.trashure.ui.components.sellpage.CategorySection
import com.example.trashure.ui.components.sellpage.CategoryWeight
import com.example.trashure.ui.components.sellpage.ModalBottomSheet
import com.example.trashure.ui.theme.*
import com.example.trashure.utils.ComposeFileProvider
import com.example.trashure.utils.ViewModelFactory
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun SellTrashScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SellTrashViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(context = LocalContext.current)
        )
    )
) {
    val state = viewModel.sellTrashUIState
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    
    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()
    
    var isCameraPermissionGranted: Boolean by remember {
        mutableStateOf(false)
    }
    
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val lifeCycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifeCycleOwner,
        effect = {
            val observer = LifecycleEventObserver{ _, event ->
                if(event == Lifecycle.Event.ON_RESUME){
                    permissionState.launchPermissionRequest()
                }
            }
            lifeCycleOwner.lifecycle.addObserver(observer)
            
            onDispose {
                lifeCycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )
    
    isCameraPermissionGranted = permissionState.status.isGranted
    
    if(isCameraPermissionGranted){
        val context = LocalContext.current
        var hasImage by remember {
            mutableStateOf(false)
        }
        var imageUri by rememberSaveable {
            mutableStateOf<Uri?>(null)
        }
        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { success ->
                hasImage = success
            }
        )
        ModalBottomSheetLayout(
            sheetContent = {
                ModalBottomSheet(onClick = {id ->
                    TrashTypeList.find{it.id == id}?.let{
                        viewModel.updateTrashType(it.name)
                    }
                
                    scope.launch {
                        modalBottomSheetState.hide()
                    }
                })
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
                            } else {"Pilih jenis sampah"})?.let {
                                CategoryItem(
                                    icon = R.drawable.trashsell,
                                    title = it,
                                    modifier = modifier
                                        .clickable {
                                            scope.launch {
                                                modalBottomSheetState.show()
                                            }
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
                            )
                        }
                    )
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
                                state.value.time?.let { SimpleDateFormat("hh:mm").format(it) }
                            } else {"Tentukan waktu"})?.let {
                                CategoryItem(
                                    icon = R.drawable.clockfill,
                                    title = it,
                                    modifier = Modifier.clickable {
                                        timeDialogState.show()
                                    }
                                )
                            }
                        }
                    )
                
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {},
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
                            viewModel.updateTime(it)
                        }
                    }
                
                }
            }
        }
    }
    else{
        navigateBack()
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
        SellTrashScreen({})
    }
}