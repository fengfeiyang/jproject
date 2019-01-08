package com.example.retrofithttp.bean;

import java.io.Serializable;

/**
 * 作者：junj
 * 时间：2017/4/19 15:09
 * 描述：TOTO
 */
public class BaseBean<T> implements Serializable {
    public int    state;
    public String message;
    public String errorCode;// 1001->token错误
    public long   serverTime;
    public T      data;

    public boolean success () {
        return state == 1;
    }


}
