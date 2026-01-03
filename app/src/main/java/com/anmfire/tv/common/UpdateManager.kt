package com.anmfire.tv.common

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.FileProvider
import com.anmfire.tv.data.api.RetrofitClient
import com.anmfire.tv.data.model.AppVersion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.io.File

sealed class UpdateStatus {
    object Idle : UpdateStatus()
    object Checking : UpdateStatus()
    data class Available(val version: AppVersion) : UpdateStatus()
    object NoUpdate : UpdateStatus()
    data class Error(val message: String) : UpdateStatus()
    data class Downloading(val progress: Int) : UpdateStatus()
    object ReadyToInstall : UpdateStatus()
}

class UpdateManager(private val context: Context) {
    private val _status = MutableStateFlow<UpdateStatus>(UpdateStatus.Idle)
    val status: StateFlow<UpdateStatus> = _status

    private val api = RetrofitClient.api
    private val VERSION_JSON_URL = "https://raw.githubusercontent.com/pacdt/anmbr-tv/main/version.json"
    private var downloadId: Long = -1

    suspend fun checkForUpdate(currentVersionCode: Int) {
        _status.value = UpdateStatus.Checking
        try {
            val remoteVersion = api.checkUpdate(VERSION_JSON_URL)
            if (remoteVersion.versionCode > currentVersionCode) {
                _status.value = UpdateStatus.Available(remoteVersion)
            } else {
                _status.value = UpdateStatus.NoUpdate
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _status.value = UpdateStatus.Error("Falha ao verificar atualizações: ${e.message}")
        }
    }

    fun startDownload(url: String) {
        _status.value = UpdateStatus.Downloading(0)
        
        try {
            val fileName = "update.apk"
            val request = DownloadManager.Request(Uri.parse(url))
                .setTitle("Atualização AnmFire")
                .setDescription("Baixando nova versão...")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)

            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadId = downloadManager.enqueue(request)

            // Start observing download progress
            monitorDownload(downloadManager)
        } catch (e: Exception) {
            e.printStackTrace()
            _status.value = UpdateStatus.Error("Erro ao iniciar download: ${e.message}")
        }
    }

    private fun monitorDownload(downloadManager: DownloadManager) {
        val query = DownloadManager.Query().setFilterById(downloadId)
        
        Thread {
            var downloading = true
            while (downloading) {
                val cursor = downloadManager.query(query)
                if (cursor.moveToFirst()) {
                    val statusColumn = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                    val status = cursor.getInt(statusColumn)
                    
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        downloading = false
                        _status.value = UpdateStatus.ReadyToInstall
                        installApk()
                    } else if (status == DownloadManager.STATUS_FAILED) {
                        downloading = false
                        _status.value = UpdateStatus.Error("Download falhou")
                    } else {
                        val bytesDownloadedIndex = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                        val bytesTotalIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)
                        
                        if (bytesDownloadedIndex != -1 && bytesTotalIndex != -1) {
                            val bytesDownloaded = cursor.getInt(bytesDownloadedIndex)
                            val bytesTotal = cursor.getInt(bytesTotalIndex)
                            
                            if (bytesTotal > 0) {
                                val progress = ((bytesDownloaded * 100L) / bytesTotal).toInt()
                                _status.value = UpdateStatus.Downloading(progress)
                            }
                        }
                    }
                }
                cursor.close()
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    downloading = false
                }
            }
        }.start()
    }

    private fun installApk() {
        try {
            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "update.apk")
            if (file.exists()) {
                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    file
                )
                
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uri, "application/vnd.android.package-archive")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
                }
                
                context.startActivity(intent)
            } else {
                _status.value = UpdateStatus.Error("Arquivo de atualização não encontrado")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _status.value = UpdateStatus.Error("Erro ao instalar: ${e.message}")
        }
    }
}
