package com.example.zach.bmvdemo.Data.Model;

import java.util.ArrayList;

/**
 * Created by zhangwenpurdue on 8/20/2017.
 */

public class LocationList extends ArrayList<Location> {
    private static  LocationList ourInstance = null;

    public synchronized static LocationList getInstance() {

        if (ourInstance == null) {
            synchronized (LocationList.class) {
                if (ourInstance == null) {
                    ourInstance = new LocationList();
                }
            }
        }
        return ourInstance;
    }

    private LocationList() {
    }
}
