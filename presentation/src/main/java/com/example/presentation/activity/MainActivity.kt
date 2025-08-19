package com.example.presentation.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.core.component.ProjectScreen
import com.example.core.theme.ProjectTheme
import com.example.domain.repository.LoadingRepository
import com.example.domain.repository.RoomRepository
import com.example.domain.repository.WebSocketRepository
import com.example.presentation.navigation.MainGraph
import com.example.presentation.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var loadingRepository: LoadingRepository

    @Inject
    lateinit var roomDataRepository: RoomRepository

    @Inject
    lateinit var webSocketRepository: WebSocketRepository

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
                    loadingColor = ProjectTheme.color.white,
                    content = {
                        MainGraph(navController = navController)
                    }
                )
            }
        }
    }


    override fun onResume() {
        super.onResume()
        if (webSocketRepository.isConnected.value) {
            webSocketRepository.reconnect(
                roomId = roomDataRepository.room.value!!.roomId,
                player = roomDataRepository.player.value!!
            )
        }
    }
}
// todo - resume이랑 네트워크 연결 감지 -> 근데 roomId랑 player를 어디서 담아두지