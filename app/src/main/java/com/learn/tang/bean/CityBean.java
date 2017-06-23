package com.learn.tang.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tang on 2017/6/23.
 */

public class CityBean {
    private String cityCode;
    private String cityName;
    List<AreaBean> areaList;

    public CityBean() {

    }

    public CityBean(String cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        areaList = new ArrayList<AreaBean>();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<AreaBean> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaBean> areaList) {
        this.areaList = areaList;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (AreaBean areaBean : areaList) {
            sb.append(areaBean);
        }
        return "CityBean{" +
                "cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", areaList=" + sb.toString() +
                '}';
    }
}
