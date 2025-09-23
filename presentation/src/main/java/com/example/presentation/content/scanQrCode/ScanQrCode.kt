package com.example.presentation.content.scanQrCode

import android.graphics.Bitmap
import android.graphics.BitmapFactory.decodeByteArray
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import android.media.Image
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.core.Icon.Back
import com.example.core.component.PrimaryDialog
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
import java.io.ByteArrayOutputStream
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun ScanQrCodeRoute(
    popBackStack: () -> Unit, navigateToWaitingRoom: () -> Unit
) {
    // view model
    val viewModel: ScanQrCodeViewModel = hiltViewModel()

    // local value
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
    val lifecycleOwner = LocalContext.current as LifecycleOwner
    var isQrScanMode by remember { mutableStateOf(true) }
    var nickname by remember { mutableStateOf("") }
    var roomId by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    var inputNameDialog by remember { mutableStateOf(false) }
    val qrBoxLineLength = with(LocalDensity.current) { 50.dp.toPx() }

    // dialog
    if (inputNameDialog) {
        PrimaryDialog(
            title = stringResource(id = R.string.component_join_room), onDismissRequest = {
                nickname = ""
                inputNameDialog = false
            }) {
            Text(
                text = stringResource(R.string.component_input_nickname),
                style = ProjectTheme.typography.s.medium,
                color = ProjectTheme.color.primary.fontColor
            )

            Spacer(modifier = Modifier.height(ProjectTheme.space.space2))

            ProjectTextField.OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                modifier = Modifier.focusRequester(focusRequester = focusRequester),
                textCenter = true
            )

            Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

            Row {
                ProjectButton.Primary.Small(
                    text = stringResource(id = R.string.component_cancel), onClick = {
                        inputNameDialog = false
                        nickname = ""
                    }, modifier = Modifier.weight(0.5f), enabled = nickname.isNotEmpty()
                )

                Spacer(modifier = Modifier.width(ProjectSpaces.Space4))

                ProjectButton.Primary.Small(
                    text = stringResource(id = R.string.component_enter), onClick = {
                        viewModel.joinRoom(
                            nickname = nickname, roomId = roomId
                        )
                        inputNameDialog = false
                    }, modifier = Modifier.weight(0.5f), enabled = nickname.length in 1..20
                )
            }
        }
    }

    // side effect
    LaunchedEffect(Unit) {
        launch {
            viewModel.handleWebSocketConnect(
                onConnect = navigateToWaitingRoom
            )
        }
    }

    LaunchedEffect(isQrScanMode) {
        if (!isQrScanMode) {
            roomId = ""
            focusRequester.requestFocus()
        }
    }

    ScanQrCodeScreen(
        onQrScanned = {
            roomId = it
            inputNameDialog = true
        },
        cameraExecutor = cameraExecutor,
        lifecycleOwner = lifecycleOwner,
        popBackStack = popBackStack,
        isQrScanMode = isQrScanMode,
        onQrCodeScanModeClick = { isQrScanMode = true },
        onInputRoomIdModeClick = { isQrScanMode = false },
        focusRequester = focusRequester,
        nicknameValue = nickname,
        roomIdValue = roomId,
        onNicknameValueChange = { nickname = it },
        onRoomIdValueChange = { roomId = it },
        onAdjustFocusClick = {},
        onJoinRoomClick = {
            viewModel.joinRoom(
                nickname = nickname, roomId = roomId
            )
        },
        qrBoxLineLength = qrBoxLineLength
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
    onRoomIdValueChange: (String) -> Unit,
    onAdjustFocusClick: () -> Unit,
    onJoinRoomClick: () -> Unit,
    qrBoxLineLength: Float
) {
    var boxBounds by remember { mutableStateOf(Rect()) }

    ProjectScreen.Screen(
        padding = false
    ) {
        if (isQrScanMode) {
            AndroidView(
                factory = { context ->
                    val previewView = PreviewView(context).apply {
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    }

                    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
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
                                    if (mediaImage != null && boxBounds.width() > 0 && boxBounds.height() > 0) {
                                        val bitmap = mediaImage.toBitmap()
                                        val scaleX = bitmap.width.toFloat() / previewView.width
                                        val scaleY = bitmap.height.toFloat() / previewView.height

                                        // 화면 좌표 -> 이미지 좌표 변환
                                        val cropLeft = (boxBounds.left * scaleX).toInt()
                                            .coerceIn(0, bitmap.width - 1)
                                        val cropTop = (boxBounds.top * scaleY).toInt()
                                            .coerceIn(0, bitmap.height - 1)
                                        val cropRight = (boxBounds.right * scaleX).toInt()
                                            .coerceIn(cropLeft + 1, bitmap.width)
                                        val cropBottom = (boxBounds.bottom * scaleY).toInt()
                                            .coerceIn(cropTop + 1, bitmap.height)
                                        val cropWidth = cropRight - cropLeft
                                        val cropHeight = cropBottom - cropTop

                                        val croppedBitmap = Bitmap.createBitmap(
                                            bitmap, cropLeft, cropTop, cropWidth, cropHeight
                                        )
                                        val inputImage = InputImage.fromBitmap(
                                            croppedBitmap, imageProxy.imageInfo.rotationDegrees
                                        )

                                        scanner.process(inputImage)
                                            .addOnSuccessListener { barcodes ->
                                                for (barcode in barcodes) {
                                                    barcode.rawValue?.let { value ->
                                                        onQrScanned(value)
                                                    }
                                                }
                                            }.addOnFailureListener { it.printStackTrace() }
                                            .addOnCompleteListener { imageProxy.close() }
                                    } else {
                                        imageProxy.close()
                                    }
                                }
                            }

                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                CameraSelector.DEFAULT_BACK_CAMERA,
                                preview,
                                imageAnalysis
                            )
                        } catch (exc: Exception) {
                            Log.e("QrCodeScan", "Camera binding failed", exc)
                        }

                    }, ContextCompat.getMainExecutor(context))

                    previewView
                }, modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        val boxSize = 250.dp.toPx()
                        val left = (size.width - boxSize) / 2
                        val top = (size.height - boxSize) / 2
                        val right = left + boxSize
                        val bottom = top + boxSize

                        val overlayColor = Color.Black.copy(alpha = 0.5f)

                        drawRect(
                            color = overlayColor,
                            topLeft = Offset(0f, 0f),
                            size = Size(size.width, top)
                        )
                        drawRect(
                            color = overlayColor,
                            topLeft = Offset(0f, bottom),
                            size = Size(size.width, size.height - bottom)
                        )
                        drawRect(
                            color = overlayColor,
                            topLeft = Offset(0f, top),
                            size = Size(left, boxSize)
                        )
                        drawRect(
                            color = overlayColor,
                            topLeft = Offset(right, top),
                            size = Size(size.width - right, boxSize)
                        )
                    }
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
                        modifier = Modifier.align(Alignment.CenterStart),
                        size = ProjectSpaces.Space8,
                        onClick = popBackStack
                    )

                    Text(
                        text = stringResource(R.string.component_waiting_room),
                        modifier = Modifier.align(Alignment.Center),
                        style = ProjectTheme.typography.l.medium,
                        color = ProjectTheme.color.primary.fontColor
                    )
                }
            }, containerColor = Color.Transparent
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopStart),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(ProjectSpaces.Space8))

                    Text(
                        text = if (isQrScanMode) {
                            stringResource(id = R.string.scan_qr_code_scan_qr_code)
                        } else {
                            stringResource(id = R.string.scan_qr_code_input_room_id)
                        },
                        color = ProjectTheme.color.primary.fontColor,
                        style = ProjectTheme.typography.m.medium,
                    )

                    Spacer(modifier = Modifier.height(ProjectSpaces.Space4))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ProjectButton.Primary.Xlarge(
                            text = stringResource(R.string.component_qr_code_scan),
                            modifier = Modifier.weight(0.5f),
                            onClick = onQrCodeScanModeClick,
                        )

                        Spacer(modifier = Modifier.width(ProjectSpaces.Space4))

                        ProjectButton.Primary.Xlarge(
                            text = stringResource(R.string.component_input_room_id),
                            modifier = Modifier.weight(0.5f),
                            onClick = onInputRoomIdModeClick
                        )
                    }
                }

                if (isQrScanMode) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier
                            .size(250.dp)
                            .onGloballyPositioned {
                                val pos = it.positionInRoot()
                                val size = it.size
                                boxBounds = Rect(
                                    pos.x.toInt(),
                                    pos.y.toInt(),
                                    (pos.x + size.width).toInt(),
                                    (pos.y + size.height).toInt()
                                )
                            }
                            .drawBehind {
                                drawPath(
                                    path = Path().apply {
                                        moveTo(0f, qrBoxLineLength)
                                        lineTo(0f, 0f)
                                        lineTo(qrBoxLineLength, 0f)

                                        moveTo(size.width - qrBoxLineLength, 0f)
                                        lineTo(size.width, 0f)
                                        lineTo(size.width, qrBoxLineLength)

                                        moveTo(0f, size.height - qrBoxLineLength)
                                        lineTo(0f, size.height)
                                        lineTo(qrBoxLineLength, size.height)

                                        moveTo(size.width, size.height - qrBoxLineLength)
                                        lineTo(size.width, size.height)
                                        lineTo(size.width - qrBoxLineLength, size.height)
                                    },
                                    color = Color.Green,
                                    style = Stroke(
                                        width = 5.dp.toPx(),
                                        cap = StrokeCap.Round
                                    )
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(ProjectSpaces.Space2))

                        ProjectButton.Primary.Medium(
                            text = stringResource(id = R.string.component_adjust_focus),
                            onClick = {}
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.component_nickname),
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
                            text = stringResource(R.string.component_room_id),
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
                            onClick = onJoinRoomClick,
                            enabled = nicknameValue.length in 1..20 && roomIdValue.length == 12
                        )
                    }
                }
            }
        }
    }
}

fun Image.toBitmap(): Bitmap {
    val yBuffer = planes[0].buffer
    val uBuffer = planes[1].buffer
    val vBuffer = planes[2].buffer

    val ySize = yBuffer.remaining()
    val uSize = uBuffer.remaining()
    val vSize = vBuffer.remaining()

    val nv21 = ByteArray(ySize + uSize + vSize)
    yBuffer.get(nv21, 0, ySize)
    vBuffer.get(nv21, ySize, vSize)
    uBuffer.get(nv21, ySize + vSize, uSize)

    val yuvImage = YuvImage(nv21, ImageFormat.NV21, width, height, null)
    val out = ByteArrayOutputStream()
    yuvImage.compressToJpeg(Rect(0, 0, width, height), 100, out)
    val imageBytes = out.toByteArray()
    return decodeByteArray(imageBytes, 0, imageBytes.size)
}