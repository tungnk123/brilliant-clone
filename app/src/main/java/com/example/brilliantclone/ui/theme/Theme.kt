package com.example.brilliantclone.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.brilliantclone.R

private val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val plusJakartaSans = GoogleFont("Plus Jakarta Sans")

val PlusJakartaSansFontFamily = FontFamily(
    Font(googleFont = plusJakartaSans, fontProvider = fontProvider, weight = FontWeight.Normal),
    Font(googleFont = plusJakartaSans, fontProvider = fontProvider, weight = FontWeight.Medium),
    Font(googleFont = plusJakartaSans, fontProvider = fontProvider, weight = FontWeight.SemiBold),
    Font(googleFont = plusJakartaSans, fontProvider = fontProvider, weight = FontWeight.Bold),
)

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

private val BrilliantTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = PlusJakartaSansFontFamily,
        fontSize = 32.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontFamily = PlusJakartaSansFontFamily,
        fontSize = 24.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = PlusJakartaSansFontFamily,
        fontSize = 20.sp, fontWeight = FontWeight.SemiBold
    ),
    titleMedium = TextStyle(
        fontFamily = PlusJakartaSansFontFamily,
        fontSize = 16.sp, fontWeight = FontWeight.SemiBold
    ),
    bodyLarge = TextStyle(
        fontFamily = PlusJakartaSansFontFamily,
        fontSize = 16.sp, fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = PlusJakartaSansFontFamily,
        fontSize = 14.sp, fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontFamily = PlusJakartaSansFontFamily,
        fontSize = 12.sp, fontWeight = FontWeight.Normal
    ),
    labelSmall = TextStyle(
        fontFamily = PlusJakartaSansFontFamily,
        fontSize = 11.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.8.sp
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
