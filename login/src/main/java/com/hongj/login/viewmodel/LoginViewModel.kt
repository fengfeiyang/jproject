package com.hongj.login.viewmodel

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.hongj.common.base.BaseViewModel

/**
 * Created by hongj on 2018/8/21 0021.
 */
class LoginViewModel(context: Context) : BaseViewModel(context) {

    var isLogin: ObservableBoolean = ObservableBoolean(false)
    override fun onCreate() {
        super.onCreate()
        titleName = ObservableField("登录")
    }

    fun checkPwdType() {
        isLogin.set(!isLogin.get())
    }


}