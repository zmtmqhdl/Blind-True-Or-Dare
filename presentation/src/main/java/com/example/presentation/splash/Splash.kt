package com.example.presentation.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.presentation.screen.ProjectScreen.PrimaryScreen

@Composable
fun SplashRoute() {
    SplashScreen()
}

@Composable
fun SplashScreen() {
    PrimaryScreen() {
        Text("Splash")
    }
}