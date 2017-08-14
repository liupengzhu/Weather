package com.example.weather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/8/14.
 */

/**
 * 管理当前县的数据库
 */
public class County extends DataSupport {
    private int id;
    private String countyName;//县名字
    private int weatherId;//天气ID
    private  int cityId;//对应城市的ID

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
