package com.idfinance.debugview

import com.idfinance.debugview.domain.repository.LogRepository
import com.idfinance.debugview.domain.usecase.SaveLogUseCase

internal object ServiceLocator {
    val repository: LogRepository = com.idfinance.debugview.data.repository.LogRepository()
    val saveLogUseCase: SaveLogUseCase
        get() = SaveLogUseCase(repository)
}