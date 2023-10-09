package com.idfinance.debugview

import com.arkivanov.decompose.ComponentContext
import com.idfinance.debugview.data.model.Log
import com.idfinance.debugview.domain.repository.LogRepository
import com.idfinance.debugview.domain.usecase.ClearLogsUseCase
import com.idfinance.debugview.domain.usecase.GetLogsFlowUseCase
import com.idfinance.debugview.domain.usecase.SaveLogUseCase
import com.idfinance.debugview.presentation.decompose.DefaultDebugComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

internal object ServiceLocator {

    val saveLogUseCase: SaveLogUseCase
        get() = SaveLogUseCase(repository)

    fun getRootComponent(context: ComponentContext) =
        DefaultDebugComponent(context, getLogsFlowUseCase, clearLogsUseCase)

    private val getLogsFlowUseCase: GetLogsFlowUseCase
        get() = GetLogsFlowUseCase(repository)

    private val clearLogsUseCase: ClearLogsUseCase
        get() = ClearLogsUseCase(repository)

    private val repository: LogRepository by lazy { com.idfinance.debugview.data.repository.LogRepository(realm) }

    private val realm by lazy {
        val config = RealmConfiguration.create(schema = setOf(Log::class))
        Realm.open(config)
    }
}