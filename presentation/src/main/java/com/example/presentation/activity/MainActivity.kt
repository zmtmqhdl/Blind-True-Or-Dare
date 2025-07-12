package com.example.presentation.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.domain.repository.LoadingRepository
import com.example.presentation.LoadingManager
import com.example.presentation.component.ProjectScreen
import com.example.presentation.navigation.MainGraph
import com.example.presentation.splash.SplashViewModel
import com.example.presentation.theme.ProjectShapes.Box
import com.example.presentation.theme.ProjectTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var loadingRepository: LoadingRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            val splashViewModel: SplashViewModel = hiltViewModel()
            val navController = rememberNavController()

            val loading by loadingRepository.loading.collectAsState()


            ProjectTheme {
                ProjectScreen.LoadingScreen(
                    loading = loading,
                    content = {
                        MainGraph(navController = navController)
                    }
                )
            }
        }
    }
}