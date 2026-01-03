package com.anmfire.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.anmfire.tv.common.UpdateStatus
import com.anmfire.tv.ui.theme.BrandAccent
import com.anmfire.tv.ui.theme.Neutral800
import com.anmfire.tv.ui.theme.Neutral950

@Composable
fun UpdateDialog(
    status: UpdateStatus,
    onUpdateClick: () -> Unit,
    onDismiss: () -> Unit
) {
    if (status is UpdateStatus.Available || status is UpdateStatus.Downloading || status is UpdateStatus.Error || status is UpdateStatus.ReadyToInstall) {
        Dialog(onDismissRequest = { 
            if (status !is UpdateStatus.Downloading && status !is UpdateStatus.ReadyToInstall) {
                onDismiss()
            }
        }) {
            Box(
                modifier = Modifier
                    .width(400.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Neutral950)
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Atualização Disponível",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    when (status) {
                        is UpdateStatus.Available -> {
                            Text(
                                text = "Nova versão: ${status.version.versionName}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = status.version.releaseNotes,
                                color = Color.LightGray,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(
                                    onClick = onDismiss,
                                    colors = ButtonDefaults.buttonColors(containerColor = Neutral800)
                                ) {
                                    Text("Depois", color = Color.White)
                                }
                                Button(
                                    onClick = onUpdateClick,
                                    colors = ButtonDefaults.buttonColors(containerColor = BrandAccent)
                                ) {
                                    Text("Atualizar Agora", color = Color.White)
                                }
                            }
                        }
                        is UpdateStatus.Downloading -> {
                            Text(
                                text = "Baixando atualização...",
                                color = Color.LightGray
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            LinearProgressIndicator(
                                progress = { status.progress / 100f },
                                modifier = Modifier.fillMaxWidth(),
                                color = BrandAccent,
                                trackColor = Neutral800
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "${status.progress}%",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        is UpdateStatus.ReadyToInstall -> {
                            Text(
                                text = "Download concluído! Instalando...",
                                color = BrandAccent
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            CircularProgressIndicator(color = BrandAccent)
                        }
                        is UpdateStatus.Error -> {
                            Text(
                                text = status.message,
                                color = Color.Red
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Button(
                                onClick = onDismiss,
                                colors = ButtonDefaults.buttonColors(containerColor = Neutral800)
                            ) {
                                Text("Fechar", color = Color.White)
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}
