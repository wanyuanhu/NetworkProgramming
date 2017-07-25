package com.learn.tang.networkprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.learn.tang.bean.Book;
import com.learn.tang.bean.IpModel;
import com.learn.tang.presenter.BookPresenter;
import com.learn.tang.presenter.BookView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by tang on 2017/6/21.
 * Fiddler
 * http://www.weather.com.cn/data/sk/101010100.html
 * http://www.weather.com.cn/data/cityinfo/101010100.html
 */

public class MyRetrofitFragment extends Fragment implements View.OnClickListener {

    private static int index = 0;
    private static final String TAG = "LIFE";
    @BindView(R.id.retrofit_get)
    Button btn1;
    @BindView(R.id.retrofit_post)
    Button btn2;
    @BindView(R.id.retrofit_pull)
    Button btn3;
    @BindView(R.id.retrofit_push)
    Button btn4;
    @BindView(R.id.retrofit_tv)
    TextView tv;
    private BookPresenter mBookPresenter = new BookPresenter(getContext());

    private void log(String method) {
        Log.d(TAG, method);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retrofit, container, false);
        ButterKnife.bind(this, view);
        log("createview");
        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);
        return view;
    }

    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            tv.setText(mBook.toString());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        log("resume");
        tv.setText("第" + index++ + "页");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log("destroy");
        mBookPresenter.onStop();
    }

    @OnClick({R.id.retrofit_get, R.id.retrofit_post, R.id.retrofit_pull, R.id.retrofit_push})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retrofit_get:
                getIpInformation("59.108.54.37");
                break;
            case R.id.retrofit_post:
                getIpInfoByPost("59.108.54.37");
                break;
            case R.id.retrofit_pull:
                startActivity(new Intent("com.tang.dagger2ActivityTest"));
                break;
            case R.id.retrofit_push:
                mBookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
                break;
            default:
                break;
        }
    }

    private void getIpInformation(String ip) {
        String url = "http://ip.taobao.com/service/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofit.create(IpService.class).getIpMsg(ip).enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                String country = response.body().getData().getCountry();
                Toast.makeText(getContext(), country, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    private void getIpInfoByPost(String ip) {
        String url = "http://ip.taobao.com/service/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        IpServiceForPost ipService = retrofit.create(IpServiceForPost.class);
        Call<IpModel> call = ipService.getIpMsg("59.108.54.37");
        call.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                if (response.body() != null) {
                    String country = response.body().getData().getCountry();
                    Toast.makeText(getContext(), country, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {
            }
        });
    }

    private void rxJavaText() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "number:" + integer + ",thread:" + Thread.currentThread().getName());
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
