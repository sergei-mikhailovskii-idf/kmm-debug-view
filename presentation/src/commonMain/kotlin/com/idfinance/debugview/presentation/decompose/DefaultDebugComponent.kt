package com.idfinance.debugview.presentation.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnStart
import com.arkivanov.essenty.lifecycle.doOnStop

class DefaultDebugComponent(
    context: ComponentContext
) : DebugComponent, ComponentContext by context {

    init {
        lifecycle.doOnStart {
            println("lifecycle.doOnStart")
        }
        lifecycle.doOnStop {
            println("lifecycle.doOnStop")
        }
    }
}