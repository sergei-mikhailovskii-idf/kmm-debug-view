package com.idfinance.debugview.presentation.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
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

    private val _model = MutableValue(DebugComponent.Model())
    override val model: Value<DebugComponent.Model> = _model

    init {
        val logsFlow = getLogsFlowUseCase()
        var logsFlowJob: Job? = null
        lifecycle.doOnStart {
            logsFlowJob = launch {
                logsFlow.collectLatest {
                    _model.value = _model.value.copy(logs = it.list)
                }
            }
        }
        lifecycle.doOnStop {
            logsFlowJob?.cancel()
        }
//        launch {
//            val useCase = ServiceLocator.saveLogUseCase
//            for (i in 0..100) {
//                useCase(SaveLogUseCase.Payload(LogType.DEFAULT, "Log$i"))
//                delay(100)
//            }
//        }
    }
}