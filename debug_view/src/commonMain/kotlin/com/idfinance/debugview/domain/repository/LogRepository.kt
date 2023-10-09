package com.idfinance.debugview.domain.repository

import com.idfinance.debugview.data.model.Log
import com.idfinance.debugview.domain.LogType
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

internal interface LogRepository {
    suspend fun saveLog(type: LogType, message: String)
    fun readLogs(): Flow<ResultsChange<Log>>
}