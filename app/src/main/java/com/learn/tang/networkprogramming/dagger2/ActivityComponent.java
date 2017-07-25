package com.learn.tang.networkprogramming.dagger2;

import com.learn.tang.networkprogramming.SecondActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Tang on 2017/7/25.
 */

@Component(modules = SecondActivityModule.class)
@Singleton
public interface ActivityComponent {
    void inject(SecondActivity activity);
    TestLazy getTestLazy();
    TestProvider getProvider();
}
