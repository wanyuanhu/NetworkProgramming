package com.learn.tang.builder;

/**
 * Created by Tang on 2017/7/7.
 */

public interface Builder {
    Builder buildCpu(String cpu);

    Builder buildMainboard(String mainboard);

    Builder buildRam(String ram);

    Computer create();
}
