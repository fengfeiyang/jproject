package com.hongj.login

import com.hongj.componentbase.service.ILoginService

/**
 * Created by hongj on 2018/8/14 0014.
 */

class LoginService : ILoginService {
    override val isLogin: Boolean
        get() = false
    override val accountId: String?
        get() = null

}