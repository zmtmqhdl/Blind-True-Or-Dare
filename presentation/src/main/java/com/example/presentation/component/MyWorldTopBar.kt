package com.example.presentation.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.example.presentation.component.Icon.Back
import com.example.presentation.component.Icon.Close
import com.example.presentation.component.Icon.Forward
import com.example.presentation.theme.MyWorldTheme
import com.example.presentation.util.MyWorldPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryTopBar(
    title: @Composable () -> Unit,
    size: Dp = MyWorldTheme.space.space4,
    leftIcons: List<ImageVector>? = null,
    rightIcons: List<ImageVector>? = null,
    onLeftIconClick: (() -> Unit)? = null,
    onRightIconClick: List<(() -> Unit)>? = null,
) {
    TopAppBar(
        title = { title() },
        navigationIcon = @Composable {
            leftIcons?.forEach {
                MyWorldIcon(icon = it, onClick = { onLeftIconClick?.invoke() }, size = size)
            }
        },
        actions = @Composable {
            rightIcons?.forEachIndexed { index, value ->
                MyWorldIcon(
                    icon = value,
                    onClick = { onRightIconClick?.getOrNull(index)?.invoke() }
                )
            }
        }
    )
}

@MyWorldPreview
@Composable
private fun PrimaryTopBarPreview() {
    PrimaryTopBar(
        title = { Text("title") },
        leftIcons = listOf(Back),
        rightIcons = listOf(Close, Forward),
        onLeftIconClick = { },
        onRightIconClick = listOf(
            {  },
            {  }
        ),
    )
}