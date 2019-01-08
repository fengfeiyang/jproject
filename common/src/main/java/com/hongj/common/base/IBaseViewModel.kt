package com.hongj.common.base

/**
 * Created by goldze on 2017/6/15.
 */

interface IBaseViewModel {
    //    void initData();

    /**
     * View的界面创建时回调
     */
    fun onCreate()

    /**
     * View的界面销毁时回调
     */
    fun onDestroy()

//    /**
//     * 注册RxBus
//     */
//    fun registerRxBus()
//
//    /**
//     * 移除RxBus
//     */
//    fun removeRxBus()
}
