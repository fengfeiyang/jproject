package com.hongj.login.view

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.retrofithttp.RxHelper
import com.example.retrofithttp.RxSubscribe
import com.example.retrofithttp.utils.ParamUtil
import com.hongj.common.base.BaseActivity
import com.hongj.login.BR
import com.hongj.login.R
import com.hongj.login.bean.CheckVersionBean
import com.hongj.login.databinding.ActivityLoginBinding
import com.hongj.login.http.HttpClient
import com.hongj.login.viewmodel.LoginViewModel

/**
 * Created by hongj on 2018/8/10 0010.
 */
@Route(path = "/login/login")
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewModel(): LoginViewModel {
        return LoginViewModel(this)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_login)
//        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
//        val viewModel = LoginViewModel(this)
//
////        binding.viewModel = viewModel
//        initData()
//    }


    override fun initData() {
        val patams = ParamUtil.getPatams()
        patams["versionNo"] = "212"
        HttpClient.Builder.service
                .checkVersion(patams)
                .compose(RxHelper.handleResult<CheckVersionBean>())
                .subscribe(object : RxSubscribe<CheckVersionBean>(this) {
                    override fun _onNext(bean: CheckVersionBean?) {
                    }

                    override fun _onError(e: Throwable?, message: String?) {
                    }
                })
    }
}