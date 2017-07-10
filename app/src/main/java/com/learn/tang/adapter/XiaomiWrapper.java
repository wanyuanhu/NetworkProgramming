package com.learn.tang.adapter;

/**
 * Created by Tang on 2017/7/10.
 */

public class XiaomiWrapper implements USB {
    private Phone phone;

    public XiaomiWrapper(Phone phone) {
        super();
        this.phone = phone;
    }

    @Override
    public String store() {
        return "XiaomiWrapper::store";
    }

    @Override
    public String takeAlong() {
        return "XiaomiWrapper::takeAlong";
    }
}
