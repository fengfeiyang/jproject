package com.hongj.login.view

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hongj.common.base.BaseActivity
import com.hongj.login.BR
import com.hongj.login.R
import com.hongj.login.databinding.LoginActivitySetPasswordBinding
import com.hongj.login.viewmodel.LoginSetPasswordModel

/**
 * Created by hongj on 2018/8/10 0010.
 */
@Route(path = "/login/setPassword")
class SetPasswordActivity : BaseActivity<LoginActivitySetPasswordBinding, LoginSetPasswordModel>() {
    @JvmField
    @Autowired
    var phone: String = ""
    
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.login_activity_set_password
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewModel(): LoginSetPasswordModel {
        return LoginSetPasswordModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        viewModel?.phone?.set(phone)
    }


    override fun initData() {
         
    }
}