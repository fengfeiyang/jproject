package com.hongj.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.launcher.ARouter
import com.hongj.app.R
import com.hongj.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
//        setContentView(R.layout.activity_main)
        ARouter.getInstance().build("/login/login").navigation()
    }
}
