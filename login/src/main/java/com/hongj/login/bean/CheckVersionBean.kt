package com.hongj.login.bean

import com.google.gson.annotations.SerializedName

data class CheckVersionBean(
        @SerializedName("message") val message: String,
        @SerializedName("isAlert") val isAlert: Int,
        @SerializedName("isPush") val isPush: Int,
        @SerializedName("url") val url: String
)