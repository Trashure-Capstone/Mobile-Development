package com.example.trashure.ui.screen.register

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trashure.R
import com.example.trashure.di.Injection
import com.example.trashure.ui.common.UiState
import com.example.trashure.ui.components.AnnotatedClickableText
import com.example.trashure.ui.components.MyTextFieldComponent
import com.example.trashure.ui.components.PasswordTextFieldComponent
import com.example.trashure.ui.screen.login.LoginUIEvent
import com.example.trashure.ui.theme.Lato
import com.example.trashure.ui.theme.PrimaryBackgroundColor
import com.example.trashure.ui.theme.PrimaryColor
import com.example.trashure.ui.theme.Shapes_Larger
import com.example.trashure.utils.ViewModelFactory

@Composable
fun RegisterScreen (
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current)
        )
),
) {
    Log.d("zzz","RegisterScreen")
    var isLoading by remember{ mutableStateOf(false) }
    RegisterScreenContent(
        navigateToLogin = navigateToLogin,
        modifier = modifier.fillMaxSize(),
        viewModel = viewModel,
        isLoading = isLoading
    )
    viewModel.registerState.collectAsState(initial = UiState.Empty).value.let{ uiState ->
        when(uiState){
            is UiState.Loading -> {
                isLoading = true
            }
            is UiState.Success -> {
                isLoading = false
                LaunchedEffect(key1 = true) {
                    navigateToLogin()
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
}


@Composable
fun RegisterScreenContent(
    navigateToLogin: () -> Unit,
    modifier: Modifier,
    viewModel: RegisterViewModel,
    isLoading: Boolean
){
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(PrimaryBackgroundColor, PrimaryColor)
                )
            ),
    ) {
        
        Column{
            Image(
                painter = painterResource(id = R.drawable.image_register),
                contentDescription = stringResource(id = R.string.image),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.register),
                fontFamily = Lato,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.register_desc),
                fontFamily = Lato,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.name),
                iconVector = Icons.Default.Person,
                errorStatus = viewModel.registerUIState.value.nameError,
                onTextChanged = {
                    viewModel.onEvent(RegisterUIEvent.NameChanged(it))
                },
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                iconVector = Icons.Default.Email,
                errorStatus = viewModel.registerUIState.value.emailError,
                onTextChanged = {
                    viewModel.onEvent(RegisterUIEvent.EmailChanged(it))
                },
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                iconVector = Icons.Default.Lock,
                onTextChanged = {
                    viewModel.onEvent(RegisterUIEvent.PasswordChanged(it))
                },
                errorStatus = viewModel.registerUIState.value.passwordError,
                isLastField = false,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.confirm_password),
                iconVector = Icons.Default.Lock,
                onTextChanged = {
                    viewModel.onEvent(RegisterUIEvent.ConfirmPasswordChanged(it))
                },
                errorStatus = viewModel.registerUIState.value.confirmPasswordError,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                shape = Shapes_Larger.medium,
                elevation = ButtonDefaults.buttonElevation(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,
                ),
                enabled = viewModel.isAllValidationsPassed.value,
                onClick = {
                    viewModel.onEvent(RegisterUIEvent.RegisterButtonClicked)
                },
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.register),
                    fontFamily = Lato,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(painterResource(id = R.drawable.image_faded_line),"")
                Text(
                    stringResource(id = R.string.or_continue_with),
                    fontFamily = Lato,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Image(
                    painterResource(id = R.drawable.image_faded_line),
                    contentDescription = "",
                    modifier = Modifier.scale(scaleX = -1f, scaleY = 1f)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBackgroundColor,
                    ),
                    shape = Shapes_Larger.large,
                    elevation = ButtonDefaults.buttonElevation(5.dp),
                    onClick = {},
                    modifier = Modifier
                        .size(72.dp)
                
                ){
                    Image(
                        painter = painterResource(id = R.drawable.google_logo),
                        contentDescription = stringResource(id = R.string.google_sign_in),
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
                Spacer(Modifier.width(20.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBackgroundColor,
                    ),
                    shape = Shapes_Larger.large,
                    elevation = ButtonDefaults.buttonElevation(5.dp),
                    onClick = {},
                    modifier = Modifier
                        .size(72.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.facebook_logo),
                        contentDescription = stringResource(id = R.string.facebook_sign_in),
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
        
        AnnotatedClickableText(
            initialText = stringResource(id = R.string.have_an_account) + " ",
            clickableText = stringResource(R.string.sign_in),
            onClick = {
                navigateToLogin()
            },
            modifier = Modifier
                .padding(bottom = 40.dp)
                .align(Alignment.CenterHorizontally)
        )
        
    }
    
    if(isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(
                color = PrimaryColor
            )
        }
    }
}