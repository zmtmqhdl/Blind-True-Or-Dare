package com.example.core.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.example.core.Icon.Close
import com.example.core.core.ProjectPreview
import com.example.core.theme.ProjectTheme
import com.example.core.type.IconPosition

@Composable
fun ProjectIcon(
    icon: ImageVector,
    onClick: (() -> Unit)? = null,
    size: Dp = ProjectTheme.space.space4,
    color: Color = ProjectTheme.color.black,
    iconPosition: IconPosition = IconPosition.DEFAULT,
) {
    val modifier = if (onClick != null) {
        Modifier
            .size(size)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    } else {
        Modifier.size(size)
    }

    when (iconPosition) {
        IconPosition.DEFAULT ->
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = modifier,
                tint = color
            )

        IconPosition.LEFT ->
            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = modifier,
                    tint = color
                )
                Spacer(modifier = Modifier.width(ProjectTheme.space.space1))
            }

        IconPosition.RIGHT ->
            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                Spacer(modifier = Modifier.width(ProjectTheme.space.space1))
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = modifier,
                    tint = color
                )
            }
    }
}

@ProjectPreview
@Composable
private fun ProjectIconPreview() {
    ProjectIcon(
        icon = Close,
        onClick = { },
    )
}