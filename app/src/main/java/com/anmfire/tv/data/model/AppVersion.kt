package com.anmfire.tv.data.model

import com.google.gson.annotations.SerializedName

data class AppVersion(
    @SerializedName("versionCode") val versionCode: Int,
    @SerializedName("versionName") val versionName: String,
    @SerializedName("apkUrl") val apkUrl: String,
    @SerializedName("releaseNotes") val releaseNotes: String
)
