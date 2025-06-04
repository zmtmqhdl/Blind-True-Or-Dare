package com.example.presentation.component

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
import com.example.presentation.common.IconPosition
import com.example.presentation.theme.ProjectColorSet
import com.example.presentation.theme.MyWorldTheme
import com.example.presentation.theme.ProjectTheme
import com.example.presentation.util.MyWorldPreview

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier? = null,
    state: Boolean,
    color: ProjectColorSet,
    width: Dp? = null,
    height: Dp,
    space: Dp,
    style: TextStyle,
    icon: ImageVector? = null,
    iconPosition: IconPosition = IconPosition.DEFAULT,
) {
    Box(
        modifier = modifier
            ?: if (width != null) {
                Modifier
                    .width(width)
                    .height(height)
                    .background(
                        color = if (state) color.background else MyWorldTheme.color.disable.background,
                        shape = MyWorldTheme.shape.button
                    )
                    .border(
                        width = MyWorldTheme.space.space0,
                        color = color.outline,
                        shape = MyWorldTheme.shape.button
                    )
                    .clip(MyWorldTheme.shape.button)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick,
                    )
            } else {
                Modifier
                    .fillMaxWidth()
                    .height(height)
                    .background(
                        color = if (state) color.background else MyWorldTheme.color.disable.background,
                        shape = MyWorldTheme.shape.button
                    )
                    .border(
                        width = MyWorldTheme.space.space0,
                        color = color.outline,
                        shape = MyWorldTheme.shape.button
                    )
                    .clip(MyWorldTheme.shape.button)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick,
                    )
            },
        contentAlignment = Alignment.Center
    ) {
        if (icon != null && iconPosition == IconPosition.LEFT) {
            MyWorldIcon(icon = icon, iconPosition = iconPosition)
        }
        Text(
            text = text,
            color = if (state) color.fontColor else MyWorldTheme.color.disable.fontColor,
            style = style
        )
    }
    if (icon != null && iconPosition == IconPosition.RIGHT) {
        MyWorldIcon(icon = icon, iconPosition = iconPosition)
    }
}

object MyWorldButton {
    object CTA {
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
                state = state,
                color = MyWorldTheme.color.primary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space12,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.xl.regular,
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
                state = state,
                color = MyWorldTheme.color.primary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space11,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.l.regular,
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
                state = state,
                color = MyWorldTheme.color.primary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space9,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.m.regular,
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
                state = state,
                color = MyWorldTheme.color.primary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space8,
                space = MyWorldTheme.space.space3,
                style = MyWorldTheme.typography.s.regular,
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
                state = state,
                color = MyWorldTheme.color.primary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space7,
                space = MyWorldTheme.space.space3,
                style = MyWorldTheme.typography.xs.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }
    }

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
                state = state,
                color = MyWorldTheme.color.primary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space12,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.xl.regular,
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
                state = state,
                color = MyWorldTheme.color.primary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space11,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.l.regular,
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
                state = state,
                color = MyWorldTheme.color.primary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space9,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.m.regular,
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
                state = state,
                color = MyWorldTheme.color.primary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space8,
                space = MyWorldTheme.space.space3,
                style = MyWorldTheme.typography.s.regular,
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
                state = state,
                color = MyWorldTheme.color.primary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space7,
                space = MyWorldTheme.space.space3,
                style = MyWorldTheme.typography.xs.regular,
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
                state = state,
                color = MyWorldTheme.color.secondary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space12,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.xl.regular,
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
                state = state,
                color = MyWorldTheme.color.secondary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space11,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.l.regular,
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
                state = state,
                color = MyWorldTheme.color.secondary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space9,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.m.regular,
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
                state = state,
                color = MyWorldTheme.color.secondary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space8,
                space = MyWorldTheme.space.space3,
                style = MyWorldTheme.typography.s.regular,
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
                state = state,
                color = MyWorldTheme.color.secondary,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space7,
                space = MyWorldTheme.space.space3,
                style = MyWorldTheme.typography.xs.regular,
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
                state = state,
                color = MyWorldTheme.color.warning,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space12,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.xl.regular,
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
                state = state,
                color = MyWorldTheme.color.warning,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space11,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.l.regular,
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
                state = state,
                color = MyWorldTheme.color.warning,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space9,
                space = MyWorldTheme.space.space4,
                style = MyWorldTheme.typography.m.regular,
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
                state = state,
                color = MyWorldTheme.color.warning,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space8,
                space = MyWorldTheme.space.space3,
                style = MyWorldTheme.typography.s.regular,
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
                state = state,
                color = MyWorldTheme.color.warning,
                modifier = modifier,
                width = width,
                height = MyWorldTheme.space.space7,
                space = MyWorldTheme.space.space3,
                style = MyWorldTheme.typography.xs.regular,
                icon = icon,
                iconPosition = iconPosition
            )
        }
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_CTA_Xlarge_Preview() {
    ProjectTheme {
        MyWorldButton.CTA.Xlarge(
            text = "Xlarge",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_CTA_Large_Preview() {
    ProjectTheme {
        MyWorldButton.CTA.Large(
            text = "Large",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_CTA_Medium_Preview() {
    ProjectTheme {
        MyWorldButton.CTA.Medium(
            text = "Medium",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_CTA_Small_Preview() {
    ProjectTheme {
        MyWorldButton.CTA.Small(
            text = "Small",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_CTA_Tiny_Preview() {
    ProjectTheme {
        MyWorldButton.CTA.Tiny(
            text = "Tiny",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Primary_Xlarge_Preview() {
    ProjectTheme {
        MyWorldButton.Primary.Xlarge(
            text = "Xlarge",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Primary_Large_Preview() {
    ProjectTheme {
        MyWorldButton.Primary.Large(
            text = "Large",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Primary_Medium_Preview() {
    ProjectTheme {
        MyWorldButton.Primary.Medium(
            text = "Medium",
            onClick = { },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyWorldButton_Primary_Small_Preview() {
    ProjectTheme {
        MyWorldButton.Primary.Small(
            text = "Small",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Primary_Tiny_Preview() {
    ProjectTheme {
        MyWorldButton.Primary.Tiny(
            text = "Tiny",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Secondary_Xlarge_Preview() {
    ProjectTheme {
        MyWorldButton.Secondary.Xlarge(
            text = "Xlarge",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Secondary_Large_Preview() {
    ProjectTheme {
        MyWorldButton.Secondary.Large(
            text = "Large",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Secondary_Medium_Preview() {
    ProjectTheme {
        MyWorldButton.Secondary.Medium(
            text = "Medium",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Secondary_Small_Preview() {
    ProjectTheme {
        MyWorldButton.Secondary.Small(
            text = "Small",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Secondary_Tiny_Preview() {
    ProjectTheme {
        MyWorldButton.Secondary.Tiny(
            text = "Tiny",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Warning_Xlarge_Preview() {
    ProjectTheme {
        MyWorldButton.Warning.Xlarge(
            text = "Xlarge",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Warning_Large_Preview() {
    ProjectTheme {
        MyWorldButton.Warning.Large(
            text = "Large",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Warning_Medium_Preview() {
    ProjectTheme {
        MyWorldButton.Warning.Medium(
            text = "Medium",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Warning_Small_Preview() {
    ProjectTheme {
        MyWorldButton.Warning.Small(
            text = "Small",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Warning_Tiny_Preview() {
    ProjectTheme {
        MyWorldButton.Warning.Tiny(
            text = "Tiny",
            onClick = { },
        )
    }
}
