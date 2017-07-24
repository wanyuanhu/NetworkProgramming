package com.learn.tang.presenter;

import com.learn.tang.bean.Book;

/**
 * Created by Tang on 2017/7/24.
 */

public interface BookView extends View {
    void onSuccess(Book mBook);
    void onError(String result);
}
