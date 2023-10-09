package com.idfinance.debugview.presentation.component

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.idfinance.debugview.ServiceLocator
import com.idfinance.debugview.presentation.ui.LogView
import com.idfinance.debugview.presentation.ui.theme.DebugViewTheme

fun openDebugView(context: Context) {
    context.startActivity(Intent(context, DebugViewActivity::class.java).apply {
        flags += Intent.FLAG_ACTIVITY_NEW_TASK
    })
}

class DebugViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = ServiceLocator.getRootComponent(defaultComponentContext())
        setContent {
            DebugViewTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LogView(root)
                }
            }
        }
    }
}