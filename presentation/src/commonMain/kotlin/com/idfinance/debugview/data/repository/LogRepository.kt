package com.idfinance.debugview.data.repository

import com.idfinance.debugview.data.model.Log
import com.idfinance.debugview.domain.LogType
import com.idfinance.debugview.domain.repository.LogRepository
import io.realm.kotlin.Realm

internal class LogRepository(
    private val realm: Realm
) : LogRepository {
    override suspend fun saveLog(type: LogType, message: String) {
        realm.write {
            copyToRealm(Log().apply {
                this.type = type
                this.message = message
            })
        }
    }
}