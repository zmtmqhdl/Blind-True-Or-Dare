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

val Refresh: ImageVector = ImageVector.Builder(
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
        moveTo(480f, 800f)
        quadTo(346f, 800f, 253f, 707f)
        quadTo(160f, 614f, 160f, 480f)
        quadTo(160f, 346f, 253f, 253f)
        quadTo(346f, 160f, 480f, 160f)
        quadTo(549f, 160f, 612f, 188.5f)
        quadTo(675f, 217f, 720f, 270f)
        lineTo(720f, 160f)
        lineTo(800f, 160f)
        lineTo(800f, 440f)
        lineTo(520f, 440f)
        lineTo(520f, 360f)
        lineTo(688f, 360f)
        quadTo(656f, 304f, 600.5f, 272f)
        quadTo(545f, 240f, 480f, 240f)
        quadTo(380f, 240f, 310f, 310f)
        quadTo(240f, 380f, 240f, 480f)
        quadTo(240f, 580f, 310f, 650f)
        quadTo(380f, 720f, 480f, 720f)
        quadTo(557f, 720f, 619f, 676f)
        quadTo(681f, 632f, 706f, 560f)
        lineTo(790f, 560f)
        quadTo(762f, 666f, 676f, 733f)
        quadTo(590f, 800f, 480f, 800f)
        close()
    }
}.build()

@Preview(showBackground = true)
@Composable
private fun RefreshPreview() {
    Icon(
        imageVector = Refresh,
        contentDescription = null,
    )
}