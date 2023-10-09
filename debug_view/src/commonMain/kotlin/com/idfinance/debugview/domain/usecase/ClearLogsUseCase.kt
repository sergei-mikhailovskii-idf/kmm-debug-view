package com.idfinance.debugview.domain.usecase

import com.idfinance.debugview.domain.repository.LogRepository

internal class ClearLogsUseCase(
    private val repository: LogRepository
) {

    suspend operator fun invoke() = repository.clearLogs()
}