package com.hongj.login.view

import android.app.Activity
import android.os.Bundle
import com.hongj.login.R
import kotlinx.android.synthetic.main.login_activity_coustom_webview.*

class CoustomWebviewActivity :Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity_coustom_webview)
        login_webview.loadUrl("https://www.baidu.com/")
    }
    
}