package com.learn.tang.builder;

/**
 * Created by Tang on 2017/7/7.
 */

public class Singleton {

    private static Singleton instance = null;

    private Singleton() {
    }

    private static synchronized void syncInit() {
        if (null == instance) {
            instance = new Singleton();
        }
    }

    public static Singleton getInstance() {
        if (null != instance) {
            syncInit();
        }
        return instance;
    }
}
