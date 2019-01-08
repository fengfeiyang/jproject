package com.example.retrofithttp.utils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2016/3/28.
 */
public class ParamUtil {
    private static Gson sGson;
    //    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Map<String, String> getPatams () {
        Map<String, String> params = new HashMap<>();
        try {
//            String time = format.format(System.currentTimeMillis());
//            long nowTime = System.currentTimeMillis();
//            String nowTimeStr = SecurityUtils.getKey(nowTime + "");
//            String key = SecurityUtils.getKey(nowTimeStr);
//            params.put("key", key);
//            params.put("_key", nowTimeStr);
//            params.put("token", MyApplication.token);
//            params.put("lang", Locale.getDefault().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    public static RequestBody toRequestBody (Map map) {
        if (sGson == null) {
            synchronized (ParamUtil.class) {
                if (sGson == null) {
                    sGson = new Gson();
                }
            }
        }
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), sGson.toJson(map));
    }


}
