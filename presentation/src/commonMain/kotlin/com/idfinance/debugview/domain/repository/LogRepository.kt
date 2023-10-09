package com.idfinance.debugview.domain.repository

import com.idfinance.debugview.domain.LogType

internal interface LogRepository {
    suspend fun saveLog(type: LogType, message: String)
}