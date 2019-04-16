package com.example.retrofithttp.utils;

import com.example.retrofithttp.HttpUtils;


/**
 * @author jingbin
 * @data 2018/2/9
 * @Description
 */

public class BuildFactory {

    private static BuildFactory instance;
    private        Object       gankHttps;

    public static BuildFactory getInstance () {
        if (instance == null) {
            synchronized (BuildFactory.class) {
                if (instance == null) {
                    instance = new BuildFactory();
                }
            }
        }
        return instance;
    }

    public <T> T create (Class<T> serviceClazz, String baseUrl) {

        if (gankHttps == null) {
            synchronized (BuildFactory.class) {
                if (gankHttps == null) {
                    gankHttps = HttpUtils.getInstance().getBuilder(baseUrl).build().create(serviceClazz);
                }
            }
        }
        return (T) gankHttps;
    }

}
