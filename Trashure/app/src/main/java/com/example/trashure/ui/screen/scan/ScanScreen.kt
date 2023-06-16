package com.example.trashure.ui.screen.scan

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trashure.R
import com.example.trashure.data.remote.response.ScanResult
import com.example.trashure.di.Injection
import com.example.trashure.ui.common.UiState
import com.example.trashure.ui.components.MyTopBar
import com.example.trashure.ui.components.scanpage.CameraDialog
import com.example.trashure.ui.components.scanpage.CardInformationViews
import com.example.trashure.ui.theme.PrimaryColor
import com.example.trashure.ui.theme.Shapes_Larger
import com.example.trashure.ui.theme.TrashureTheme
import com.example.trashure.ui.theme.Typography
import com.example.trashure.utils.ComposeFileProvider
import com.example.trashure.utils.ViewModelFactory
import com.example.trashure.utils.uriToFile
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanScreen(
    navigateBack: () -> Unit,
    navigateToSellTrash: () -> Unit,
    viewModel: ScanViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(context = LocalContext.current)
        )
    )
) {
    val context = LocalContext.current
    
    var isCameraPermissionGranted: Boolean by remember {
        mutableStateOf(false)
    }
    var isGalleryPermissionGranted: Boolean by remember {
        mutableStateOf(false)
    }

    val permissionState =
        rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.CAMERA,
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    Manifest.permission.READ_MEDIA_IMAGES
                }else {
                    Manifest.permission.READ_EXTERNAL_STORAGE
                }
            )
        )

        val lifeCycleOwner = LocalLifecycleOwner.current
        DisposableEffect(
            key1 = lifeCycleOwner,
            effect = {
                val observer = LifecycleEventObserver{ _, event ->
                    if(event == Lifecycle.Event.ON_RESUME){
                        //request permission
                        permissionState.launchMultiplePermissionRequest()
                        Log.d("ScanScreen", "Permission Request")
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
                    permission.status.shouldShowRationale ->{
                        isCameraPermissionGranted = false
                    }
                    !permission.status.isGranted && !permission.status.shouldShowRationale ->{
                        Log.d("ScanScreen", "No Permission Camera")
//                        navigateBack()
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                Manifest.permission.READ_MEDIA_IMAGES
            }else {
                Manifest.permission.READ_EXTERNAL_STORAGE
            } ->{
                when {
                    permission.status.isGranted ->{
                        isGalleryPermissionGranted = true
                    }
                    permission.status.shouldShowRationale ->{
                        isGalleryPermissionGranted = false
                    }
                    !permission.status.isGranted && !permission.status.shouldShowRationale ->{
                        Log.d("ScanScreen", "NoPermission Gallery")
//                        navigateBack()
                    }
                }
            }

        }
    }
    
    if(isCameraPermissionGranted && isGalleryPermissionGranted){
        var hasImage by remember {
            mutableStateOf(false)
        }
        var imageUri by rememberSaveable {
            mutableStateOf<Uri?>(null)
        }
    
        val imagePicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                hasImage = uri != null
                imageUri = uri
                viewModel.scan(imageUri?.let { uriToFile(selectedImg = it,context = context) },context)
            }
        )
    
        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { success ->
                hasImage = success
                viewModel.scan(imageUri?.let { uriToFile(selectedImg = it,context = context) }, context)
            }
        )
    
        var isLoading by remember{ mutableStateOf(false) }
        
        if(isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator(
                    color = PrimaryColor
                )
            }
        }
        
        viewModel.scanState.collectAsState(initial = UiState.Empty).value.let{ uiState ->
            
            when(uiState){
                
                is UiState.Loading -> {
                    Log.d("ScanScreen", "UiState.Loading")
                    isLoading = true
                }
                is UiState.Success -> {
                    Log.d("ScanScreen", "UiState.Success")
                    isLoading = false
                    ScanScreenContent(
                        scanState = uiState.data,
                        navigateBack = navigateBack,
                        openDialog = {
                            viewModel.rescan()
                        },
                        navigateToSellTrash = navigateToSellTrash,
                        imageUri = imageUri
                    )
                    //tampilin screen
                }
                is UiState.Error -> {
                    Log.d("ScanScreen", "UiState.Error")
                    isLoading = false
                    navigateBack()
                    Log.d("collectStateTestError", uiState.toString())
                }
                else -> {
                    Log.d("ScanScreen", "UiState.Empty")
                    CameraDialog(
                        onCancelClicked = navigateBack ,
                        onCameraSelected = {
                            val uri = ComposeFileProvider.getImageUri(context)
                            imageUri = uri
                            cameraLauncher.launch(uri) },
                        onGallerySelected = {
                            imagePicker.launch("image/*")
                        }
                    )
                }
                

            }
        }
        
    }else{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text =stringResource(id = R.string.no_permission),
                style = Typography.body1
            )
        }
    }
    
    
    
}
    
