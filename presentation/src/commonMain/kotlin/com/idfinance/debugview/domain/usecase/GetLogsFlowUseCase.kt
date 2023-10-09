package com.idfinance.debugview.domain.usecase

import com.idfinance.debugview.domain.repository.LogRepository

internal class GetLogsFlowUseCase(
    private val repository: LogRepository
) {

    operator fun invoke() = repository.readLogs()
}