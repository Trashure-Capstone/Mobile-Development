package com.example.trashure.ui.screen.splash

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.trashure.R

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trashure.di.Injection
import com.example.trashure.ui.common.UiState
import com.example.trashure.ui.screen.login.LoginViewModel
import com.example.trashure.ui.theme.*
import com.example.trashure.utils.ViewModelFactory
import kotlinx.coroutines.delay

@Composable
fun SplashScreen1(
    navigateToSplashScreen2: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel =
        viewModel(
            factory = ViewModelFactory(
                Injection.provideRepository(context = LocalContext.current)
            )
        ),
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(2000)
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(PrimaryBackgroundColor, PrimaryColor)
                )
            ),
    ) {
        Image(
            painterResource(id = R.drawable.trashure_logo),
            contentDescription = stringResource(id = R.string.trashure_logo),
            modifier = Modifier
                .size(120.dp, 128.dp)
                .alpha(alphaAnim.value)
        )
    }
//    viewModel.logout()
    viewModel.isLogin.collectAsState(initial = UiState.Empty).value.let{ uiState ->
        when(uiState){
            is UiState.Loading -> {
            }
            is UiState.Success -> {
                if(uiState.data){
                    LaunchedEffect(key1 = true){
                        startAnimation = true
                        delay(2000)
                        navigateToHome()
                    }
                    
                } else {
                    LaunchedEffect(key1 = true){
                        startAnimation = true
                        delay(2000)
                        navigateToSplashScreen2()
                    }
                    

                }
            }
            is UiState.Error -> {
                Log.d("collectStateTestError", uiState.toString())
            }
            else -> {
                viewModel.checkIsLogin()
            }
            
        }
    }
}