package com.example.presentation.Icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val Back: ImageVector = ImageVector.Builder(
    defaultWidth = 16.dp,
    defaultHeight = 16.dp,
    viewportWidth = 960f,
    viewportHeight = 960f
).apply {
    path(
        fill = SolidColor(Color.Black),
    ) {
        moveTo(330f, 516f)
        lineTo(531f, 717f)
        lineTo(480f, 768f)
        lineTo(192f, 480f)
        lineTo(480f, 192f)
        lineTo(531f, 243f)
        lineTo(330f, 444f)
        lineTo(768f, 444f)
        lineTo(768f, 516f)
        lineTo(330f, 516f)
        close()
    }
}.build()

@Preview(showBackground = true)
@Composable
private fun BackPreview() {
    Icon(
        imageVector = Back,
        contentDescription = null,
    )
}