package com.example.presentation.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.core.component.ProjectScreen.Screen

@Composable
fun SplashRoute() {
    SplashScreen()
}

@Composable
fun SplashScreen() {
    Screen() {
        Text("Splash")
    }
}