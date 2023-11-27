package com.example.kifiyaassessment.config;

import android.content.Context;
import android.content.res.Resources;

import com.example.kifiyaassessment.R;
import com.example.kifiyaassessment.constants.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static String getProperty(String key, Context context) throws IOException {
        Resources resources = context.getResources();
        InputStream inputStream = resources.openRawResource(R.raw.config);
        Properties properties = new Properties();
        properties.load(inputStream);

        String propertyValue = properties.getProperty(key);
        inputStream.close();
        return  propertyValue;
    }

    public static String getApiUrl(Context context) {
        try{
            return Config.getProperty(Constants.API_URL, context);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
