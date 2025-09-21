package com.example.presentation.content.scanQrCode

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.core.Icon.Back
import com.example.core.component.ProjectButton
import com.example.core.component.ProjectIcon
import com.example.core.component.ProjectScreen
import com.example.core.component.ProjectTextField
import com.example.core.theme.ProjectSpaces
import com.example.core.theme.ProjectTheme
import com.example.domain.usecase.RoomIdTransformationUseCase
import com.example.presentation.R
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun ScanQrCodeRoute(
    popBackStack: () -> Unit,
    navigateToWaitingRoom: () -> Unit
) {
    // view model
    val scanQrCodeViewModel: ScanQrCodeViewModel = hiltViewModel()

    // local value
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
    val lifecycleOwner = LocalContext.current as LifecycleOwner
    var isQrScanMode by remember { mutableStateOf(true) }
    var nicknameValue by remember { mutableStateOf("") }
    var roomIdValue by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    // side effect
    LaunchedEffect(Unit) {
        launch {
            scanQrCodeViewModel.handleWebSocketConnect(
                onConnect = navigateToWaitingRoom
            )
        }
    }

    LaunchedEffect(isQrScanMode) {
        if (!isQrScanMode) {
            focusRequester.requestFocus()
        }
    }


    ScanQrCodeScreen(
        onQrScanned = { },
        cameraExecutor = cameraExecutor,
        lifecycleOwner = lifecycleOwner,
        popBackStack = popBackStack,
        isQrScanMode = isQrScanMode,
        onQrCodeScanModeClick = { isQrScanMode = true },
        onInputRoomIdModeClick = { isQrScanMode = false },
        focusRequester = focusRequester,
        nicknameValue = nicknameValue,
        roomIdValue = roomIdValue,
        onNicknameValueChange = { nicknameValue = it },
        onRoomIdValueChange = { roomIdValue = it }
    )
}

@OptIn(ExperimentalGetImage::class)
@Composable
fun ScanQrCodeScreen(
    onQrScanned: (String) -> Unit,
    cameraExecutor: ExecutorService,
    lifecycleOwner: LifecycleOwner,
    popBackStack: () -> Unit,
    isQrScanMode: Boolean,
    onQrCodeScanModeClick: () -> Unit,
    onInputRoomIdModeClick: () -> Unit,
    focusRequester: FocusRequester,
    nicknameValue: String,
    roomIdValue: String,
    onNicknameValueChange: (String) -> Unit,
    onRoomIdValueChange: (String) -> Unit
) {
    ProjectScreen.Screen(
        padding = false,
    ) {
        if (isQrScanMode) {
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx).apply {
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    }

                    val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()

                        val preview = Preview.Builder().build().also {
                            it.surfaceProvider = previewView.surfaceProvider
                        }

                        val imageAnalysis = ImageAnalysis.Builder()
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build().also { analysis ->
                                val scanner = BarcodeScanning.getClient()
                                analysis.setAnalyzer(cameraExecutor) { imageProxy ->
                                    val mediaImage = imageProxy.image
                                    if (mediaImage != null) {
                                        val image = InputImage.fromMediaImage(
                                            mediaImage,
                                            imageProxy.imageInfo.rotationDegrees
                                        )
                                        scanner.process(image)
                                            .addOnSuccessListener { barcodes ->
                                                for (barcode in barcodes) {
                                                    barcode.rawValue?.let { value ->
                                                        onQrScanned(value)
                                                    }
                                                }
                                            }
                                            .addOnFailureListener { it.printStackTrace() }
                                            .addOnCompleteListener { imageProxy.close() }
                                    } else {
                                        imageProxy.close()
                                    }
                                }
                            }

                        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                imageAnalysis
                            )
                        } catch (exc: Exception) {
                            Log.e("QrCodeScan", "Camera binding failed", exc)
                        }

                    }, ContextCompat.getMainExecutor(ctx))

                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        ProjectScreen.Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = ProjectTheme.space.space2),
                    contentAlignment = Alignment.Center
                ) {
                    ProjectIcon(
                        icon = Back,
                        modifier = Modifier.align(alignment = Alignment.CenterStart),
                        size = ProjectSpaces.Space8,
                        onClick = popBackStack
                    )

                    Text(
                        text = stringResource(R.string.component_waiting_room),
                        modifier = Modifier.align(alignment = Alignment.Center),
                        style = ProjectTheme.typography.l.medium,
                        color = ProjectTheme.color.primary.fontColor
                    )
                }
            },
            containerColor = Color.Transparent
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(height = ProjectSpaces.Space8))

                Text(
                    text = if (isQrScanMode) {
                        stringResource(id = R.string.scan_qr_code_scan_qr_code)
                    } else {
                        stringResource(
                            id = R.string.scan_qr_code_input_room_id
                        )
                    },
                    color = ProjectTheme.color.primary.fontColor,
                    style = ProjectTheme.typography.m.medium,
                )

                Spacer(modifier = Modifier.height(height = ProjectSpaces.Space4))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ProjectButton.Primary.Xlarge(
                        text = stringResource(R.string.component_qr_code_scan),
                        modifier = Modifier.weight(0.5f),
                        onClick = onQrCodeScanModeClick,
                    )

                    Spacer(modifier = Modifier.width(width = ProjectSpaces.Space4))

                    ProjectButton.Primary.Xlarge(
                        text = stringResource(R.string.component_input_room_id),
                        modifier = Modifier.weight(0.5f),

                        onClick = onInputRoomIdModeClick
                    )
                }

                Spacer(modifier = Modifier.height(height = ProjectSpaces.Space4))

                if (isQrScanMode) {
                    Box(
                        modifier = Modifier
                            .size(250.dp)
                            .border(
                                width = 3.dp,
                                color = Color.Green,
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                    )

                    Spacer(modifier = Modifier.height(height = ProjectSpaces.Space2))

                    ProjectButton.Primary.Medium(
                        text = stringResource(id = R.string.component_adjust_focus),
                        onClick = {}
                    )
                } else {
                    Text(
                        text = stringResource(R.string.main_dialog_input_nickname),
                        style = ProjectTheme.typography.s.medium,
                        color = ProjectTheme.color.primary.fontColor
                    )

                    Spacer(modifier = Modifier.height(ProjectTheme.space.space2))

                    ProjectTextField.OutlinedTextField(
                        value = nicknameValue,
                        onValueChange = { onNicknameValueChange(it) },
                        modifier = Modifier.focusRequester(focusRequester = focusRequester),
                        textCenter = true
                    )

                    Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

                    Text(
                        text = stringResource(R.string.main_dialog_input_room_id),
                        style = ProjectTheme.typography.s.medium,
                        color = ProjectTheme.color.primary.fontColor
                    )

                    Spacer(modifier = Modifier.height(ProjectTheme.space.space2))

                    ProjectTextField.OutlinedTextField(
                        value = roomIdValue,
                        onValueChange = { onRoomIdValueChange(it) },
                        modifier = Modifier.focusRequester(focusRequester = focusRequester),
                        textCenter = true,
                        visualTransformation = RoomIdTransformationUseCase()
                    )

                    Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

                    ProjectButton.Primary.Small(
                        text = stringResource(R.string.component_enter),
                        onClick = {

                        },
                        enabled = nicknameValue.length in 1..20 && roomIdValue.length == 12
                    )
                }
            }
        }
    }
}
