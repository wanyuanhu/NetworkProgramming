package com.learn.tang.proxy;

/**
 * Created by Tang on 2017/7/10.
 */

public class RealSource implements SourceProxy {
    @Override
    public String method() {
        return "RealSource::method";
    }
}
