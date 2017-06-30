package com.learn.tang.networkprogramming;

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

import com.learn.tang.bean.IpModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by tang on 2017/6/21.
 * Fiddler
 * http://www.weather.com.cn/data/sk/101010100.html
 * http://www.weather.com.cn/data/cityinfo/101010100.html
 */

public class MyRetrofitFragment extends Fragment implements View.OnClickListener {

    private static int index = 0;
    private static final String TAG = "LIFE";

    private TextView tv;
    private Button btn1, btn2, btn3, btn4;

    private void log(String method) {
        Log.d(TAG, method);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retrofit, container, false);
        initView(view);
        log("createview");
        return view;
    }

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retrofit_get:
                getIpInformation("59.108.54.37");
                break;
            case R.id.retrofit_post:
                getIpInfoByPost("59.108.54.37");
                break;
            case R.id.retrofit_pull:
                break;
            case R.id.retrofit_push:
                break;
            default:
                break;
        }
    }

    private void initView(View view) {
        tv = (TextView) view.findViewById(R.id.retrofit_tv);
        btn1 = (Button) view.findViewById(R.id.retrofit_get);
        btn2 = (Button) view.findViewById(R.id.retrofit_post);
        btn3 = (Button) view.findViewById(R.id.retrofit_pull);
        btn4 = (Button) view.findViewById(R.id.retrofit_push);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    private void getIpInformation(String ip) {
        String url = "http://ip.taobao.com/service/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofit.create(IpService.class).getIpMsg(ip).enqueue(new retrofit2.Callback<IpModel>() {
            @Override
            public void onResponse(retrofit2.Call<IpModel> call, retrofit2.Response<IpModel> response) {
                String country = response.body().getData().getCountry();
                Toast.makeText(getContext(), country, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(retrofit2.Call<IpModel> call, Throwable t) {
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
}
