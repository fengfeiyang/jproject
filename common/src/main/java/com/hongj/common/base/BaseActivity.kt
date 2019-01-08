package com.hongj.common.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by hongj on 2018/8/20 0020.
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(), IBaseActivity {
    var binding: V? = null
    var viewModel: VM? = null
    var mContext: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        initParam()

        initViewDataBinding(savedInstanceState)

        initData()

        initViewObservable()

        viewModel?.onCreate()

//        viewModel!!.registerRxBus()
    }

    override fun onDestroy() {
        super.onDestroy()
//        viewModel!!.removeRxBus()
        viewModel?.onDestroy()
        viewModel = null
        binding?.unbind()
    }

    /**
     * 注入绑定
     */
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        viewModel = initViewModel()
        binding?.setVariable(initVariableId(), viewModel)
    }

    //刷新布局
    fun refreshLayout() {
        if (viewModel != null) {
            binding?.setVariable(initVariableId(), viewModel)
        }
    }

    override fun initParam() {

    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(savedInstanceState: Bundle?): Int

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    abstract fun initViewModel(): VM

    override fun initData() {

    }

    override fun initViewObservable() {

    }
}
