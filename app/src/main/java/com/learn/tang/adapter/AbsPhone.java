package com.learn.tang.adapter;

/**
 * Created by Tang on 2017/7/10.
 */

public abstract class AbsPhone implements USB {
    public String store() {
        return "absPhone::store";
    }

    public String takeAlong() {
        return "absPhone::takeAlong";
    }
}
