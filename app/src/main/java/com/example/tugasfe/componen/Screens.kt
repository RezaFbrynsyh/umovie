package com.example.tugasfe.componen

sealed class Screens(val screen : String) {
    object Home : Screens("home")
    object Search : Screens("search")
    object Favorite : Screens("fav")
    object Profile : Screens("profile")
}