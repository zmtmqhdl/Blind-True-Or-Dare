package com.example.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SplashScreen() {
    MyWorldScreen.PrimaryScreen() {
        Text("Splash")
    }
}