package com.hongj.base

import android.app.Application

/**
 * Created by hongj on 2018/8/14 0014.
 */

abstract class BaseApp : Application() {
    /**
     * Application 初始化
     */
    abstract fun initModuleApp(application: Application)

    /**
     * 所有 Application 初始化后的自定义操作
     */
    abstract fun initModuleData(application: Application)

}