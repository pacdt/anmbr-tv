package com.anmfire.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anmfire.tv.common.DeviceUtils
import com.anmfire.tv.ui.components.AppBottomNavigation
import com.anmfire.tv.ui.components.Sidebar
import com.anmfire.tv.ui.theme.AnmFireTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.anmfire.tv.common.UpdateManager
import com.anmfire.tv.common.UpdateStatus
import com.anmfire.tv.ui.components.UpdateDialog
import androidx.core.content.pm.PackageInfoCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnmFireTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    
                    // Update Check
                    val updateManager = remember { UpdateManager(context) }
                    val updateStatus by updateManager.status.collectAsState()
                    
                    LaunchedEffect(Unit) {
                        try {
                            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                            val version = PackageInfoCompat.getLongVersionCode(pInfo).toInt()
                            updateManager.checkForUpdate(version)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    
                    UpdateDialog(
                        status = updateStatus,
                        onUpdateClick = {
                            if (updateStatus is UpdateStatus.Available) {
                                val url = (updateStatus as UpdateStatus.Available).version.apkUrl
                                updateManager.startDownload(url)
                            }
                        },
                        onDismiss = {
                            // Optional: Hide dialog explicitly or handle dismissal
                        }
                    )

                    val isTv = remember { DeviceUtils.isTv(context) }
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination?.route

                    val showNavigation = currentDestination?.startsWith("player") != true

                    if (isTv) {
                        // TV Layout: Sidebar on the left
                        Row(modifier = Modifier.fillMaxSize()) {
                            if (showNavigation) {
                                Sidebar(
                                    currentDestination = currentDestination ?: "home",
                                    onNavigate = { route ->
                                        navController.navigate(route) {
                                            popUpTo("home") { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }

                            AppNavigation(
                                navController = navController,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    } else {
                        // Mobile Layout: Bottom Navigation
                        Scaffold(
                            bottomBar = {
                                if (showNavigation) {
                                    AppBottomNavigation(
                                        currentDestination = currentDestination ?: "home",
                                        onNavigate = { route ->
                                            navController.navigate(route) {
                                                popUpTo("home") { saveState = true }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        ) { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                AppNavigation(
                                    navController = navController,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
