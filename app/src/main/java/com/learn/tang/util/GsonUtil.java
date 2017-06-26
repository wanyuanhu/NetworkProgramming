package com.learn.tang.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.tang.bean.AreaBean;
import com.learn.tang.bean.CityBean;
import com.learn.tang.bean.ProvinceBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Tang 2017/6/23.
 */

public class GsonUtil {
    private static List<ProvinceBean> provinceList = null;

    public static List<ProvinceBean> getProvinceList() {
        return provinceList;
    }

    public static void logSelf(String arg){
        Log.d("app",arg);
    }
    public synchronized static boolean handleProvinceFromJson(String json) {
        if (null == provinceList) {
            Gson gson = new Gson();
            Map<String, List<ProvinceBean>> map = gson.fromJson(json, new TypeToken<Map<String, List<ProvinceBean>>>() {
            }.getType());
            provinceList = map.get("CityCode");
        }
        logSelf("handleProvinceFromJson list:"+ provinceList.size());
        if (provinceList.size() > 0) {
            for (ProvinceBean provinceBean : provinceList) {
                logSelf(provinceBean.toString());
            }
            return true;
        }
        return false;
    }

    public synchronized static boolean handleCitiesFromJson(String jsonStr, ProvinceBean province) {
        if (provinceList == null) {
            Gson gson = new Gson();
            Map<String, List<ProvinceBean>> map = gson.fromJson(jsonStr, new TypeToken<Map<String, List<ProvinceBean>>>() {
            }.getType());
            provinceList = map.get("CityCode");
        }
        boolean result = false;
        String provinceName = province.getProvinceName();
        for (ProvinceBean provinceJson : provinceList) {
            if (provinceJson.getProvinceName().equals(provinceName)) {
                if (provinceJson.getCityList().size() > 0) result = true;
                for (CityBean cityJson : provinceJson.getCityList()) {
                    //TODO;
                    //处理每一个cityJson
                }
                break;
            }
        }
        return result;
    }

    public synchronized static boolean handleCountiesFromJson(String jsonStr, ProvinceBean province, CityBean city) {
        if (provinceList == null) {
            Gson gson = new Gson();
            Map<String, List<ProvinceBean>> map = gson.fromJson(jsonStr, new TypeToken<Map<String, List<ProvinceBean>>>() {
            }.getType());
            provinceList = map.get("CityCode");
        }
        boolean result = false;
        String provinceName = province.getProvinceName();
        String cityName = city.getCityName();
        for (ProvinceBean provinceJson : provinceList) {
            if (provinceJson.getProvinceName().equals(provinceName)) {
                for (CityBean cityJson : provinceJson.getCityList()) {
                    if (cityJson.getCityName().equals(cityName)) {
                        if (cityJson.getCountyList().size() > 0) result = true;
                        for (AreaBean countyJson : cityJson.getCountyList()) {
                            //TODO;
                            //处理每一个countyJson
                        }
                        break;
                    }
                }
                break;
            }
        }
        return result;

    }
}
