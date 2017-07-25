package com.learn.tang.networkprogramming.dagger2;

import dagger.Component;

/**
 * Created by Tang on 2017/7/24.
 */

@Component(modules = ShangjiaAModule.class)
public interface Platform {
    zhainan waimai();
}
