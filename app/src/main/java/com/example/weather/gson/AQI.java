package com.example.weather.gson;

/**
 * Created by Administrator on 2017/8/14.
 */

public class AQI {

    public AQICity city;

    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
