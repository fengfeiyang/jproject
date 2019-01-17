package com.hongj.login.http

import com.example.retrofithttp.bean.BaseBean
import com.example.retrofithttp.utils.BuildFactory
import com.hongj.common.Constant.Constants
import com.hongj.login.bean.CheckVersionBean
import com.hongj.login.bean.LoginBean
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*
import java.util.*

/**
 * Created by hongj on 2018/8/15 0015.
 */
interface HttpClient {


    object Builder {
        val service: HttpClient
            get() = BuildFactory.getInstance().create(HttpClient::class.java, Constants.BASE_URL)

    }

    /**
     * 下载新版本
     *
     * @param path
     * @return
     */
    @GET
    fun downloadNewVersion(@Url path: String): Observable<ResponseBody>

    /**
     * 登录
     *
     * @param
     * @return
     */
//    @POST("/api/authenticate")
//    @FormUrlEncoded
//    fun login(@FieldMap map: Map<String, String>): Observable<BaseBean<LoginBean>>
    @POST("/api/authenticate")
    fun login(@Body map: Map<String,String>): Observable<BaseBean<LoginBean>>

    /**
     * 版本更新
     *
     * @param
     * @return
     */
    @POST("/app/version/checkVersion")
    @FormUrlEncoded
    fun checkVersion(@FieldMap map: Map<String, String>): Observable<BaseBean<CheckVersionBean>>
}