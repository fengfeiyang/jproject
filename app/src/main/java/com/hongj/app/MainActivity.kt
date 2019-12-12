package com.hongj.app

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.launcher.ARouter
import com.hongj.app.databinding.ActivityMainBinding
import com.hongj.login.view.CoustomWebviewActivity

class MainActivity : AppCompatActivity() {
    var noncompatDensity: Float = 0.0f
    var noncompatscaledDensity: Float = 0.0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setCustomDensity(this,application,720)
        ARouter.getInstance().build("/login/login").navigation()
    }

    /**
     * 今日头条适配方案
     */
    private fun setCustomDensity(@NonNull activity: Activity, application: Application, width: Int){
        
        val appDisplayMetrics = application.resources.displayMetrics
        if (noncompatDensity == 0f) {
            noncompatDensity = appDisplayMetrics.density
            noncompatscaledDensity = appDisplayMetrics.scaledDensity
            application.registerComponentCallbacks( object : ComponentCallbacks{
                override fun onLowMemory() {
                }

                override fun onConfigurationChanged(newConfig: Configuration?) {
                    if (newConfig != null && newConfig.fontScale > 0){
                        noncompatscaledDensity = application.resources.displayMetrics.scaledDensity
                    }
                }

            })
        }

        val targetDensity = appDisplayMetrics.widthPixels / width.toFloat()
        val targetScaledDensity = targetDensity * (noncompatscaledDensity / noncompatDensity)
        val targetDensityDpi = (targetDensity * 160).toInt()
        
        appDisplayMetrics.density =targetDensity
        appDisplayMetrics.scaledDensity = targetScaledDensity
        appDisplayMetrics.densityDpi = targetDensityDpi

        val activityDisplayMetrics = activity.resources.displayMetrics
        activityDisplayMetrics.density = targetDensity
        activityDisplayMetrics.scaledDensity = targetScaledDensity
        activityDisplayMetrics.densityDpi = targetDensityDpi

    }
}
