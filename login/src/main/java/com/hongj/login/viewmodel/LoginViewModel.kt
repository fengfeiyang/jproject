package com.hongj.login.viewmodel

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.snackbar.Snackbar
import com.hongj.common.base.BaseViewModel


/**
 * Created by hongj on 2018/8/21 0021.
 */
class LoginViewModel(context: Context) : BaseViewModel(context) {

    var usePwdLogin: ObservableBoolean = ObservableBoolean(true)
    var phone: ObservableField<String> = ObservableField("")
    var verifyCode: ObservableField<String> = ObservableField("")
    var pwd: ObservableField<String> = ObservableField("")
    override fun onCreate() {
        super.onCreate()
        titleName = ObservableField("登录")
    }

    fun checkLoginType() {
        usePwdLogin.set(!usePwdLogin.get())
    }
    
    fun login() {
        if(usePwdLogin.get()){
            Toast.makeText(context,"登录登录",Toast.LENGTH_SHORT).show()
        } else {
            ARouter.getInstance().build("/login/setPassword").withString("phone",phone.get()).navigation()
        }
    }
    
    fun setPwd(){
        ARouter.getInstance().build("/login/setPassword").navigation(context)
    }
}