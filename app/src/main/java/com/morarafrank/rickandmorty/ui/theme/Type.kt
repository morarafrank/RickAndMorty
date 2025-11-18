package com.morarafrank.rickandmorty.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.morarafrank.rickandmorty.R

val regularFont = FontFamily(Font(R.font.dm_sans_regular))
val boldFont = FontFamily(Font(R.font.dm_sans_bold))
val mediumFont = FontFamily(Font(R.font.dm_sans_medium))

val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = regularFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = mediumFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.25.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = boldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)