@Composable
fun ScanScreenContent (
    scanState: ScanResult,
    navigateBack: () -> Unit,
    openDialog : ()->Unit,
    navigateToSellTrash : ()->Unit,
    imageUri: Uri?,
    modifier: Modifier = Modifier
){
    Scaffold (
        topBar = {
            MyTopBar(
                navigateBack = navigateBack,
                title = stringResource(R.string.scan_page)
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
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                scanState.jenis_sampah,
                textAlign = TextAlign.Center,
                style = Typography.subtitle1
            )
            if(imageUri == null){
                ImageScanViews(R.drawable.baseline_image_24)
            }else{
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
            
            LogoType(scanState.logo)
            Spacer(modifier = Modifier.height(4.dp))
            CardInformationViews(title = "Tipe Plastik", description = scanState.tipe_sampah)
            CardInformationViews(title = "Nama Lain", description = scanState.nama_lain)
            CardInformationViews(title = "Manfaat", description = scanState.manfaat)
            CardInformationViews(title = "Kekurangan", description = scanState.kekurangan)
            ButtonScanSell(
                navigateToSellTrash = navigateToSellTrash,
                openDialog = openDialog,
                modifier = modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}

@Preview
@Composable
fun ScanScreenPreview(){
    TrashureTheme {
        ScanScreenContent(ScanResult("","","","","",""),{},{},{},null)
    }
}

@Preview
@Composable
fun MyTopBarPreview(){
    TrashureTheme {
        MyTopBar(
            {},"Identifikasi Jenis Sampah"
        )
    }
}

@Composable
fun ImageScanViews(
    image: Int,
    modifier: Modifier = Modifier,
) {
    Card (
        modifier = modifier
            .width(220.dp)
            .height(220.dp),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(width = 2.dp, color = Color.Black)
    ){
        Column {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}

@Preview
@Composable
fun ImageScanPreview(){
    TrashureTheme {
        ImageScanViews(R.drawable.avatarss )
    }
}

@Composable
fun LogoType(
    image: String,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = stringResource(R.string.logo_title),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        
        AsyncImage(
            model = image ,
            contentDescription = stringResource(id = R.string.trash_type_logo),
            modifier = modifier
                .size(50.dp)
        )
    }
}

@Composable
fun ButtonChoose(
    onClick: ()->Unit,
    buttonName: String,
    modifier: Modifier = Modifier
){
    Button(
        onClick = {
            onClick()
        },
        shape = Shapes_Larger.small,
        colors = ButtonDefaults.buttonColors(PrimaryColor),
        modifier = modifier
            .width(140.dp)
    ) {
        Text(text = buttonName)
    }
}

@Composable
fun ButtonScanSell(
    openDialog : ()->Unit,
    navigateToSellTrash : ()->Unit,
    modifier: Modifier = Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ButtonChoose(buttonName = "Scan Sampah", onClick = openDialog)
        ButtonChoose(buttonName = "Jual SAMPAH", onClick = navigateToSellTrash)
    }
}

@Preview
@Composable
fun ButtonChoosePreview(){
    TrashureTheme {
        ButtonChoose({},buttonName = stringResource(R.string.scan_trash))
    }
}

