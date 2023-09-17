package com.example.courseregistration

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.courseregistration.screen.home.HomeScreen


@Composable
fun CourseRegistrationNavGraph(
    modifier: Modifier = Modifier.padding(16.dp),
    navController: NavHostController,
    startDestination: String = Destinations.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Destinations.HOME_ROUTE){
            HomeScreen(modifier = modifier)
        }

    }
}