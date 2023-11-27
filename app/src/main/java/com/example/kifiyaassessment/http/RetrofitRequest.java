package com.example.kifiyaassessment.http;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitRequest {
    private static Retrofit retrofit;

    public static Retrofit getApiInstance(String apiURL){


        OkHttpClient client = HttpService.okHttpClient();
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(apiURL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
