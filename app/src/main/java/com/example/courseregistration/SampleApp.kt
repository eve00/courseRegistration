package com.example.courseregistration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.courseregistration.ui.theme.SampleTheme

@Composable
fun SampleApp(){
    SampleTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            SampleNavigationActions(navController)
        }
        val navBackStateEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStateEntry?.destination?.route ?: Destinations.HOME_ROUTE
        val coroutineScope = rememberCoroutineScope()

        SampleNavGraph(
            navController = navController
        )
    }
}