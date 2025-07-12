package com.example.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.presentation.component.ProjectScreen

@Composable
fun FirstScreen() {
    ProjectScreen.Screen() {
        Text("First")
    }
}