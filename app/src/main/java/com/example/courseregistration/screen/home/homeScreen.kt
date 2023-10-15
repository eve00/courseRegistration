package com.example.courseregistration.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.courseregistration.data.courseRegistrations.CourseRegistration


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val courses by viewModel.courses.collectAsStateWithLifecycle()
    val courseRegistrations by viewModel.courseRegistrations.collectAsStateWithLifecycle()
    val studentId = viewModel.studentId


    Scaffold(
       topBar = {
                TopAppBar(title = { Text("courseRegistration App") })
       },
    ) { innerPadding ->
        Surface(modifier.padding(innerPadding)) {
            Column {
                Text(text = studentId)
                LazyColumn(Modifier.background(color = Color.Cyan)) {
                    items(courses){course ->
                        Text(text = course.title)
                    }
                }
                Text(text = courseRegistrations.size.toString())
                Button(onClick = { viewModel.createCourseRegistration() }) {
                    Text(text = "ADD")
                }
                LazyColumn(Modifier.background(color = Color.LightGray)) {
                    items(courseRegistrations){courseRegistration ->
                        courseRegistrationItem(courseRegistration = courseRegistration)

                    }
                }

            }
        }

    }
}

@Composable
fun courseRegistrationItem(courseRegistration: CourseRegistration){
    Row() {
        Text(text = courseRegistration.studentId)
        Text(text = courseRegistration.courseId)
        Text(text = courseRegistration.createdAt.toString())
    }
}
