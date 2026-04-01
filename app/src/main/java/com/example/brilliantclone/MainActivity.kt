package com.example.brilliantclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.brilliantclone.ui.screens.HomeScreen
import com.example.brilliantclone.ui.theme.BrilliantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrilliantTheme {
                HomeScreen()
            }
        }
    }
}
