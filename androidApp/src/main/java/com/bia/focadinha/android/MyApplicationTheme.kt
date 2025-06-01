package com.bia.focadinha.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFF967AA1),
            onPrimary = Color(0xFFF5E6E8),
            secondary = Color(0xFFAAA1C8),
            background = Color(0xFF192A51),
            surface = Color(0xFF2A3550),
            onBackground = Color(0xFFD5C6E0),
            onSurface = Color(0xFFF5E6E8)
        )
    } else {
        lightColorScheme(
            primary = Color(0xFFAAA1C8),
            onPrimary = Color(0xFF192A51),
            secondary = Color(0xFF967AA1),
            background = Color(0xFFFFFFFF),
            surface = Color(0xFFD5C6E0),
            onBackground = Color(0xFF192A51),
            onSurface = Color(0xFF192A51)
        )
    }

    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(16.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
