package com.example.uaspam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uaspam.ui.add.AddScreen
import com.example.uaspam.ui.add.DestinasiEntry
import com.example.uaspam.ui.detail.DetailDestination
import com.example.uaspam.ui.detail.DetailScreen
import com.example.uaspam.ui.edit.EditDestination
import com.example.uaspam.ui.edit.EditScreen
import com.example.uaspam.ui.homescreen.DestinasiHome
import com.example.uaspam.ui.homescreen.FirstScreen
import com.example.uaspam.ui.homescreen.HomeScreen
import com.example.uaspam.ui.loginscreen.SignInScreen
import com.example.uaspam.ui.signupscreen.SignUpScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    val auth = FirebaseAuth.getInstance()
    NavHost(

        navController = navController,
        startDestination = Screens.FirstScreen.route,
        modifier = Modifier
    ) {
        composable(route = Screens.FirstScreen.route){
            FirstScreen (navController)
        }

        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController)

        }
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(navController)

        }
        composable(
            DestinasiHome.route
        ) {
            HomeScreen(navigateToItemEntry = {
                navController.navigate(DestinasiEntry.route)
            },
                onDetailClick = { appId ->
                    navController.navigate("${DetailDestination.route}/$appId")
                    println("itemId: $appId")
                })
        }
        composable(DestinasiEntry.route) {
            AddScreen(navigateBack = {
                navController.popBackStack()
            })

        }

        composable(
            route = DetailDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailDestination.aplikasiId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getString(DetailDestination.aplikasiId)
            appId?.let {
                DetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = {
                        navController.navigate("${EditDestination.route}/$appId")
                        println("appId: $appId")
                    }
                )
            }
        }

        composable(
            route = EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.appid) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val kontakId = backStackEntry.arguments?.getString(EditDestination.appid)
            kontakId?.let {
                EditScreen(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}