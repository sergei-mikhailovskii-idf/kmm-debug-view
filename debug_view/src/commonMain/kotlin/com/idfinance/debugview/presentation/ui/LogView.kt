package com.idfinance.debugview.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.idfinance.debugview.data.model.Log
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
            Text(
                getAttributedLog(it),
                modifier = Modifier.padding(16.dp),
                color = if (it.isError) Color.Red else Color.Black
            )
        }
    }
}

private fun getAttributedLog(log: Log): AnnotatedString {
    val fullMessage = "[${log.tag}] ${log.message}"
    val builder = AnnotatedString.Builder(fullMessage)
    val endTagIndex = fullMessage.indexOfFirst { it == ']' }
    builder.addStyle(SpanStyle(Color.Blue), 0, endTagIndex)
    return builder.toAnnotatedString()
}