package com.example.brilliantclone.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val BrilliantColorScheme = lightColorScheme(
    primary = Purple,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    primaryContainer = PurpleLight,
    secondary = Success,
    background = Background,
    surface = Background,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    outline = Border,
    surfaceVariant = androidx.compose.ui.graphics.Color(0xFFF5F5F5)
)

// Plus Jakarta Sans not bundled by default → using system sans-serif
// In production: add via Google Fonts Compose or custom font files
private val BrilliantTypography = Typography(
    displayLarge = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp),
    displayMedium = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp),
    titleLarge = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
    titleMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
    bodyLarge = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
    bodyMedium = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
    bodySmall = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal),
    labelSmall = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.8.sp
    )
)

@Composable
fun BrilliantTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = BrilliantColorScheme,
        typography = BrilliantTypography,
        content = content
    )
}
