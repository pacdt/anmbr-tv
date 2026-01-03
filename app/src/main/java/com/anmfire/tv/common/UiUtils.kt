package com.anmfire.tv.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object UiUtils {
    @Composable
    fun getScreenPadding(): Dp {
        val context = LocalContext.current
        val isTv = remember { DeviceUtils.isTv(context) }
        return if (isTv) 40.dp else 16.dp
    }
}
