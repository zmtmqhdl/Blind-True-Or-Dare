package com.example.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val lightColor = MyWorldColor(
    primary = MyWorldColors.Light.Primary,
    secondary = MyWorldColors.Light.Secondary,
    tertiary = MyWorldColors.Light.Tertiary,
    warning = MyWorldColors.Light.Warning,
    alarm = MyWorldColors.Light.Alarm,
    success = MyWorldColors.Light.Success,
    caution = MyWorldColors.Light.Caution,
    disable = MyWorldColors.Light.Caution,
    white = MyWorldColors.Light.white,
    black = MyWorldColors.Light.black,
    gray600 = MyWorldColors.Light.gray600,
)

private val darkColor = MyWorldColor(
    primary = MyWorldColors.Dark.Primary,
    secondary = MyWorldColors.Dark.Secondary,
    tertiary = MyWorldColors.Dark.Tertiary,
    warning = MyWorldColors.Dark.Warning,
    alarm = MyWorldColors.Dark.Alarm,
    success = MyWorldColors.Dark.Success,
    caution = MyWorldColors.Dark.Caution,
    disable = MyWorldColors.Dark.Caution,
    white = MyWorldColors.Dark.white,
    black = MyWorldColors.Dark.black,
    gray600 = MyWorldColors.Dark.gray600,
)

private val typograpy = MyWorldTypography(
    xxxl = MyWorldTextStyles.XXXL,
    xxl = MyWorldTextStyles.XXL,
    xl = MyWorldTextStyles.XL,
    l = MyWorldTextStyles.L,
    m = MyWorldTextStyles.M,
    s = MyWorldTextStyles.S,
    xs = MyWorldTextStyles.XS,
    xxs = MyWorldTextStyles.XXS,
)

private val space = MyWorldSpace(
    space0 = MyWorldSpaces.Space0,
    space1 = MyWorldSpaces.Space1,
    space2 = MyWorldSpaces.Space2,
    space3 = MyWorldSpaces.Space3,
    space4 = MyWorldSpaces.Space4,
    space5 = MyWorldSpaces.Space5,
    space6 = MyWorldSpaces.Space6,
    space7 = MyWorldSpaces.Space7,
    space8 = MyWorldSpaces.Space8,
    space9 = MyWorldSpaces.Space9,
    space10 = MyWorldSpaces.Space10,
    space11 = MyWorldSpaces.Space11,
    space12 = MyWorldSpaces.Space12,
)

private val shape = MyWorldShape(
    bottomSheet = MyWorldShapes.BottomSheet,
    button = MyWorldShapes.Button,
    dialog = MyWorldShapes.Dialog,
    snackBar = MyWorldShapes.SnackBar,
    textField =  MyWorldShapes.TextField
)


@Composable
fun MyWorldTheme(
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

object MyWorldTheme {
    val color: MyWorldColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current

    val shape: MyWorldShape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current

    val space: MyWorldSpace
        @Composable
        @ReadOnlyComposable
        get() = LocalSpace.current

    val typography: MyWorldTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}