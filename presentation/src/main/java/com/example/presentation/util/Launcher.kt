package com.example.presentation.util

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat

@Composable
fun permissionRequestLauncher(): (
    permissionList: List<String>,
    onSuccess: () -> Unit,
    onDenied: () -> Unit,
    onPermanentlyDenied: (List<String>) -> Unit
) -> Unit {
    val context = LocalContext.current
    val activity = context as Activity

    val onSuccessState = remember { mutableStateOf({}) }
    val onDeniedState = remember { mutableStateOf({}) }
    val onPermanentlyDeniedState = remember { mutableStateOf<(List<String>) -> Unit>({}) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        val denied = result.filterValues { !it }.keys
        val permanentlyDenied = denied.filter {
            !ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
        }

        when {
            denied.isEmpty() -> onSuccessState.value()
            permanentlyDenied.isEmpty() -> onDeniedState.value()
            else -> onPermanentlyDeniedState.value(permanentlyDenied)
        }
    }

    return { permissionList, onSuccess, onDenied, onPermanentlyDenied ->
        onSuccessState.value = onSuccess
        onDeniedState.value = onDenied
        onPermanentlyDeniedState.value = onPermanentlyDenied
        launcher.launch(permissionList.toTypedArray())
    }
}