package com.learn.tang.ObserverTest;

import com.learn.tang.util.CommonUtil;

/**
 * Created by Tang on 2017/7/7.
 * android事件驱动，broadcastReceiver
 */

public class MySubject extends AbstractSubject {
    @Override
    public void operation() {
        CommonUtil.logSelf("update self");
        notifyObservers();
    }

    public static void main(String[] args){
        Subject subject = new MySubject();
        subject.add(new Observer1());
        subject.add(new Observer2());
        subject.operation();
    }
}
