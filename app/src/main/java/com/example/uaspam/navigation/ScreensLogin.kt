package com.example.uaspam.navigation

sealed class Screens(val route: String) {
    object SignInScreen : Screens(route = "SignIn_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")

    object FirstScreen : Screens(route = "First_Screen")
}