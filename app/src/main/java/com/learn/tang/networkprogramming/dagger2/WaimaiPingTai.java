package com.learn.tang.networkprogramming.dagger2;

import com.learn.tang.networkprogramming.Dagger2Activity;

import dagger.Component;

/**
 * Created by Tang on 2017/7/24.
 */
@Component(modules = {ShangjiaAModule.class,ActivityModule.class})
public interface WaimaiPingTai {
    zhainan waimai();
    void zhuru(zhainan zhainan);
    void inject(Dagger2Activity activity);
}
