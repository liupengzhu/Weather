package com.example.weather.util;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.weather.WeatherActivity;
import com.example.weather.db.City;
import com.example.weather.db.County;
import com.example.weather.db.Province;
import com.example.weather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/8/14.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        //判断response是否为空
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for(int i =0;i<allProvinces.length();i++){
                    JSONObject object = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(object.getInt("id"));
                    province.setProvinceName(object.getString("name"));
                    province.save();
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理市级数据
     */
    public static boolean handleCityResponse(String response , int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i <allCities.length() ; i++) {
                    JSONObject object = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(object.getString("name"));
                    city.setCityCode(object.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return false;
    }
    /**
     * 处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response ,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i <allCounties.length() ; i++) {
                    JSONObject object = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(object.getString("name"));
                    county.setWeatherId(object.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return false;
    }
    /**
     * 将返回的JSON数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
           // Log.d("menu",weatherContent);
            return new Gson().fromJson(weatherContent,Weather.class);


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
