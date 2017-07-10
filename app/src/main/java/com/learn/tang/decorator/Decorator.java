package com.learn.tang.decorator;

import java.util.Date;

/**
 * Created by Tang on 2017/7/10.
 */

public class Decorator implements Sourceable {
    private Sourceable source;

    public Decorator(Sourceable source) {
        this.source = source;
    }

    @Override
    public String method() {
        StringBuilder sb = new StringBuilder();
        sb.append("befor:" + new Date().getTime());
        sb.append(source.method());
        sb.append("after:" + new Date().getTime());
        return sb.toString();
    }
}
