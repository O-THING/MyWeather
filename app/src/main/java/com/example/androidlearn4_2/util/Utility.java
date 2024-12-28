package com.example.androidlearn4_2.util;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.androidlearn4_2.db.DBManager;
import com.example.androidlearn4_2.db.MyDBHelper;


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
                    DBManager.addProvince(provinceObject.getString("name"), provinceObject.getInt("id"));
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
                    DBManager.addCity(cityObject.getString("name"), cityObject.getInt("id"), provinceId);
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
                    DBManager.addCounty(countyObject.getString("name"), countyObject.getInt("id"),cityId);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
