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

val Person: ImageVector = ImageVector.Builder(
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
        moveTo(480f, 480f)
        quadTo(414f, 480f, 367f, 433f)
        quadTo(320f, 386f, 320f, 320f)
        quadTo(320f, 254f, 367f, 207f)
        quadTo(414f, 160f, 480f, 160f)
        quadTo(546f, 160f, 593f, 207f)
        quadTo(640f, 254f, 640f, 320f)
        quadTo(640f, 386f, 593f, 433f)
        quadTo(546f, 480f, 480f, 480f)
        close()

        moveTo(160f, 800f)
        lineTo(160f, 688f)
        quadTo(160f, 654f, 177.5f, 625.5f)
        quadTo(195f, 597f, 224f, 582f)
        quadTo(286f, 551f, 350f, 535.5f)
        quadTo(414f, 520f, 480f, 520f)
        quadTo(546f, 520f, 610f, 535.5f)
        quadTo(674f, 551f, 736f, 582f)
        quadTo(765f, 597f, 782.5f, 625.5f)
        quadTo(800f, 654f, 800f, 688f)
        lineTo(800f, 800f)
        lineTo(160f, 800f)
        close()

        moveTo(240f, 720f)
        lineTo(720f, 720f)
        lineTo(720f, 688f)
        quadTo(720f, 677f, 714.5f, 668f)
        quadTo(709f, 659f, 700f, 654f)
        quadTo(646f, 627f, 591f, 613.5f)
        quadTo(536f, 600f, 480f, 600f)
        quadTo(424f, 600f, 369f, 613.5f)
        quadTo(314f, 627f, 260f, 654f)
        quadTo(251f, 659f, 245.5f, 668f)
        quadTo(240f, 677f, 240f, 688f)
        lineTo(240f, 720f)
        close()

        moveTo(480f, 400f)
        quadTo(513f, 400f, 536.5f, 376.5f)
        quadTo(560f, 353f, 560f, 320f)
        quadTo(560f, 287f, 536.5f, 263.5f)
        quadTo(513f, 240f, 480f, 240f)
        quadTo(447f, 240f, 423.5f, 263.5f)
        quadTo(400f, 287f, 400f, 320f)
        quadTo(400f, 353f, 423.5f, 376.5f)
        quadTo(447f, 400f, 480f, 400f)
        close()
    }
}.build()

@Preview(showBackground = true)
@Composable
private fun PersonPreview() {
    Icon(
        imageVector = Person,
        contentDescription = null,
    )
}