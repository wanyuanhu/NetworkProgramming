package com.learn.tang.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tang on 2017/6/23.
 */

public class CityBean {
    private String cityCode;
    private String cityName;
    List<AreaBean> countyList;

    public CityBean() {

    }

    public CityBean(String cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        countyList = new ArrayList<AreaBean>();
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

    public List<AreaBean> getCountyList() {
        return countyList;
    }

    public void setCountyList(List<AreaBean> countyList) {
        this.countyList = countyList;
    }

    public List<String> getAreaName() {
        List<String> list = new ArrayList<String>();
        if (null != countyList) {
            for (AreaBean areaBean : countyList) {
                list.add(areaBean.getName());
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (null != countyList) {
            for (AreaBean areaBean : countyList) {
                sb.append(areaBean);
            }
        }
        return "CityBean{" +
                "cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", countyList=" + sb.toString() +
                '}';
    }
}
