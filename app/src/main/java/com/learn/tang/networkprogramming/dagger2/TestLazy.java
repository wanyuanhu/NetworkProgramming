package com.learn.tang.networkprogramming.dagger2;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;

/**
 * Created by Tang on 2017/7/25.
 */

public class TestLazy {
    @Inject
    public TestLazy(){

    }
    @Inject
    @Named("TestLazy")
    Lazy<String> name;
    public String getName(){
        return name.get();
    }
}
