package com.idfinance.debugview.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.idfinance.debugview.presentation.decompose.DebugComponent

@Composable
internal fun LogView(component: DebugComponent) {
    val model by component.model.subscribeAsState()

    LazyColumn {
        items(model.logs) {
            Text(it.message)
        }
    }
}