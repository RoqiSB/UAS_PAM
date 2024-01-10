package com.example.uaspam.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uaspam.ui.signupscreen.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
  NavHost(
      navController = navController,
      startDestination = Screens.SignUpScreen.route
  ){
      composable(route = Screens.SignInScreen.route){

      }
      composable(route = Screens.SignUpScreen.route){
            SignUpScreen()
      }
  }
}