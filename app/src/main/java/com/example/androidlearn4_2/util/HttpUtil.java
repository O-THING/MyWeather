package com.example.androidlearn4_2.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOKHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();  // GET全国各省市数据
        client.newCall(request).enqueue(callback);
    }
}
