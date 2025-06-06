package com.example.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val lightColor = ProjectColor(
    primary = ProjectColors.Light.Primary,
    secondary = ProjectColors.Light.Secondary,
    tertiary = ProjectColors.Light.Tertiary,
    warning = ProjectColors.Light.Warning,
    alarm = ProjectColors.Light.Alarm,
    success = ProjectColors.Light.Success,
    caution = ProjectColors.Light.Caution,
    disable = ProjectColors.Light.Caution,
    white = ProjectColors.Light.white,
    black = ProjectColors.Light.black,
    gray600 = ProjectColors.Light.gray600,
)

private val darkColor = ProjectColor(
    primary = ProjectColors.Dark.Primary,
    secondary = ProjectColors.Dark.Secondary,
    tertiary = ProjectColors.Dark.Tertiary,
    warning = ProjectColors.Dark.Warning,
    alarm = ProjectColors.Dark.Alarm,
    success = ProjectColors.Dark.Success,
    caution = ProjectColors.Dark.Caution,
    disable = ProjectColors.Dark.Caution,
    white = ProjectColors.Dark.white,
    black = ProjectColors.Dark.black,
    gray600 = ProjectColors.Dark.gray600,
)

private val typograpy = ProjectTypography(
    xxxl = ProjectTextStyles.XXXL,
    xxl = ProjectTextStyles.XXL,
    xl = ProjectTextStyles.XL,
    l = ProjectTextStyles.L,
    m = ProjectTextStyles.M,
    s = ProjectTextStyles.S,
    xs = ProjectTextStyles.XS,
    xxs = ProjectTextStyles.XXS,
)

private val space = ProjectSpace(
    space0 = ProjectSpaces.Space0,
    space1 = ProjectSpaces.Space1,
    space2 = ProjectSpaces.Space2,
    space3 = ProjectSpaces.Space3,
    space4 = ProjectSpaces.Space4,
    space5 = ProjectSpaces.Space5,
    space6 = ProjectSpaces.Space6,
    space7 = ProjectSpaces.Space7,
    space8 = ProjectSpaces.Space8,
    space9 = ProjectSpaces.Space9,
    space10 = ProjectSpaces.Space10,
    space11 = ProjectSpaces.Space11,
    space12 = ProjectSpaces.Space12,
)

private val shape = ProjectShape(
    bottomSheet = ProjectShapes.BottomSheet,
    button = ProjectShapes.Button,
    dialog = ProjectShapes.Dialog,
    snackBar = ProjectShapes.SnackBar,
    textField =  ProjectShapes.TextField,
    box = ProjectShapes.Box
)


@Composable
fun ProjectTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val color = if (isDarkTheme) darkColor else lightColor

    CompositionLocalProvider(
        LocalColor provides color,
        LocalTypography provides typograpy,
        LocalSpace provides space,
        LocalShape provides shape,
        content = content
    )
}

object ProjectTheme {
    val color: ProjectColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current

    val shape: ProjectShape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current

    val space: ProjectSpace
        @Composable
        @ReadOnlyComposable
        get() = LocalSpace.current

    val typography: ProjectTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}