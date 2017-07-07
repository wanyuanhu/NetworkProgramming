package com.learn.tang.builder;

/**
 * Created by Tang on 2017/7/7.
 */

public class Computer {
    private String mCpu;
    private String mMainboard;
    private String mRam;

    public String getmCpu() {
        return mCpu;
    }

    public void setmCpu(String mCpu) {
        this.mCpu = mCpu;
    }

    public String getmMainboard() {
        return mMainboard;
    }

    public void setmMainboard(String mMainboard) {
        this.mMainboard = mMainboard;
    }

    public String getmRam() {
        return mRam;
    }

    public void setmRam(String mRam) {
        this.mRam = mRam;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "mCpu='" + mCpu + '\'' +
                ", mMainboard='" + mMainboard + '\'' +
                ", mRam='" + mRam + '\'' +
                '}';
    }
}
