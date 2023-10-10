package com.idfinance.debugview.presentation

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.pause
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.idfinance.debugview.ServiceLocator
import com.idfinance.debugview.presentation.decompose.DebugComponent
import com.idfinance.debugview.presentation.ui.LogView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSNotificationCenter
import platform.UIKit.UIApplicationDidBecomeActiveNotification
import platform.UIKit.UIApplicationDidEnterBackgroundNotification
import platform.UIKit.UIApplicationWillResignActiveNotification
import platform.UIKit.UIViewController
import platform.UIKit.addChildViewController
import platform.objc.sel_registerName

private fun DebugViewViewController(component: DebugComponent) = ComposeUIViewController {
    LogView(component)
}

@OptIn(ExperimentalForeignApi::class)
class DebugViewViewController : UIViewController(null, null) {
    private val lifecycle = LifecycleRegistry()
    private val component = ServiceLocator.getRootComponent(DefaultComponentContext(lifecycle))

    init {
        initObservers()
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        addChildViewController(DebugViewViewController(component))
    }

    private fun initObservers() {
        NSNotificationCenter.defaultCenter.addObserver(
            this,
            sel_registerName("onResume"),
            UIApplicationDidBecomeActiveNotification,
            null
        )
        NSNotificationCenter.defaultCenter.addObserver(
            this,
            sel_registerName("onPause"),
            UIApplicationWillResignActiveNotification,
            null
        )
        NSNotificationCenter.defaultCenter.addObserver(
            this,
            sel_registerName("onStop"),
            UIApplicationDidEnterBackgroundNotification,
            null
        )
    }

    private fun onResume() {
        lifecycle.resume()
    }

    private fun onPause() {
        lifecycle.pause()
    }

    private fun onStop() {
        lifecycle.stop()
    }
}