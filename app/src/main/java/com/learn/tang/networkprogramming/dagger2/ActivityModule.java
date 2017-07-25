package com.learn.tang.networkprogramming.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tang on 2017/7/25.
 */

@Module
public class ActivityModule {
    @Provides
    public int provideActivityTest(){
        return 1234567890;
    }
}
