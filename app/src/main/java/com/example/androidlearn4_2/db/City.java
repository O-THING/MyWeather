package com.example.androidlearn4_2.db;

import org.litepal.crud.LitePalSupport;

public class City extends LitePalSupport {
    private int id;
    private String cityName;
    private int cityCode;
    private int provinceId;

    public int getId(){
        return id;
    }
    public void setId(int _id){
        id=_id;
    }

    public String getCityName(){
        return cityName;
    }
    public void setCityName(String _cityName){
        cityName=_cityName;
    }

    public int getCityCode(){
        return cityCode;
    }
    public void setCityCode(int _cityCode){
        cityCode=_cityCode;
    }

    public int getProvinceId(){
        return provinceId;
    }
    public void setProvinceId(int _provinceId){
        provinceId=_provinceId;
    }

}
