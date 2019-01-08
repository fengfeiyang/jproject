package com.hongj.common.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * Created by hongj on 2018/8/20 0020.
 *
 */

abstract class BaseViewModel(var context: Context) : IBaseViewModel, ViewModel() {

    lateinit var titleName: ObservableField<String>
    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    fun startActivity(clz: Class<*>) {
        context.startActivity(Intent(context, clz))
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(context, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        context.startActivity(intent)
    }

    fun onBack() {
        try {
            (context as Activity).finish()
        } catch (e: Exception) {

        }
    }


    override fun onCreate() {}

    override fun onDestroy() {}

//    override fun registerRxBus() {}
//
//    override fun removeRxBus() {}
}