package com.idfinance.debugview.domain.usecase

import com.idfinance.debugview.domain.LogType
import com.idfinance.debugview.domain.repository.LogRepository

internal class SaveLogUseCase(
    private val repository: LogRepository
) {
    suspend operator fun invoke(payload: Payload) {
        repository.saveLog(payload.type, payload.message)
    }

    class Payload(
        val type: LogType,
        val message: String
    )
}