package com.hongj.componentbase.empty_service


import com.hongj.componentbase.service.ILoginService

class EmptyLoginService : ILoginService {
    override val isLogin: Boolean
        get() = false

    override val accountId: String?
        get() = null
}
