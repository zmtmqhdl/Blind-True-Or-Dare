package com.example.core.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ProjectColorSet(
    val fontColor: Color,
    val background: Color,
    val outline: Color
) {
    companion object {
        val Unspecified = ProjectColorSet(
            fontColor = Color.Unspecified,
            background = Color.Unspecified,
            outline = Color.Unspecified
        )
    }
}

val LightPrimaryFontColor = Color(0xFFDBD4BB)
val LightPrimaryBackground = Color(0xFF203427)
val LightPrimaryOutline = Color(0xFFDBD4BB)

val LightSecondaryFontColor = Color(0xFF00C200)
val LightSecondaryBackground = Color(0xFFA4F29F)
val LightSecondaryOutline = Color(0xFFE3FAE1)

val LightTertiaryFontColor = Color(0xFF6A1B9A)
val LightTertiaryBackground = Color(0xFFF1C6FF)
val LightTertiaryOutline = Color(0xFFEEA9D3)

val LightWarningFontColor = Color(0xFFED2B2A)
val LightWarningBackground = Color(0xFFFDD8D9)
val LightWarningOutline = Color(0xFFFDF3F3)

val LightAlarmFontColor = Color(0xFF067CFB)
val LightAlarmBackground = Color(0xFFC9E6FD)
val LightAlarmOutline = Color(0xFFECF5FD)

val LightSuccessFontColor = Color(0xFF00C200)
val LightSuccessBackground = Color(0xFFA4F29F)
val LightSuccessOutline = Color(0xFFE3FAE1)

val LightCautionFontColor = Color(0xFFFDD000)
val LightCautionBackground = Color(0xFFFBF6C9)
val LightCautionOutline = Color(0xFFFDFAE3)

val LightDisableFontColor = Color(0xFFFDD000)
val LightDisableBackground = Color(0xFFFBF6C9)
val LightDisableOutline = Color(0xFFFDFAE3)

val LightWhite = Color(0xFFFFFFFF)
val LightBlack = Color(0xFF000000)
val LightGray600 = Color(0xFF757575)
val LightBackground = Color(0xFF203427)
val LightBackgroundElevated = Color(0xFFFFFFFF)
val LightActiveIcon = Color(0xFFB0B9C2)
val LightInactiveIcon = Color(0xFF181F29)
val LightText = Color(0xFF161D27)

val DarkPrimaryFontColor = Color(0xFFE2D9C5)
val DarkPrimaryBackground = Color(0xFF2B3E9B)
val DarkPrimaryOutline = Color(0xFFE2D9C5)

val DarkSecondaryFontColor = Color(0xFF00C200)
val DarkSecondaryBackground = Color(0xFF307D32)
val DarkSecondaryOutline = Color(0xFF4E9F4E)

val DarkTertiaryFontColor = Color(0xFF6A1B9A)
val DarkTertiaryBackground = Color(0xFF9C4F9A)
val DarkTertiaryOutline = Color(0xFF6F3072)

val DarkWarningFontColor = Color(0xFFED2B2A)
val DarkWarningBackground = Color(0xFF9B5D5D)
val DarkWarningOutline = Color(0xFF9F4C4C)

val DarkAlarmFontColor = Color(0xFF067CFB)
val DarkAlarmBackground = Color(0xFF2B3E9B)
val DarkAlarmOutline = Color(0xFF3A5E9F)

val DarkSuccessFontColor = Color(0xFF00C200)
val DarkSuccessBackground = Color(0xFF307D32)
val DarkSuccessOutline = Color(0xFF4E9F4E)

val DarkCautionFontColor = Color(0xFFFDD000)
val DarkCautionBackground = Color(0xFFAB8A2A)
val DarkCautionOutline = Color(0xFFB69F59)

val DarkDisableFontColor = Color(0xFFFDD000)
val DarkDisableBackground = Color(0xFFAB8A2A)
val DarkDisableOutline = Color(0xFFB69F59)

val DarkWhite = Color(0xFFFFFFFF)
val DarkBlack = Color(0xFF000000)
val DarkGray600 = Color(0xFF757575)
val DarkBackground = Color(0xFF121F18)
val DarkBackgroundElevated = Color(0xFF18171C)
val DarkActiveIcon = Color(0xFF63636D)
val DarkInactiveIcon = Color(0xFFFFFFFF)
val DarkText = Color(0xFFFFFFFF)


