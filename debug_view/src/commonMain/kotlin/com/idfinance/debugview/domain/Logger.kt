package com.idfinance.debugview.domain

import com.idfinance.debugview.ServiceLocator
import com.idfinance.debugview.domain.usecase.SaveLogUseCase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

fun handleLog(type: LogType, tag: String, message: String) {
    val useCase = ServiceLocator.saveLogUseCase
    MainScope().launch {
        useCase(SaveLogUseCase.Payload(type, tag, message))
    }
}

enum class LogType {
    DEFAULT,
    ERROR
}