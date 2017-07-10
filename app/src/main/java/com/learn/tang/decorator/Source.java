package com.learn.tang.decorator;

/**
 * Created by Tang on 2017/7/10.
 */

public class Source implements Sourceable {
    @Override
    public String method() {
        return "the original method";
    }
}
