package com.example.presentation.content

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.core.component.PrimaryDialog
import com.example.core.component.ProjectButton
import com.example.core.component.ProjectScreen
import com.example.core.theme.ProjectTheme
import com.example.presentation.R
import com.example.presentation.navigation.MainGraph
import kotlinx.coroutines.launch

@SuppressLint("RestrictedApi")
@Composable
fun Content() {
    // view model
    val viewModel: ContentViewModel = hiltViewModel()

    // view model state value
    val loading by viewModel.loading.collectAsState()
    val isNetworkAvailableDialog by viewModel.isNetworkAvailableDialog.collectAsState()
    val reconnectTimer by viewModel.reconnectTimer.collectAsState()

    // local state
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        launch {
            viewModel.handleWebSocketConnect(
                onDisconnect = { navController.popBackStack() }
            )
        }
    }

    ProjectScreen.LoadingScreen(
        loading = loading,
        loadingColor = ProjectTheme.color.white,
        content = {
            MainGraph(navController = navController)
        }
    )

    if (isNetworkAvailableDialog) {
        PrimaryDialog(
            title = stringResource(id = R.string.component_network_error),
            onDismissRequest = {
                if (reconnectTimer == 0) {
                    viewModel.dismissIsNetworkAvailableDialog()
                    navController.popBackStack()
                }
            }
        ) {
            Text(
                text = stringResource(id = R.string.content_dialog_is_network_available_dialog_message, reconnectTimer)
            )

            Spacer(modifier = Modifier.height(ProjectTheme.space.space2))

            CircularProgressIndicator(
                modifier = Modifier.size(ProjectTheme.space.space12),
                color = ProjectTheme.color.primary.background,
                strokeWidth = ProjectTheme.space.space1
            )

            Spacer(modifier = Modifier.height(ProjectTheme.space.space2))

            ProjectButton.Primary.Medium(
                text = stringResource(id = R.string.component_cancel),
                onClick = {
                    if (reconnectTimer == 0) {
                        viewModel.dismissIsNetworkAvailableDialog()
                        navController.popBackStack()
                    }
                }
            )

        }
    }


}

// 일단 네트워크 다이얼로그부터 띄워봅시다!!
//