package com.jasmeet.memesharer.compose.ui

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    background = darkPrimaryBackground,
    primaryContainer = darkUserChatBubble,
    secondaryContainer = darkBotBubbleColor,
    onPrimary = darkPrimaryText,
    errorContainer = darkErrorBubbleColor,
    tertiary = darkButtonColor


)

private val LightColorScheme = lightColorScheme(
    background = lightPrimaryBackground,
    primaryContainer = lightUserChatBubble,
    secondaryContainer = lightBotBubbleColor,
    onPrimary = lightPrimaryText,
    errorContainer = lightErrorBubbleColor,
    tertiary = lightButtonColor,


    )

@Composable
fun MemeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if(!view.isInEditMode){
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}