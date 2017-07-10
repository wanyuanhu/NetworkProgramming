package com.learn.tang.proxy;

/**
 * Created by Tang on 2017/7/10.
 */

public class Proxy implements SourceProxy {
    private SourceProxy source;

    public Proxy() {
        super();
        source = new RealSource();
    }

    @Override
    public String method() {
        StringBuilder sb = new StringBuilder();
        sb.append("before");
        sb.append(source.method());
        sb.append("after");
        return sb.toString();
    }
}
