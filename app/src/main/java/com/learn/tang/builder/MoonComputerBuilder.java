package com.learn.tang.builder;

/**
 * Created by Tang on 2017/7/7.
 */

public class MoonComputerBuilder implements Builder {
    private Computer mCompute = new Computer();

    @Override
    public Builder buildCpu(String cpu) {
        mCompute.setmCpu(cpu);
        return this;
    }

    @Override
    public Builder buildMainboard(String mainboard) {
        mCompute.setmMainboard(mainboard);
        return this;
    }

    @Override
    public Builder buildRam(String ram) {
        mCompute.setmRam(ram);
        return this;
    }

    @Override
    public Computer create() {
        return mCompute;
    }
}
