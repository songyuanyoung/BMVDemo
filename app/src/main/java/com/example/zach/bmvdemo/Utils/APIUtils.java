package com.example.zach.bmvdemo.Utils;

import com.example.zach.bmvdemo.Data.Remote.RetrofitClient;
import com.example.zach.bmvdemo.Data.Remote.SOService;

/**
 * Created by zhangwenpurdue on 8/20/2017.
 */

public class APIUtils {
    public static final String BASE_URL = "http://localsearch.azurewebsites.net/";
    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
