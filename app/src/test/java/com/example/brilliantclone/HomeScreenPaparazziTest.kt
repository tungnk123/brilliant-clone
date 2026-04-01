package com.example.brilliantclone

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.example.brilliantclone.ui.screens.HomeScreen
import com.example.brilliantclone.ui.theme.BrilliantTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6,
        theme = "android:Theme.Material.Light.NoActionBar"
    )

    @Test
    fun homeScreen() {
        paparazzi.snapshot {
            BrilliantTheme {
                HomeScreen()
            }
        }
    }

    @Test
    fun homeScreen_darkMode() {
        paparazzi.snapshot {
            BrilliantTheme {
                HomeScreen()
            }
        }
    }
}
