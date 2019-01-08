package com.hongj.common.base

/**
 * Created by goldze on 2017/6/15.
 */

interface IBaseActivity {
    /**
     * 初始化界面传递参数
     */
    fun initParam()

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 初始化界面观察者的监听
     */
    fun initViewObservable()
}
