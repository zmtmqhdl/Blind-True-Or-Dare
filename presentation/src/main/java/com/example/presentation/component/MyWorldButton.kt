package com.example.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.presentation.common.IconPosition
import com.example.presentation.theme.MyWorldColorSet
import com.example.presentation.theme.MyWorldTheme
import com.example.presentation.util.MyWorldPreview

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier? = null,
    state: Boolean,
    color: MyWorldColorSet,
    width: Dp? = null,
    height: Dp,
    space: Dp,
    style: TextStyle,
    icon: ImageVector? = null,
    iconPosition: IconPosition = IconPosition.DEFAULT,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            ?: if (width != null) {
                Modifier
                    .width(width)
                    .height(height)
                    .border(width = MyWorldTheme.space.space0, color = color.outline, shape = MyWorldTheme.shape.button)
                    .clip(MyWorldTheme.shape.button)
            } else {
                Modifier
                    .fillMaxWidth()
                    .height(height)
                    .border(width = MyWorldTheme.space.space0, color = color.outline)
                    .clip(MyWorldTheme.shape.button)
            },
        enabled = state,
        shape = MyWorldTheme.shape.button,
        colors = if (state) ButtonDefaults.buttonColors(color.background) else ButtonDefaults.buttonColors(
            MyWorldTheme.color.disable.background
        ),
        contentPadding = PaddingValues(
            start = space,
            end = space
        )
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
    MyWorldTheme {
        MyWorldButton.CTA.Xlarge(
            text = "Xlarge",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_CTA_Large_Preview() {
    MyWorldTheme {
        MyWorldButton.CTA.Large(
            text = "Large",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_CTA_Medium_Preview() {
    MyWorldTheme {
        MyWorldButton.CTA.Medium(
            text = "Medium",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_CTA_Small_Preview() {
    MyWorldTheme {
        MyWorldButton.CTA.Small(
            text = "Small",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_CTA_Tiny_Preview() {
    MyWorldTheme {
        MyWorldButton.CTA.Tiny(
            text = "Tiny",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Primary_Xlarge_Preview() {
    MyWorldTheme {
        MyWorldButton.Primary.Xlarge(
            text = "Xlarge",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Primary_Large_Preview() {
    MyWorldTheme {
        MyWorldButton.Primary.Large(
            text = "Large",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Primary_Medium_Preview() {
    MyWorldTheme {
        MyWorldButton.Primary.Medium(
            text = "Medium",
            onClick = { },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyWorldButton_Primary_Small_Preview() {
    MyWorldTheme {
        MyWorldButton.Primary.Small(
            text = "Small",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Primary_Tiny_Preview() {
    MyWorldTheme {
        MyWorldButton.Primary.Tiny(
            text = "Tiny",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Secondary_Xlarge_Preview() {
    MyWorldTheme {
        MyWorldButton.Secondary.Xlarge(
            text = "Xlarge",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Secondary_Large_Preview() {
    MyWorldTheme {
        MyWorldButton.Secondary.Large(
            text = "Large",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Secondary_Medium_Preview() {
    MyWorldTheme {
        MyWorldButton.Secondary.Medium(
            text = "Medium",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Secondary_Small_Preview() {
    MyWorldTheme {
        MyWorldButton.Secondary.Small(
            text = "Small",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Secondary_Tiny_Preview() {
    MyWorldTheme {
        MyWorldButton.Secondary.Tiny(
            text = "Tiny",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Warning_Xlarge_Preview() {
    MyWorldTheme {
        MyWorldButton.Warning.Xlarge(
            text = "Xlarge",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Warning_Large_Preview() {
    MyWorldTheme {
        MyWorldButton.Warning.Large(
            text = "Large",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Warning_Medium_Preview() {
    MyWorldTheme {
        MyWorldButton.Warning.Medium(
            text = "Medium",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Warning_Small_Preview() {
    MyWorldTheme {
        MyWorldButton.Warning.Small(
            text = "Small",
            onClick = { },
        )
    }
}

@MyWorldPreview
@Composable
private fun MyWorldButton_Warning_Tiny_Preview() {
    MyWorldTheme {
        MyWorldButton.Warning.Tiny(
            text = "Tiny",
            onClick = { },
        )
    }
}
