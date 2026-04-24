package co.edu.unab.EliasCruz.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationApp() {
    val myNavController = rememberNavController()

    val myStartDestination = "login"

    NavHost(
        navController = myNavController,
        startDestination = myStartDestination
    ) {
        composable(route = "login") {
            LoginScreen(
                onLoginSuccess = {

                },
                onNavigateToRegister = {
                    myNavController.navigate("register")
                }
            )
        }

        composable(route = "register") {
            RegisterScreen(
                onRegisterSuccess = {
                    myNavController.popBackStack()
                },
                onNavigateToLogin = {
                    myNavController.popBackStack()
                },
                onBackClick = {
                    myNavController.popBackStack()
                }
            )
        }
    }
}