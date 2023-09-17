package com.example.courseregistration.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val courses = viewModel.courses.collectAsStateWithLifecycle()

    Scaffold(
       topBar = {
                TopAppBar(title = { Text("courseRegistration App") })
       },
    ) { innerPadding ->
        Surface(modifier.padding(innerPadding)) {
            Column {
                LazyColumn {
                    items(courses.value){course ->
                        Text(text = course.title)
                    }

                }
            }
        }

    }


}
