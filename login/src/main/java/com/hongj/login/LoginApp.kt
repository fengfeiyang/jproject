package com.hongj.login

import android.app.Application
import com.hongj.base.BaseApp
import com.hongj.componentbase.ServiceFactory

/**
 * Created by hongj on 2018/8/14 0014.
 */

class LoginApp : BaseApp() {


    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {
        ServiceFactory.instance.loginService = LoginService()
    }

    override fun initModuleData(application: Application) {

    }
}