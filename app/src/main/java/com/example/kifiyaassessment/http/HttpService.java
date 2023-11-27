package com.example.kifiyaassessment.http;

import okhttp3.OkHttpClient;

public class HttpService {

    public static OkHttpClient okHttpClient(){
        OkHttpClient client = new OkHttpClient();
        return client;
    }
}
