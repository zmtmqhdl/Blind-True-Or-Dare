package com.example.presentation.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ProjectSpaces{
    val Space0 = 2.dp
    val Space1 = 4.dp
    val Space2 = 8.dp
    val Space3 = 12.dp
    val Space4 = 16.dp
    val Space5 = 20.dp
    val Space6 = 24.dp
    val Space7 = 28.dp
    val Space8 = 32.dp
    val Space9 = 36.dp
    val Space10 = 40.dp
    val Space11 = 44.dp
    val Space12 = 48.dp
}

@Stable
class ProjectSpace(
    val space0: Dp,
    val space1: Dp,
    val space2: Dp,
    val space3: Dp,
    val space4: Dp,
    val space5: Dp,
    val space6: Dp,
    val space7: Dp,
    val space8: Dp,
    val space9: Dp,
    val space10: Dp,
    val space11: Dp,
    val space12: Dp,
)

val LocalSpace = staticCompositionLocalOf {
    ProjectSpace(
        space0 = Dp.Unspecified,
        space1 = Dp.Unspecified,
        space2 = Dp.Unspecified,
        space3 = Dp.Unspecified,
        space4 = Dp.Unspecified,
        space5 = Dp.Unspecified,
        space6 = Dp.Unspecified,
        space7 = Dp.Unspecified,
        space8 = Dp.Unspecified,
        space9 = Dp.Unspecified,
        space10 = Dp.Unspecified,
        space11 = Dp.Unspecified,
        space12 = Dp.Unspecified,
    )
}