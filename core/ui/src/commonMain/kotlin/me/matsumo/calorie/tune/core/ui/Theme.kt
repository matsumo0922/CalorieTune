package me.matsumo.calorie.tune.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import me.matsumo.fanbox.core.ui.theme.color.DarkBlueColorScheme
import me.matsumo.fanbox.core.ui.theme.color.LightBlueColorScheme
import me.matsumo.calorie.tune.core.model.Theme

@Composable
fun TranslatorTheme(
    themeConfig: Theme = Theme.SYSTEM,
    content: @Composable () -> Unit,
) {
    val shouldUseDarkTheme = shouldUseDarkTheme(themeConfig)
    val colorScheme = if (shouldUseDarkTheme) DarkBlueColorScheme else LightBlueColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TranslatorTypography,
        shapes = TranslatorShapes,
        content = content,
    )
}

@Composable
fun shouldUseDarkTheme(themeConfig: Theme): Boolean {
    return when (themeConfig) {
        Theme.SYSTEM -> isSystemInDarkTheme()
        Theme.LIGHT -> false
        Theme.DARK -> true
    }
}