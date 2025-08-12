package com.example.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.core.core.ProjectPreview
import com.example.core.theme.ProjectColorSet
import com.example.core.theme.ProjectTheme
import com.example.core.type.IconPosition

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier? = null,
    enabled: Boolean,
    color: ProjectColorSet,
    width: Dp? = null,
    height: Dp,
    space: Dp,
    style: TextStyle,
    icon: ImageVector? = null,
    iconPosition: IconPosition = IconPosition.DEFAULT,
) {
    Box(
        modifier = modifier ?: Modifier
            .then(if (width != null) Modifier.width(width) else Modifier.fillMaxWidth())
            .height(height)
            .background(
                color = if (enabled) color.background else ProjectTheme.color.disable.background,
                shape = ProjectTheme.shape.button
            )
            .border(
                width = ProjectTheme.space.space1,
                color = color.outline,
                shape = ProjectTheme.shape.button
            )
            .clip(ProjectTheme.shape.button)
            .then(
                if (enabled) Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick
                ) else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        if (icon != null && iconPosition == IconPosition.LEFT) {
            ProjectIcon(icon = icon, iconPosition = iconPosition)
        }
        Text(
            text = text,
            color = if (enabled) color.fontColor else ProjectTheme.color.disable.fontColor,
            style = style
        )
    }
    if (icon != null && iconPosition == IconPosition.RIGHT) {
        ProjectIcon(icon = icon, iconPosition = iconPosition)
    }
}

object ProjectButton {
    object Primary {
        @Composable
        fun Xlarge(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.primary,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space12,
                space = ProjectTheme.space.space4,
                style = ProjectTheme.typography.xl.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }

        @Composable
        fun Large(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.primary,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space11,
                space = ProjectTheme.space.space4,
                style = ProjectTheme.typography.l.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }

        @Composable
        fun Medium(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.primary,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space9,
                space = ProjectTheme.space.space4,
                style = ProjectTheme.typography.m.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }

        @Composable
        fun Small(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            enabled: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT,
        ) {
                PrimaryButton(
                    text = text,
                    onClick = onClick,
                    enabled = enabled,
                    color = ProjectTheme.color.primary,
                    modifier = modifier,
                    width = width,
                    height = ProjectTheme.space.space8,
                    space = ProjectTheme.space.space3,
                    style = ProjectTheme.typography.s.regular,
                    icon = icon,
                    iconPosition = iconPosition
                )
        }

        @Composable
        fun Tiny(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.primary,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space7,
                space = ProjectTheme.space.space3,
                style = ProjectTheme.typography.xs.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }
    }

    object Secondary {
        @Composable
        fun Xlarge(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.secondary,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space12,
                space = ProjectTheme.space.space4,
                style = ProjectTheme.typography.xl.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }

        @Composable
        fun Large(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.secondary,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space11,
                space = ProjectTheme.space.space4,
                style = ProjectTheme.typography.l.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }

        @Composable
        fun Medium(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.secondary,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space9,
                space = ProjectTheme.space.space4,
                style = ProjectTheme.typography.m.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }

        @Composable
        fun Small(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.secondary,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space8,
                space = ProjectTheme.space.space3,
                style = ProjectTheme.typography.s.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }

        @Composable
        fun Tiny(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.secondary,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space7,
                space = ProjectTheme.space.space3,
                style = ProjectTheme.typography.xs.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }
    }

    object Warning {
        @Composable
        fun Xlarge(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.warning,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space12,
                space = ProjectTheme.space.space4,
                style = ProjectTheme.typography.xl.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }

        @Composable
        fun Large(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.warning,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space11,
                space = ProjectTheme.space.space4,
                style = ProjectTheme.typography.l.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }

        @Composable
        fun Medium(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.warning,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space9,
                space = ProjectTheme.space.space4,
                style = ProjectTheme.typography.m.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }

        @Composable
        fun Small(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.warning,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space8,
                space = ProjectTheme.space.space3,
                style = ProjectTheme.typography.s.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }

        @Composable
        fun Tiny(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier? = null,
            width: Dp? = null,
            state: Boolean = true,
            icon: ImageVector? = null,
            iconPosition: IconPosition = IconPosition.LEFT
        ) {
            PrimaryButton(
                text = text,
                onClick = onClick,
                enabled = state,
                color = ProjectTheme.color.warning,
                modifier = modifier,
                width = width,
                height = ProjectTheme.space.space7,
                space = ProjectTheme.space.space3,
                style = ProjectTheme.typography.xs.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Primary_Xlarge_Preview() {
    ProjectTheme {
        ProjectButton.Primary.Xlarge(
            text = "Xlarge",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Primary_Large_Preview() {
    ProjectTheme {
        ProjectButton.Primary.Large(
            text = "Large",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Primary_Medium_Preview() {
    ProjectTheme {
        ProjectButton.Primary.Medium(
            text = "Medium",
            onClick = { },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProjectButton_Primary_Small_Preview() {
    ProjectTheme {
        ProjectButton.Primary.Small(
            text = "Small",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Primary_Tiny_Preview() {
    ProjectTheme {
        ProjectButton.Primary.Tiny(
            text = "Tiny",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Secondary_Xlarge_Preview() {
    ProjectTheme {
        ProjectButton.Secondary.Xlarge(
            text = "Xlarge",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Secondary_Large_Preview() {
    ProjectTheme {
        ProjectButton.Secondary.Large(
            text = "Large",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Secondary_Medium_Preview() {
    ProjectTheme {
        ProjectButton.Secondary.Medium(
            text = "Medium",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Secondary_Small_Preview() {
    ProjectTheme {
        ProjectButton.Secondary.Small(
            text = "Small",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Secondary_Tiny_Preview() {
    ProjectTheme {
        ProjectButton.Secondary.Tiny(
            text = "Tiny",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Warning_Xlarge_Preview() {
    ProjectTheme {
        ProjectButton.Warning.Xlarge(
            text = "Xlarge",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Warning_Large_Preview() {
    ProjectTheme {
        ProjectButton.Warning.Large(
            text = "Large",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Warning_Medium_Preview() {
    ProjectTheme {
        ProjectButton.Warning.Medium(
            text = "Medium",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Warning_Small_Preview() {
    ProjectTheme {
        ProjectButton.Warning.Small(
            text = "Small",
            onClick = { },
        )
    }
}

@ProjectPreview
@Composable
private fun ProjectButton_Warning_Tiny_Preview() {
    ProjectTheme {
        ProjectButton.Warning.Tiny(
            text = "Tiny",
            onClick = { },
        )
    }
}
