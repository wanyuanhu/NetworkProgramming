package com.learn.tang.ObserverTest;

import com.learn.tang.util.CommonUtil;

/**
 * Created by Tang on 2017/7/7.
 */

public class Observer1 implements Observer {
    private static int num = 0;
    @Override
    public void update() {
        CommonUtil.logSelf("observer1 has received");
        num++;
    }

    @Override
    public int getNum() {
        return num;
    }
}
