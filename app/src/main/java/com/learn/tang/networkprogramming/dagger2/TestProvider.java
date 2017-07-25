package com.learn.tang.networkprogramming.dagger2;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by Tang on 2017/7/25.
 */

public class TestProvider {
    @Inject
    public TestProvider(){

    }
    @Inject
    Provider<Integer> randomValue;
    public int getRandomValue(){
        return randomValue.get().intValue();
    }
}
