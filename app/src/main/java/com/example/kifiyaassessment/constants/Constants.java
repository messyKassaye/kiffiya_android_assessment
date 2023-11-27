package com.example.kifiyaassessment.constants;

import android.content.Context;

import com.example.kifiyaassessment.config.Config;

public class Constants {

    public static String getApiUrl(Context context) {
        try{
            return Config.getProperty("API_URL", context);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
