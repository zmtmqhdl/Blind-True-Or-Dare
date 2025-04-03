package com.example.presentation.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.presentation.screen.MyWorldScreen.PrimaryScreen

@Composable
fun SplashScreen() {
    PrimaryScreen() {
        Text("Splash")
    }
}