package com.learn.tang.networkprogramming.dagger2;

import javax.inject.Inject;

/**
 * Created by Tang on 2017/7/24.
 */

public class zhainan {
    @Inject
    Baozi baozi;
    @Inject
    Noodle noodle;
    @Inject
    public zhainan() {
    }
    @Inject
    String restaurant;
    public String eat(){
        StringBuilder sb = new StringBuilder();
        sb.append("我从 ");
        sb.append(restaurant+" 订的外卖， ");
        sb.append("我吃的是：");
        if(null != baozi){
            sb.append(baozi+"  ");
        }
        if(null != noodle){
            sb.append(noodle);
        }
        return sb.toString();
    }
}
