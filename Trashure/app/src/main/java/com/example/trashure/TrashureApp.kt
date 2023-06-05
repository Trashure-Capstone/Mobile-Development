package com.example.trashure

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.ui.navigation.NavigationItem
import com.example.trashure.ui.navigation.Screen
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trashure.ui.screen.home.HomeScreenContent
import com.example.trashure.ui.screen.inbox.InboxScreenContent
import com.example.trashure.ui.screen.login.LoginScreen
import com.example.trashure.ui.screen.order.OrderScreen
import com.example.trashure.ui.screen.profile.ProfileScreen
import com.example.trashure.ui.screen.register.RegisterScreen
import com.example.trashure.ui.screen.splash.SplashScreen1
import com.example.trashure.ui.screen.splash.SplashScreen2
import com.example.trashure.ui.theme.PrimaryBackgroundColor
import com.example.trashure.ui.theme.SecondaryColor
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun TrashureApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val screenWithoutBottomBar:List<String?> =
        listOf(
            Screen.Splash1.route,
            Screen.Splash2.route,
            Screen.Login.route,
            Screen.Register.route,
        )

    Scaffold(
        bottomBar = {
            if(!(screenWithoutBottomBar).contains(currentRoute)){
                BottomBar(navController)
            }
        },
        floatingActionButton = {
            if(!(screenWithoutBottomBar).contains(currentRoute)){
                MyUI()
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash1.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Splash1.route){
                SplashScreen1(
                    navigateToSplashScreen2 = {
                        navController.navigate(Screen.Splash2.route)
                    },
                    navigateToHome = {
                        navController.navigate(Screen.Home.route)
                    }
                )
            }
            
            composable(Screen.Splash2.route){
                SplashScreen2(
                    navigateToLogin = {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }
            composable(Screen.Login.route){
                LoginScreen(
                    navigateToRegister = {
                        navController.navigate(Screen.Register.route)
                    },
                    navigateToHome = {
                        navController.navigate(Screen.Home.route)
                    }
                )
            }
            composable(Screen.Register.route){
                RegisterScreen(
                    navigateToLogin = {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }
            composable(Screen.Home.route){
                HomeScreenContent()
            }
            composable(Screen.Inbox.route){
                InboxScreenContent()
            }
            composable(Screen.Order.route){
                OrderScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
        }
    }

}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier : Modifier = Modifier
) {
    BottomNavigation(
        backgroundColor = Color.Black,
        elevation = 10.dp,
        modifier = modifier
            .fillMaxWidth()
    
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                shadowElevation = 20f
            }
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Pesanan",
                icon = Icons.Default.Article,
                screen = Screen.Order
            ),
            NavigationItem(
                title = "",
                icon = Icons.Default.Person,
                screen = Screen.ScanPage
            ),
            NavigationItem(
                title = "Pesan",
                icon = Icons.Default.Email,
                screen = Screen.Inbox
            ),
            NavigationItem(
                title = "Profile",
                icon = Icons.Default.Person,
                screen = Screen.Profile
            ),
        )
        BottomNavigation(
            backgroundColor = PrimaryBackgroundColor
        ) {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                                imageVector = item.icon,
                                contentDescription = item.title + "_page"
                            )
                    },
                    label = {
                        Text(item.title)
                    },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun MyUI() {
    val contextForToast = LocalContext.current.applicationContext

    Box(modifier = Modifier.fillMaxSize()){
        FloatingActionButton(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(all = 0.dp)
                .offset(x = 18.dp, y = 60.dp)
                .size(70.dp),
            onClick = {
                Toast.makeText(contextForToast, "Click", Toast.LENGTH_SHORT)
                    .show()
            },
            backgroundColor = Color(0xFF7BBB71)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "Add",
                    tint = Color.White
                )
                Text(
                    text = "Scan",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomPreview() {
    TrashureTheme{
        TrashureApp()
    }
}
