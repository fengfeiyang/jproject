package com.hongj.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.hongj.base.AppConfig
import com.hongj.base.BaseApp
import com.hongj.common.Constant.Constants
import com.hongj.app.BuildConfig

/**
 * Created by hongj on 2018/8/14 0014.
 */
class MainApplication : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        val debug = !BuildConfig.ENVIRONMENT
        Constants.getBaseUrl(debug)

        // 初始化 ARouter
        if (debug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)

        initModuleApp(this)
        initModuleData(this)
    }


    override fun initModuleApp(application: Application) {
        for (moduleApp in AppConfig.moduleApps) {
            try {
                val clazz = Class.forName(moduleApp)
                val baseApp = clazz.newInstance() as BaseApp
                baseApp.initModuleApp(this)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            }

        }
    }

    override fun initModuleData(application: Application) {
        for (moduleApp in AppConfig.moduleApps) {
            try {
                val clazz = Class.forName(moduleApp)
                val baseApp = clazz.newInstance() as BaseApp
                baseApp.initModuleData(this)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            }

        }
    }
}