package com.learn.tang.networkprogramming;

import android.content.Context;
import android.graphics.Bitmap;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bigkoo.pickerview.OptionsPickerView;
import com.learn.tang.bean.ProvinceBean;
import com.learn.tang.util.BitmapCache;
import com.learn.tang.util.GsonUtil;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by tang on 2017/6/21.
 * Fiddler
 * http://www.weather.com.cn/data/sk/101010100.html
 * http://www.weather.com.cn/data/cityinfo/101010100.html
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private static int index = 0;
    private static final String TAG = "LIFE";
    private static final int START_ADAPTER = 0;
    private static final int RESULT_URL = 1;

    private TextView tv;
    private Button btn1, btn2, btn3, btn4, btn5;
    private ImageView imageView;
    private NetworkImageView niv;

    private RequestQueue mqueue;

    private List<ProvinceBean> provinceList = new ArrayList<>();
    private List<String> allProv = new ArrayList<>();
    private List<List<String>> cityList = new ArrayList<>();
    private List<List<List<String>>> countyList = new ArrayList<>();
    private OptionsPickerView optionsPickerView = null;

    private void log(String method) {
        Log.d(TAG, method);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case START_ADAPTER:
                    setAdapter();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
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
        ImageLoader imageLoader = new ImageLoader(mqueue,new BitmapCache());
        niv.setDefaultImageResId(R.drawable.ico_default);
        niv.setErrorImageResId(R.drawable.ico_default_err);
        niv.setImageUrl("http://img06.tooopen.com/images/20170514/tooopen_sy_209849089662.jpg",imageLoader);
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
            case R.id.httpurlconnectionget:
                if (null != optionsPickerView) {
                    optionsPickerView.show();
                }
                break;
            case R.id.volleyString:
                StringRequest stringRequest = new StringRequest("https://www.baidu.com", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tv.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                });
                mqueue.add(stringRequest);
                break;
            case R.id.volleyJson:
                JsonObjectRequest mjsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        "http://api.1-blog.com/biz/bizserver/article/list.do", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                tv.setText(response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                });
                mqueue.add(mjsonObjectRequest);
                break;
            case R.id.volleyImage:
                ImageRequest imageRequest = new ImageRequest("http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg", new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        imageView.setImageResource(R.drawable.ic_home_black_24dp);
                    }
                });
                mqueue.add(imageRequest);
                break;
            case R.id.volleyImageLoader:
                ImageLoader imageLoader = new ImageLoader(mqueue, new BitmapCache());
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.ico_default, R.drawable.ico_default_err);
                imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener, 200, 200);
                break;
            default:
                break;
        }
    }

    private void initView(View view) {
        tv = (TextView) view.findViewById(R.id.tv);
        btn1 = (Button) view.findViewById(R.id.httpurlconnectionget);
        btn2 = (Button) view.findViewById(R.id.volleyString);
        btn3 = (Button) view.findViewById(R.id.volleyJson);
        btn4 = (Button) view.findViewById(R.id.volleyImage);
        btn5 = (Button) view.findViewById(R.id.volleyImageLoader);
        imageView = (ImageView) view.findViewById(R.id.showImage);
        niv = (NetworkImageView)view.findViewById(R.id.nw_image);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        startParse();
        if (null == mqueue) {
            mqueue = Volley.newRequestQueue(getContext());
        }
    }

    private void setAdapter() {
        optionsPickerView = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                final String code = provinceList.get(options1).getCityList().get(options2).getCountyList().get(options3).getCode();
                log("select code:" + code);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = getWeatherInfo(code);
                        Message msg = Message.obtain();
                        msg.what = RESULT_URL;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                }).start();
            }
        }).build();
        optionsPickerView.setPicker(allProv, cityList, countyList);
    }

    private String getWeatherInfo(String code) {
        String result = null;
        log("code:" + getString(R.string.weatherURL, code));
        try {
            URL url = new URL(getString(R.string.weatherURL, code));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5 * 1000);
            httpURLConnection.setReadTimeout(5 * 1000);
            httpURLConnection.setUseCaches(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                result = streamToString(httpURLConnection.getInputStream());
                log("response:" + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String streamToString(InputStream inputStream) {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] arr = new byte[1024];
        int index = 0;
        StringBuffer sb = new StringBuffer();
        try {
            while ((index = bis.read(arr)) != -1) {
                sb.append(new String(arr, 0, index));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    private void startParse() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (allProv.size() == 0) {
                    ParseJson();
                    provinceList = GsonUtil.getProvinceList();
                    if (null != provinceList) {
                        for (ProvinceBean province : provinceList) {
                            allProv.add(province.getProvinceName());
                            cityList.add(province.getCityName());
                            countyList.add(province.getCountyName());
                        }
                    }
                }
                mHandler.sendEmptyMessage(START_ADAPTER);
            }
        }).start();
    }

    private void ParseJson() {
        log("ParseJson start");
        try {
            InputStreamReader isr = new InputStreamReader(getResources().getAssets().open("weather_city.json"), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            GsonUtil.handleProvinceFromJson(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
