package co.edu.unab.EliasCruz.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.unab.EliasCruz.myapplication.ui.elements.AddPlaceScreen
import co.edu.unab.EliasCruz.myapplication.ui.elements.HomeScreen
import co.edu.unab.EliasCruz.myapplication.ui.elements.LoginScreen
import co.edu.unab.EliasCruz.myapplication.ui.elements.RegisterScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationApp() {
    val myNavController = rememberNavController()
    val auth = FirebaseAuth.getInstance()

    // Si ya hay sesión, entra directo a Home. Si no, va al Login.
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
                onNavigateToAddPlace = {
                    myNavController.navigate(route = "add_place")
                }
                // Nota: Si mantienes la función de cerrar sesión en Home,
                // asegúrate de pasarle también el parámetro onLogout = { ... } aquí.
            )
        }

        composable(route = "add_place") {
            AddPlaceScreen(
                // AQUÍ CONECTAMOS EL BOTÓN DE ATRÁS
                onBackClick = {
                    myNavController.popBackStack()
                },
                onPublishClick = {
                    // Por ahora puede solo regresar, o luego puedes añadir la lógica de guardar en Firebase
                    myNavController.popBackStack()
                }
            )
        }
    }
}