package com.example.core.component

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
import com.example.core.Icon.Person
import com.example.core.core.ProjectPreview
import com.example.core.type.IconPosition
import com.example.core.theme.ProjectColorSet
import com.example.core.theme.ProjectTheme
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
            ProjectSnackBarVisuals(
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

data class ProjectSnackBarVisuals(
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
fun ProjectSnackBar(
    snackBarHostState: SnackbarHostState,
    icon: ImageVector? = null,
    iconColor: Color = ProjectTheme.color.black,
    containerColor: Color = ProjectTheme.color.white,
    color: ProjectColorSet = ProjectTheme.color.primary
) {
    SnackbarHost(
        hostState = snackBarHostState,
        snackbar = { snackBarData ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = ProjectTheme.space.space0,
                        color = color.outline,
                        shape = ProjectTheme.shape.snackBar
                    )
                    .background(color = containerColor, shape = ProjectTheme.shape.snackBar)
            ) {
                Row(
                    modifier = Modifier.padding(ProjectTheme.space.space4),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    icon?.let {
                        ProjectIcon(
                            icon = icon,
                            color = iconColor,
                            iconPosition = IconPosition.LEFT
                        )
                    }
                    Text(
                        text = snackBarData.visuals.message,
                        color = color.fontColor,
                        textAlign = TextAlign.Center,
                        style = ProjectTheme.typography.s.medium
                    )
                }
            }
        }
    )
}

@ProjectPreview
@Composable
fun ProjectSnackBarPreview() {

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        snackBarHostState.showSnackbar("message")
    }

    ProjectTheme {
        ProjectSnackBar(
            snackBarHostState = snackBarHostState,
            icon = Person,
            iconColor = ProjectTheme.color.primary.fontColor
        )
    }
}