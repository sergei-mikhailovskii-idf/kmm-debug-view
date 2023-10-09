package com.idfinance.debugview

import com.idfinance.debugview.data.model.Log
import com.idfinance.debugview.domain.repository.LogRepository
import com.idfinance.debugview.domain.usecase.SaveLogUseCase
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

internal object ServiceLocator {
    val saveLogUseCase: SaveLogUseCase
        get() = SaveLogUseCase(repository)

    private val repository: LogRepository by lazy { com.idfinance.debugview.data.repository.LogRepository(realm) }

    private val realm by lazy {
        val config = RealmConfiguration.create(schema = setOf(Log::class))
        Realm.open(config)
    }
}