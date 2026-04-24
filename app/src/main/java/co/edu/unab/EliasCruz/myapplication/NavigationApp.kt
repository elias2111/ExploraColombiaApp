package co.edu.unab.EliasCruz.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationApp() {
    val myNavController = rememberNavController()
    val auth = FirebaseAuth.getInstance()


    val myStartDestination = if (auth.currentUser != null) "home" else "login"

    NavHost(
        navController = myNavController,
        startDestination = myStartDestination
    ) {
        composable(route = "login") {
            LoginScreen(
                onLoginSuccess = {

                    myNavController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    myNavController.navigate("register")
                }
            )
        }

        composable(route = "register") {
            RegisterScreen(
                onRegisterSuccess = { myNavController.popBackStack() },
                onNavigateToLogin = { myNavController.popBackStack() },
                onBackClick = { myNavController.popBackStack() }
            )
        }

        composable(route = "home") {
            HomeScreen(
                onLogout = {
                    myNavController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}