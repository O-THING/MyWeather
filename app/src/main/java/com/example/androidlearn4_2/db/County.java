package com.example.androidlearn4_2.db;

import org.litepal.crud.LitePalSupport;

public class County extends LitePalSupport {
    private int id;
    private String countyName;
    private String weatherId;
    private int cityId;
    // 注意：未设countyCode，因为省市设置这个是保存服务器返回的id，此id还有用可以用其来查询下属市/县的
    // 则表属性id应该就起个索引的作用

    public int getId(){
        return id;
    }
    public void setId(int _id){
        id=_id;
    }

    public String getCountyName(){
        return countyName;
    }
    public void setCountyName(String _countyName){
        countyName=_countyName;
    }

    public String getWeatherId(){
        return weatherId;
    }
    public void setWeatherId(String _weatherId){
        weatherId=_weatherId;
    }

    public int getCityId(){
        return cityId;
    }
    public void setCityId(int _cityId){
        cityId=_cityId;
    }
}
