package com.hongj.componentbase.service


interface ILoginService {

    /**
     * 是否已经登录
     *
     * @return
     */
    val isLogin: Boolean

    /**
     * 获取登录用户的 AccountId
     *
     * @return
     */
    val accountId: String?


}
