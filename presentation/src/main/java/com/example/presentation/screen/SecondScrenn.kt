package com.example.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.presentation.component.ProjectScreen

@Composable
fun SecondScreen() {
    ProjectScreen.PrimaryScreen() {
        Text("Second")
    }
}