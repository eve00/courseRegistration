package com.example.courseregistration

import androidx.navigation.NavHostController


object Destinations {
    const val HOME_ROUTE = "home"
}

class SampleNavigationActions(navController: NavHostController){
    val navigateToHome: () -> Unit = {
        navController.navigate(Destinations.HOME_ROUTE){
            popUpTo(navController.graph.id){
                //画面の状態をバックスタックに保存しておく
                saveState = true
            }
            //同じ画面を積まない
            launchSingleTop = true
            restoreState = true
        }
    }
}