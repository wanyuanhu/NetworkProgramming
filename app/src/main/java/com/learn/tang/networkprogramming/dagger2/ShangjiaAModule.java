package com.learn.tang.networkprogramming.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tang on 2017/7/24.
 */

@Module
public class ShangjiaAModule {
    String restaurant;
    public ShangjiaAModule(String restaurant) {
        this.restaurant = restaurant;
    }
    @Provides
    public Baozi provideBaozi(){
        return new Baozi();
    }
//    @Provides
//    public Noodle provideNoodle(TongYi noodle){
//        return noodle;
//    }
    @Provides
    public Noodle provideNoodle(){
        return new Kangshifu();
    }
    @Provides
    public String provideRestaurant() {
        return restaurant;
    }
}
