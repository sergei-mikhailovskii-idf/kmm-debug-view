package com.idfinance.debugview.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.idfinance.debugview.presentation.decompose.DebugComponent

@Composable
internal fun LogView(component: DebugComponent) {
    val model by component.model.subscribeAsState()
    val state = rememberLazyListState()

    LaunchedEffect(model.logs.size) {
        state.animateScrollToItem(maxOf(model.logs.size - 1, 0))
    }

    LazyColumn(state = state) {
        items(model.logs) {
            Text(it.message, color = if (it.isError) Color.Red else Color.Black)
        }
    }
}