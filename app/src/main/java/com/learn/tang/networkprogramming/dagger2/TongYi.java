package com.learn.tang.networkprogramming.dagger2;

import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by Tang on 2017/7/24.
 */

public class TongYi extends Noodle {
    @Inject
    public TongYi(){}

    @Override
    public String toString() {
        return "统一方便面";
    }
}
