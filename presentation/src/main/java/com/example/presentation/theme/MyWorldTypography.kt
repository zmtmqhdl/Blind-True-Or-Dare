package com.example.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R

@Immutable
data class MyWorldTypographySet(
    val bold: TextStyle,
    val medium: TextStyle,
    val regular: TextStyle,
) {
    companion object {
        val Default = MyWorldTypographySet(
            bold = TextStyle.Default,
            medium = TextStyle.Default,
            regular = TextStyle.Default,
        )
    }
}

object MyWorldFontSize {
    val FontSize1 = 11.sp
    val FontSize2 = 12.sp
    val FontSize3 = 14.sp
    val FontSize4 = 16.sp
    val FontSize5 = 18.sp
    val FontSize6 = 20.sp
    val FontSize7 = 22.sp
    val FontSize8 = 24.sp
}

object MyWorldFontWeight {
    val Bold = FontWeight(700)
    val Medium = FontWeight(500)
    val Regular = FontWeight(400)
}

object MyWorldTextStyles {
    val XXXL = MyWorldTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = MyWorldFontSize.FontSize8,
            fontWeight = MyWorldFontWeight.Bold,
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = MyWorldFontSize.FontSize8,
            fontWeight = MyWorldFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = MyWorldFontSize.FontSize8,
            fontWeight = MyWorldFontWeight.Regular
        )
    )

    val XXL = MyWorldTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = MyWorldFontSize.FontSize7,
            fontWeight = MyWorldFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = MyWorldFontSize.FontSize7,
            fontWeight = MyWorldFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = MyWorldFontSize.FontSize7,
            fontWeight = MyWorldFontWeight.Regular
        )
    )

    val XL = MyWorldTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = MyWorldFontSize.FontSize6,
            fontWeight = MyWorldFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = MyWorldFontSize.FontSize6,
            fontWeight = MyWorldFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = MyWorldFontSize.FontSize6,
            fontWeight = MyWorldFontWeight.Regular
        )
    )

    val L = MyWorldTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = MyWorldFontSize.FontSize5,
            fontWeight = MyWorldFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = MyWorldFontSize.FontSize5,
            fontWeight = MyWorldFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = MyWorldFontSize.FontSize5,
            fontWeight = MyWorldFontWeight.Regular
        )
    )

    val M = MyWorldTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = MyWorldFontSize.FontSize4,
            fontWeight = MyWorldFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = MyWorldFontSize.FontSize4,
            fontWeight = MyWorldFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = MyWorldFontSize.FontSize4,
            fontWeight = MyWorldFontWeight.Regular
        )
    )

    val S = MyWorldTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = MyWorldFontSize.FontSize3,
            fontWeight = MyWorldFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = MyWorldFontSize.FontSize3,
            fontWeight = MyWorldFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = MyWorldFontSize.FontSize3,
            fontWeight = MyWorldFontWeight.Regular
        )
    )

    val XS = MyWorldTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = MyWorldFontSize.FontSize2,
            fontWeight = MyWorldFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = MyWorldFontSize.FontSize2,
            fontWeight = MyWorldFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = MyWorldFontSize.FontSize2,
            fontWeight = MyWorldFontWeight.Regular
        )
    )

    val XXS = MyWorldTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = MyWorldFontSize.FontSize1,
            fontWeight = MyWorldFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = MyWorldFontSize.FontSize1,
            fontWeight = MyWorldFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = MyWorldFontSize.FontSize1,
            fontWeight = MyWorldFontWeight.Regular
        )
    )
}

@Stable
class MyWorldTypography(
    val xxxl: MyWorldTypographySet,
    val xxl: MyWorldTypographySet,
    val xl: MyWorldTypographySet,
    val l: MyWorldTypographySet,
    val m: MyWorldTypographySet,
    val s: MyWorldTypographySet,
    val xs: MyWorldTypographySet,
    val xxs: MyWorldTypographySet,
)

val LocalTypography = staticCompositionLocalOf {
    MyWorldTypography(
        xxxl = MyWorldTypographySet.Default,
        xxl = MyWorldTypographySet.Default,
        xl = MyWorldTypographySet.Default,
        l = MyWorldTypographySet.Default,
        m = MyWorldTypographySet.Default,
        s = MyWorldTypographySet.Default,
        xs = MyWorldTypographySet.Default,
        xxs = MyWorldTypographySet.Default,
    )
}