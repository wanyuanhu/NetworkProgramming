package com.learn.tang.util;

import android.util.Log;

/**
 * Created by Tang on 2017/7/7.
 */

public class CommonUtil {
    public static final String TAG = "observer";

    public static void logD(String tag, String args) {
        Log.d(tag, args);
    }

    public static void logI(String tag, String args) {
        Log.i(tag, args);
    }

    public static void logE(String tag, String args) {
        Log.e(tag, args);
    }

    public static void logSelf(String args) {
        Log.d(TAG, args);
    }
}
