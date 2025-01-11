package com.example.androidlearn4_2.db;

import org.litepal.crud.LitePalSupport;

public class Province extends LitePalSupport {
    private int id;
    private String provinceName;
    private int provinceCode;

    public int getId(){
        return id;
    }
    public void setId(int _id){
        id=_id;
    }

    public String getProvinceName(){
        return provinceName;
    }
    public void setProvinceName(String _provinceName){
        provinceName=_provinceName;
    }

    public int getProvinceCode(){
        return provinceCode;
    }
    public void setProvinceCode(int _provinceCode){
        provinceCode=_provinceCode;
    }
}
