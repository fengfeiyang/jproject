package com.hongj.common.Constant

/**
 * Created by hongj on 2018/8/15 0015.
 */
object Constants {
    var debug: Boolean = true
    private val DEBUG_BASE_URL: String = "http://mg.valp.io"
    private val RELEASE_BASE_URL: String = "http://mg.valp.io"
    var BASE_URL: String = DEBUG_BASE_URL
    fun getBaseUrl(isDebug: Boolean) {
        debug = isDebug
        BASE_URL = if (isDebug) DEBUG_BASE_URL else RELEASE_BASE_URL
    }
}