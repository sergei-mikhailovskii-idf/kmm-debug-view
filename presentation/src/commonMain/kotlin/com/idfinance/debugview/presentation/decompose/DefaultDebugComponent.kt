package com.idfinance.debugview.presentation.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnStart
import com.arkivanov.essenty.lifecycle.doOnStop
import com.idfinance.debugview.domain.usecase.GetLogsFlowUseCase
import com.idfinance.debugview.presentation.extensions.disposableScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class DefaultDebugComponent(
    context: ComponentContext,
    getLogsFlowUseCase: GetLogsFlowUseCase
) : DebugComponent, ComponentContext by context,
    CoroutineScope by context.disposableScope() {

    init {
        val logsFlow = getLogsFlowUseCase()
        var logsFlowJob: Job? = null
        lifecycle.doOnStart {
            logsFlowJob = launch {
                logsFlow.collectLatest {
                    println("logs collected")
                }
            }
        }
        lifecycle.doOnStop {
            logsFlowJob?.cancel()
        }
    }
}