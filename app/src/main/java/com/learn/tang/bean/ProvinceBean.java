package com.learn.tang.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dolph.pan on 2017/6/23.
 */

public class ProvinceBean {

    private String provinceCode;
    private String provinceName;
    List<CityBean> cityList;

    public ProvinceBean() {

    }

    public ProvinceBean(String provinceCode, String provinceName) {
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        cityList = new ArrayList<CityBean>();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<CityBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityBean> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (CityBean cityBean : cityList) {
            sb.append(cityBean);
        }
        return "ProvinceBean{" +
                "provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityList=" + sb.toString() +
                '}';
    }
}
