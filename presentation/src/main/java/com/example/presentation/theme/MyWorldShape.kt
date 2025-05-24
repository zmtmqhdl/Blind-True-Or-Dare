package com.example.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

object MyWorldShapes{
    val BottomSheet = RoundedCornerShape(MyWorldSpaces.Space4)
    val Button = RoundedCornerShape(MyWorldSpaces.Space2)
    val Dialog = RoundedCornerShape(MyWorldSpaces.Space2)
    val SnackBar = RoundedCornerShape(MyWorldSpaces.Space2)
    val TextField = RoundedCornerShape(MyWorldSpaces.Space4)
    val Box = RoundedCornerShape(MyWorldSpaces.Space2)
}

@Stable
class MyWorldShape(
    val button: Shape,
    val bottomSheet: Shape,
    val dialog: Shape,
    val snackBar: Shape,
    val textField: Shape,
    val box: Shape
)

val LocalShape = staticCompositionLocalOf {
    MyWorldShape(
        bottomSheet = RectangleShape,
        button = RectangleShape,
        dialog = RectangleShape,
        snackBar = RectangleShape,
        textField = RectangleShape,
        box = RectangleShape
    )
}