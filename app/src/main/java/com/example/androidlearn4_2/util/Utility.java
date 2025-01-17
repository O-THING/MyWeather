package com.example.androidlearn4_2.util;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.androidlearn4_2.db.City;
import com.example.androidlearn4_2.db.County;
import com.example.androidlearn4_2.db.DBManager;
import com.example.androidlearn4_2.db.MyDBHelper;
import com.example.androidlearn4_2.db.Province;

// 解析和处理服务器返回的数据 的工具类
public class Utility {
    /*
    解析和处理服务器返回的省级数据（JSON）
     */
    private static DBManager dbManager;
    public static boolean handleProvinceResponse(String response){
        if (TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces = new JSONArray(response);
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    //DBManager.addProvince(provinceObject.getString("name"), provinceObject.getInt("id"));  // 使用SQLite
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();  // 保存一个对象使用save()方法，保存一组对象使用saveAll()方法
                    // 修改一条数据使用update()方法，修改全部数据使用updateAll()方法
                    // 查询一条数据使用find()方法，查询全部使用findAll()方法
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    解析和处理服务器返回的市级数据（JSON）
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if (TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for (int i=0;i<allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    //DBManager.addCity(cityObject.getString("name"), cityObject.getInt("id"), provinceId);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    解析和处理服务器返回的县级数据（JSON）
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if (TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties = new JSONArray(response);
                for (int i=0;i<allCounties.length();i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    //DBManager.addCounty(countyObject.getString("name"), countyObject.getInt("id"),cityId);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
