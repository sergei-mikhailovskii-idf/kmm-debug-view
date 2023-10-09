package com.idfinance.debugview.data.repository

import com.idfinance.debugview.domain.LogType
import com.idfinance.debugview.domain.repository.LogRepository

internal class LogRepository : LogRepository {
    override suspend fun saveLog(type: LogType, message: String) {

    }
}