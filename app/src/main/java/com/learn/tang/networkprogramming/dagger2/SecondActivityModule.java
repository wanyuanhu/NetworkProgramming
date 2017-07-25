package com.learn.tang.networkprogramming.dagger2;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tang on 2017/7/25.
 */

@Module
public class SecondActivityModule {
    @Provides
    @Singleton
    public TestSingleton provideTestSingleton(){
        return new TestSingleton();
    }
    @Provides
    @Named("TestLazy")
    public String provideTestLazy(){
        return "this is testLazy";
    }
    @Provides
    public int provideRandomValue(){
        return (int) Math.random();
    }
}
