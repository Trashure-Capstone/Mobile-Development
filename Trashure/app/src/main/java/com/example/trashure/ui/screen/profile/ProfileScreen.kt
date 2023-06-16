package com.example.trashure.ui.screen.profile

import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trashure.R
import com.example.trashure.data.remote.response.User
import com.example.trashure.di.Injection
import com.example.trashure.ui.common.UiState
import com.example.trashure.ui.components.profilepage.CardMenuProfileViews
import com.example.trashure.ui.components.profilepage.CardProfileViews
import com.example.trashure.ui.screen.login.LoginViewModel
import com.example.trashure.ui.theme.PrimaryBackgroundColor
import com.example.trashure.ui.theme.PrimaryColor
import com.example.trashure.ui.theme.Shapes_Larger
import com.example.trashure.ui.theme.TrashureTheme
import com.example.trashure.utils.ViewModelFactory

@Composable
fun ProfileScreen(
    navigateLogin: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToChangePassword : () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(context = LocalContext.current)
        )
    )
) {
    var profileData: User? by rememberSaveable{
        mutableStateOf(null)
    }
    var isLoading by remember{
        mutableStateOf(false)
    }
    viewModel.state.collectAsState(initial = UiState.Empty).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                isLoading = true
            }
            is UiState.Success -> {
                isLoading = false
                profileData = uiState.data
            }
            is UiState.Error -> {
                isLoading = false
                val context = LocalContext.current
                val errorMessage = uiState.errorMessage
                LaunchedEffect(key1 = true){
                    Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                viewModel.getUserInfo()
            }
        
        }
    }
    
    if(isLoading){
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(
                color = PrimaryColor
            )
        }
    } else{
        Box(
            modifier = modifier
                .height(160.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            PrimaryBackgroundColor, Color(0xFFCCEFD9)
                        )
                    ),
                    shape = RoundedCornerShape(
                        bottomEnd = 20.dp,
                        bottomStart = 20.dp
                    )
                )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "Profile",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(26.dp))
        
            CardProfileViews(
                isLoading = isLoading,
                image = R.drawable.avatarss,
                username = profileData?.name ?: "Username",
                email = profileData?.email ?: "Email",
                role = profileData?.role ?: "Role"
            )
            Spacer(modifier = Modifier.height(26.dp))
            CardMenuProfileViews(navigateToEditProfile, navigateToChangePassword)
            Spacer(modifier = Modifier.height(26.dp))
            ButtonLogout(
                logout = {viewModel.logout()},
                navigateLogin = navigateLogin
            )
        }
    }
    
    
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    TrashureTheme {
        ProfileScreen(navigateLogin = {}, navigateToEditProfile = {}, navigateToChangePassword = {})
    }
}

@Composable
fun ButtonLogout(
    logout: () -> Unit,
    navigateLogin: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = {
            logout()
            navigateLogin()
        },
        shape = Shapes_Larger.small,
        colors = ButtonDefaults.buttonColors(Color(0xFFE53E3E)),
        modifier = modifier
            .width(140.dp)
    ) {
        Text(text = "Logout")
    }
}