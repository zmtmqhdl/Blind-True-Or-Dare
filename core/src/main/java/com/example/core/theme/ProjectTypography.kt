package com.example.core.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.core.R

@Immutable
data class ProjectTypographySet(
    val bold: TextStyle,
    val medium: TextStyle,
    val regular: TextStyle,
) {
    companion object {
        val Default = ProjectTypographySet(
            bold = TextStyle.Default,
            medium = TextStyle.Default,
            regular = TextStyle.Default,
        )
    }
}

object ProjectFontSize {
    val FontSize1 = 11.sp
    val FontSize2 = 12.sp
    val FontSize3 = 14.sp
    val FontSize4 = 16.sp
    val FontSize5 = 18.sp
    val FontSize6 = 20.sp
    val FontSize7 = 22.sp
    val FontSize8 = 24.sp
}

object ProjectFontWeight {
    val Bold = FontWeight(700)
    val Medium = FontWeight(500)
    val Regular = FontWeight(400)
}

object ProjectTextStyles {
    val XXXL = ProjectTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = ProjectFontSize.FontSize8,
            fontWeight = ProjectFontWeight.Bold,
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = ProjectFontSize.FontSize8,
            fontWeight = ProjectFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = ProjectFontSize.FontSize8,
            fontWeight = ProjectFontWeight.Regular
        )
    )

    val XXL = ProjectTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = ProjectFontSize.FontSize7,
            fontWeight = ProjectFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = ProjectFontSize.FontSize7,
            fontWeight = ProjectFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = ProjectFontSize.FontSize7,
            fontWeight = ProjectFontWeight.Regular
        )
    )

    val XL = ProjectTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = ProjectFontSize.FontSize6,
            fontWeight = ProjectFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = ProjectFontSize.FontSize6,
            fontWeight = ProjectFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = ProjectFontSize.FontSize6,
            fontWeight = ProjectFontWeight.Regular
        )
    )

    val L = ProjectTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = ProjectFontSize.FontSize5,
            fontWeight = ProjectFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = ProjectFontSize.FontSize5,
            fontWeight = ProjectFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = ProjectFontSize.FontSize5,
            fontWeight = ProjectFontWeight.Regular
        )
    )

    val M = ProjectTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = ProjectFontSize.FontSize4,
            fontWeight = ProjectFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = ProjectFontSize.FontSize4,
            fontWeight = ProjectFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = ProjectFontSize.FontSize4,
            fontWeight = ProjectFontWeight.Regular
        )
    )

    val S = ProjectTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = ProjectFontSize.FontSize3,
            fontWeight = ProjectFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = ProjectFontSize.FontSize3,
            fontWeight = ProjectFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = ProjectFontSize.FontSize3,
            fontWeight = ProjectFontWeight.Regular
        )
    )

    val XS = ProjectTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = ProjectFontSize.FontSize2,
            fontWeight = ProjectFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = ProjectFontSize.FontSize2,
            fontWeight = ProjectFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = ProjectFontSize.FontSize2,
            fontWeight = ProjectFontWeight.Regular
        )
    )

    val XXS = ProjectTypographySet(
        bold = TextStyle(
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = ProjectFontSize.FontSize1,
            fontWeight = ProjectFontWeight.Bold
        ),
        medium = TextStyle(
            fontFamily = FontFamily(Font(R.font.medium)),
            fontSize = ProjectFontSize.FontSize1,
            fontWeight = ProjectFontWeight.Medium
        ),
        regular = TextStyle(
            fontFamily = FontFamily(Font(R.font.regular)),
            fontSize = ProjectFontSize.FontSize1,
            fontWeight = ProjectFontWeight.Regular
        )
    )
}

@Stable
class ProjectTypography(
    val xxxl: ProjectTypographySet,
    val xxl: ProjectTypographySet,
    val xl: ProjectTypographySet,
    val l: ProjectTypographySet,
    val m: ProjectTypographySet,
    val s: ProjectTypographySet,
    val xs: ProjectTypographySet,
    val xxs: ProjectTypographySet,
)

val LocalTypography = staticCompositionLocalOf {
    ProjectTypography(
        xxxl = ProjectTypographySet.Default,
        xxl = ProjectTypographySet.Default,
        xl = ProjectTypographySet.Default,
        l = ProjectTypographySet.Default,
        m = ProjectTypographySet.Default,
        s = ProjectTypographySet.Default,
        xs = ProjectTypographySet.Default,
        xxs = ProjectTypographySet.Default,
    )
}