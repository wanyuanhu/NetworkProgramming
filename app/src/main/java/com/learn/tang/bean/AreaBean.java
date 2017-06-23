package com.learn.tang.bean;

/**
 * Created by Tang on 2017/6/23.
 */

public class AreaBean {
    private String name;
    private String code;

    public AreaBean() {

    }

    public AreaBean(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AreaBean{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
