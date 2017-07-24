package com.learn.tang.presenter;

import android.content.Context;

import com.learn.tang.bean.Book;

import rx.Observable;

/**
 * Created by Tang on 2017/7/24.
 */

public class DataManager {
    private RetrofitService mRetrofitService;

    public DataManager(Context context) {
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<Book> getSearchBooks(String name, String tag, int start, int count) {
        return mRetrofitService.getSearchBooks(name, tag, start, count);
    }
}
