package com.hongj.componentbase

import com.hongj.componentbase.empty_service.EmptyLoginService
import com.hongj.componentbase.service.ILoginService

/**
 * 禁止外部创建 ServiceFactory 对象
 */
class ServiceFactory private constructor() {

    companion object {

        /**
         * 通过静态内部类方式实现 ServiceFactory 的单例
         */
        val instance: ServiceFactory = Inner.serviceFactory
    }

    private object Inner {
        internal val serviceFactory = ServiceFactory()
    }

    /**
     *  获取loginService
     */
    var loginService: ILoginService? = null
        get() {
            if (field == null) {
                field = EmptyLoginService()
            }
            return field
        }


}
