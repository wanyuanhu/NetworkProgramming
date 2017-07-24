package com.learn.tang.presenter;

import android.content.Intent;

/**
 * Created by Tang on 2017/7/24.
 */

public interface Presenter {
    void onCreate();

    void onStart();

    void onStop();

    void pause();

    void attachView(View view);

    void attachIncomingIntent(Intent intent);
}
