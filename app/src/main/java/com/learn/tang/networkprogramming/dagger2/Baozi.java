package com.learn.tang.networkprogramming.dagger2;

import javax.inject.Inject;

/**
 * Created by Tang on 2017/7/24.
 */

public class Baozi {
    String name;
    @Inject
    public Baozi() {
        name = "小笼包";
    }
    public Baozi(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
