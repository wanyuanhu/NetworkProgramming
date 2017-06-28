package com.learn.tang.networkprogramming;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by tang on 2017/6/21.
 * Fiddler
 * http://www.weather.com.cn/data/sk/101010100.html
 * http://www.weather.com.cn/data/cityinfo/101010100.html
 */

public class MyRetrofitFragment extends Fragment implements View.OnClickListener {

    private static int index = 0;
    private static final String TAG = "LIFE";
    private static final int START_ADAPTER = 0;
    private static final int RESULT_URL = 1;
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private TextView tv;
    private Button btn1, btn2, btn3, btn4;
    private ImageView imageView;

    private OkHttpClient okHttpClient;

    private void log(String method) {
        Log.d(TAG, method);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case START_ADAPTER:
                    break;
                case RESULT_URL:
                    tv.setText((String) msg.obj);
                default:
                    break;
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        log("attach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("create");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_okhttp, container, false);
        initView(view);
        log("createview");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log("activitycreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        log("start");
    }

    @Override
    public void onResume() {
        super.onResume();
        log("resume");
        tv.setText("第" + index++ + "页");
    }

    @Override
    public void onPause() {
        super.onPause();
        log("pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        log("stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log("destroyview");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log("destroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_get:
                getAsyncHttp();
                break;
            case R.id.ok_post:
                postAsyncHttp();
                break;
            case R.id.ok_pull:
                downAsyncFile();
                break;
            case R.id.ok_push:
                postAsyncFile();
                break;
            default:
                break;
        }
    }

    private void initView(View view) {
        tv = (TextView) view.findViewById(R.id.ok_tv);
        btn1 = (Button) view.findViewById(R.id.ok_get);
        btn2 = (Button) view.findViewById(R.id.ok_post);
        btn3 = (Button) view.findViewById(R.id.ok_pull);
        btn4 = (Button) view.findViewById(R.id.ok_push);
        imageView = (ImageView) view.findViewById(R.id.ok_showImage);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        if (null == okHttpClient) {
            okHttpClient = new OkHttpClient();
        }
    }

    private void getAsyncHttp() {
        Request.Builder requestBuilder = new Request.Builder().url("http://www.baidu.com");
        Request request = requestBuilder.build();
        Call mcall = okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    log("get okhhtp:" + str);
                } else {
                    response.body().string();
                    String str = response.networkResponse().toString();
                    log("get okhttp network:" + str);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "请求成功", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void postAsyncHttp() {
        RequestBody formbody = new FormBody.Builder().add("size", "10").build();
        Request request = new Request.Builder().url("http://api.1-blog.com/biz/bizserver/article/list.do")
                .post(formbody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().toString();
                log("post okhttp:" + str);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "请求成功", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void postAsyncFile() {
        File file = new File(getContext().getFilesDir(),"test.txt");
        Request request = new Request.Builder().url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file)).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log("post okhttp file:"+response.body().toString());
            }
        });
    }

    private void downAsyncFile(){
        Request request = new Request.Builder().url("http://img02.tooopen.com/images/20150211/tooopen_sl_110676119771.jpg").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream input = response.body().byteStream();
                FileOutputStream out = null;
                out = getActivity().openFileOutput("dowm.jpg",Context.MODE_PRIVATE);
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len = input.read()) != -1){
                    out.write(buffer,0,len);
                }
                out.flush();
                out.close();
                input.close();
                log("文件下载成功");
            }
        });
    }
//    File sdcache = getExternalCacheDir();
//    int cacheSize = 10 * 1024 * 1024;
//    OkHttpClient.Builder builder = new OkHttpClient.Builder()
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(20, TimeUnit.SECONDS)
//            .readTimeout(20, TimeUnit.SECONDS)
//            .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
//    OkHttpClient mOkHttpClient=builder.build();

}