object ProjectColors{
    object Light {
        val Primary = ProjectColorSet(
            fontColor = LightPrimaryFontColor,
            background = LightPrimaryBackground,
            outline = LightPrimaryOutline
        )
        val Secondary = ProjectColorSet(
            fontColor = LightSecondaryFontColor,
            background = LightSecondaryBackground,
            outline = LightSecondaryOutline
        )
        val Tertiary = ProjectColorSet(
            fontColor = LightTertiaryFontColor,
            background = LightTertiaryBackground,
            outline = LightTertiaryOutline
        )
        val Warning = ProjectColorSet(
            fontColor = LightWarningFontColor,
            background = LightWarningBackground,
            outline = LightWarningOutline
        )
        val Alarm = ProjectColorSet(
            fontColor = LightAlarmFontColor,
            background = LightAlarmBackground,
            outline = LightAlarmOutline
        )
        val Success = ProjectColorSet(
            fontColor = LightSuccessFontColor,
            background = LightSuccessBackground,
            outline = LightSuccessOutline
        )
        val Caution = ProjectColorSet(
            fontColor = LightCautionFontColor,
            background = LightCautionBackground,
            outline = LightCautionOutline
        )
        val Disable = ProjectColorSet(
            fontColor = LightDisableFontColor,
            background = LightDisableBackground,
            outline = LightDisableOutline
        )
        val white = LightWhite
        val black = LightBlack
        val gray600 = LightGray600
        val background = LightBackground
        val backgroundElevated = LightBackgroundElevated
        val activeIcon = LightActiveIcon
        val inactiveIcon = LightInactiveIcon
        val text = LightText
    }
    object Dark {
        val Primary = ProjectColorSet(
            fontColor = DarkPrimaryFontColor,
            background = DarkPrimaryBackground,
            outline = DarkPrimaryOutline
        )
        val Secondary = ProjectColorSet(
            fontColor = DarkSecondaryFontColor,
            background = DarkSecondaryBackground,
            outline = DarkSecondaryOutline
        )
        val Tertiary = ProjectColorSet(
            fontColor = DarkTertiaryFontColor,
            background = DarkTertiaryBackground,
            outline = DarkTertiaryOutline
        )
        val Warning = ProjectColorSet(
            fontColor = DarkWarningFontColor,
            background = DarkWarningBackground,
            outline = DarkWarningOutline
        )
        val Alarm = ProjectColorSet(
            fontColor = DarkAlarmFontColor,
            background = DarkAlarmBackground,
            outline = DarkAlarmOutline
        )
        val Success = ProjectColorSet(
            fontColor = DarkSuccessFontColor,
            background = DarkSuccessBackground,
            outline = DarkSuccessOutline
        )
        val Caution = ProjectColorSet(
            fontColor = DarkCautionFontColor,
            background = DarkCautionBackground,
            outline = DarkCautionOutline
        )
        val Disable = ProjectColorSet(
            fontColor = DarkDisableFontColor,
            background = DarkDisableBackground,
            outline = DarkDisableOutline
        )
        val white = DarkWhite
        val black = DarkBlack
        val gray600 = DarkGray600
        val background = DarkBackground
        val backgroundElevated = DarkBackgroundElevated
        val activeIcon = DarkActiveIcon
        val inactiveIcon = DarkInactiveIcon
        val text = DarkText
    }
}

@Stable
class ProjectColor(
    val primary: ProjectColorSet,
    val secondary: ProjectColorSet,
    val tertiary: ProjectColorSet,
    val warning: ProjectColorSet,
    val alarm: ProjectColorSet,
    val success: ProjectColorSet,
    val caution: ProjectColorSet,
    val disable: ProjectColorSet,
    val white: Color,
    val black: Color,
    val gray600: Color,
    val background: Color,
    val backgroundElevated: Color,
    val activeIcon: Color,
    val inactiveIcon: Color,
    val text: Color
)

val LocalColor = staticCompositionLocalOf {
    ProjectColor(
        primary = ProjectColorSet.Unspecified,
        secondary = ProjectColorSet.Unspecified,
        tertiary = ProjectColorSet.Unspecified,
        warning = ProjectColorSet.Unspecified,
        alarm = ProjectColorSet.Unspecified,
        success = ProjectColorSet.Unspecified,
        caution = ProjectColorSet.Unspecified,
        disable = ProjectColorSet.Unspecified,
        white = Color.Unspecified,
        black = Color.Unspecified,
        gray600 = Color.Unspecified,
        background = Color.Unspecified,
        backgroundElevated = Color.Unspecified,
        activeIcon = Color.Unspecified,
        inactiveIcon = Color.Unspecified,
        text = Color.Unspecified
    )
}