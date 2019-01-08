package com.example.retrofithttp;

/**
 * 作者：junj
 * 时间：2016/8/12
 * 描述：TOTO
 */
public class ServerException extends Exception {

    private final String mMessage;
    private String mCode;

    public ServerException (String message) {
        mMessage = message;
    }

    public ServerException (String code, String message) {
        mMessage = message;
        mCode = code;
    }

    public String getMessage() {
        return mMessage;
    }
    public String getCode() {
        return mCode;
    }
}
