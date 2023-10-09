package com.idfinance.debugview.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.idfinance.debugview.presentation.decompose.DefaultDebugComponent

@Composable
fun DebugViewTheme(
    component: DefaultDebugComponent,
    content: @Composable () -> Unit
) {
    MaterialTheme(content = content)
}