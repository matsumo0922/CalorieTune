package me.matsumo.calorie.tune.feature.home.components

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
internal fun HomeInputSection(
    modifier: Modifier = Modifier,
) {
    val (text, setText) = remember { mutableStateOf(TextFieldState("")) }


}