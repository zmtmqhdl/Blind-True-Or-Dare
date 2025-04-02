package com.example.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.example.presentation.Icon.Close
import com.example.presentation.common.IconPosition
import com.example.presentation.theme.MyWorldTheme
import com.example.presentation.util.MyWorldPreview

@Composable
fun MyWorldIcon(
    icon: ImageVector,
    onClick: () -> Unit = {},
    size: Dp = MyWorldTheme.space.space4,
    color: Color = MyWorldTheme.color.black,
    iconPosition: IconPosition = IconPosition.DEFAULT,
) {
    when (iconPosition) {
        IconPosition.DEFAULT ->
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(size)
                    .clickable {
                        onClick()
                    },
                tint = color
            )

        IconPosition.LEFT ->
            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(size)
                        .clickable {
                            onClick()
                        },
                    tint = color
                )
                Spacer(modifier = Modifier.width(MyWorldTheme.space.space1))
            }

        IconPosition.RIGHT ->
            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                Spacer(modifier = Modifier.width(MyWorldTheme.space.space1))
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(size)
                        .clickable {
                            onClick()
                        },
                    tint = color
                )
            }
    }
}

@MyWorldPreview
@Composable
private fun MyWorldIconPreview() {
    MyWorldIcon(
        icon = Close,
        onClick = { },
    )
}