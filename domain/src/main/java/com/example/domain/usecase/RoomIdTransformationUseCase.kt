package com.example.domain.usecase

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class RoomIdTransformationUseCase: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(12)
        val out = buildString {
            trimmed.forEachIndexed { index, c ->
                append(c)
                if ((index + 1) % 4 == 0 && index != trimmed.lastIndex) {
                    append('-')
                }
            }
        }

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val dashCount = (offset / 4).coerceAtMost(2)
                return (offset + dashCount).coerceAtMost(out.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                val dashCount = (offset / 5).coerceAtMost(2)
                return (offset - dashCount).coerceAtMost(trimmed.length)
            }
        }

        return TransformedText(AnnotatedString(out), offsetTranslator)
    }
}
