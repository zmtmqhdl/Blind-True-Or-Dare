package com.example.presentation.component.Icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val Close: ImageVector = ImageVector.Builder(
    defaultWidth = 16.dp,
    defaultHeight = 16.dp,
    viewportWidth = 960f,
    viewportHeight = 960f
).apply {
    path(
        fill = SolidColor(Color.Black),
        fillAlpha = 1.0f,
        stroke = null,
        strokeAlpha = 1.0f,
        strokeLineWidth = 1.0f,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Miter,
        strokeLineMiter = 1.0f
    ) {
        moveTo(256f, 760f)
        lineTo(200f, 704f)
        lineTo(424f, 480f)
        lineTo(200f, 256f)
        lineTo(256f, 200f)
        lineTo(480f, 424f)
        lineTo(704f, 200f)
        lineTo(760f, 256f)
        lineTo(536f, 480f)
        lineTo(760f, 704f)
        lineTo(704f, 760f)
        lineTo(480f, 536f)
        lineTo(256f, 760f)
        close()
    }
}.build()

@Preview(showBackground = true)
@Composable
private fun ClosePreview() {
    Icon(
        imageVector = Close,
        contentDescription = null,
    )
}