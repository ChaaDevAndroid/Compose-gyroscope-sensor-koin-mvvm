package com.chaaba.composegyroscopesensorkoinmvvm.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.chaaba.composegyroscopesensorkoinmvvm.ui.GyroscopeScreen
import com.chaaba.composegyroscopesensorkoinmvvm.ui.theme.ComposeGyroscopeSensorKoinMvvmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeGyroscopeSensorKoinMvvmTheme {
                    GyroscopeScreen()
            }
        }
    }
}
