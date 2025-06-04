package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.example.presentation.component.Icon.Person
import com.example.presentation.common.IconPosition
import com.example.presentation.theme.ProjectColorSet
import com.example.presentation.theme.MyWorldTheme
import com.example.presentation.theme.ProjectTheme
import com.example.presentation.util.MyWorldPreview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun SnackBar(
    backgroundColor: Color?,
    duration: SnackbarDuration = SnackbarDuration.Short,
    icon: ImageVector?,
    iconColor: Color?,
    outlineColor: Color?,
    snackBarHostState: SnackbarHostState,
    text: String,
) {
    snackBarHostState.currentSnackbarData?.dismiss()

    CoroutineScope(Dispatchers.Main).launch {
        snackBarHostState.showSnackbar(
            MyWorldSnackBarVisuals(
                backgroundColor = backgroundColor,
                duration = duration,
                icon = icon,
                iconColor = iconColor,
                message = text,
                outlineColor = outlineColor
            )
        )
    }
}

data class MyWorldSnackBarVisuals(
    override val actionLabel: String? = null,
    val backgroundColor: Color? = null,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    val icon: ImageVector? = null,
    val iconColor: Color? = null,
    override val message: String,
    val outlineColor: Color? = null,
    override val withDismissAction: Boolean = false,
) : SnackbarVisuals

@Composable
fun MyWorldSnackBar(
    snackBarHostState: SnackbarHostState,
    icon: ImageVector? = null,
    iconColor: Color = MyWorldTheme.color.black,
    containerColor: Color = MyWorldTheme.color.white,
    color: ProjectColorSet = MyWorldTheme.color.primary
) {
    SnackbarHost(
        hostState = snackBarHostState,
        snackbar = { snackBarData ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = MyWorldTheme.space.space0,
                        color = color.outline,
                        shape = MyWorldTheme.shape.snackBar
                    )
                    .background(color = containerColor, shape = MyWorldTheme.shape.snackBar)
            ) {
                Row(
                    modifier = Modifier.padding(MyWorldTheme.space.space4),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    icon?.let {
                        MyWorldIcon(
                            icon = icon,
                            color = iconColor,
                            iconPosition = IconPosition.LEFT
                        )
                    }
                    Text(
                        text = snackBarData.visuals.message,
                        color = color.fontColor,
                        textAlign = TextAlign.Center,
                        style = MyWorldTheme.typography.s.medium
                    )
                }
            }
        }
    )
}

@MyWorldPreview
@Composable
fun MyWorldSnackBarPreview() {

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        snackBarHostState.showSnackbar("message")
    }

    ProjectTheme {
        MyWorldSnackBar(
            snackBarHostState = snackBarHostState,
            icon = Person,
            iconColor = MyWorldTheme.color.primary.fontColor
        )
    }
}