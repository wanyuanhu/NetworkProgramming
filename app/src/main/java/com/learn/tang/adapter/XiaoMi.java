package com.learn.tang.adapter;

/**
 * Created by Tang on 2017/7/10.
 */

public class XiaoMi extends Phone implements USB {
    @Override
    public String store() {
        return "XiaoMi::store";
    }
}
