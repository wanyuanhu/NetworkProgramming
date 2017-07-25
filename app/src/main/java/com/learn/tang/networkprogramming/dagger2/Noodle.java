package com.learn.tang.networkprogramming.dagger2;

import javax.inject.Inject;

/**
 * Created by Tang on 2017/7/24.
 */

public class Noodle {
    @Inject
    public Noodle() {
    }

    @Override
    public String toString() {
        return "面条";
    }
}
