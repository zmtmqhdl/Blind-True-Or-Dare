package com.example.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

object ProjectShapes{
    val BottomSheet = RoundedCornerShape(ProjectSpaces.Space4)
    val Button = RoundedCornerShape(ProjectSpaces.Space2)
    val Dialog = RoundedCornerShape(ProjectSpaces.Space2)
    val SnackBar = RoundedCornerShape(ProjectSpaces.Space2)
    val TextField = RoundedCornerShape(ProjectSpaces.Space4)
    val Box = RoundedCornerShape(ProjectSpaces.Space2)
}

@Stable
class ProjectShape(
    val button: Shape,
    val bottomSheet: Shape,
    val dialog: Shape,
    val snackBar: Shape,
    val textField: Shape,
    val box: Shape
)

val LocalShape = staticCompositionLocalOf {
    ProjectShape(
        bottomSheet = RectangleShape,
        button = RectangleShape,
        dialog = RectangleShape,
        snackBar = RectangleShape,
        textField = RectangleShape,
        box = RectangleShape
    )
}