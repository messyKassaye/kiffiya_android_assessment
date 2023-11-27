package com.example.kifiyaassessment.config;

import android.content.Context;
import android.content.res.Resources;

import com.example.kifiyaassessment.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static String getProperty(String key, Context context) throws IOException {
        Resources resources = context.getResources();
        InputStream inputStream = resources.openRawResource(R.raw.config);
        Properties properties = new Properties();
        properties.load(inputStream);

        String apiURL = properties.getProperty(key);
        inputStream.close();
        return  apiURL;
    }
}
