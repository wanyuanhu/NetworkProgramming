package com.learn.tang.networkprogramming;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.learn.tang.util.GsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.Map;


/**
 * Created by tang on 2017/6/21.
 * Fiddler
 * http://www.weather.com.cn/data/sk/101010100.html
 * http://www.weather.com.cn/data/cityinfo/101010100.html
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private static int index = 0;
    private static final String TAG = "LIFE";

    private TextView tv;
    private Button btn1, btn2;
    private Spinner province, city, area;

    private JSONObject jsonObject;
    private List<String> allProv = new ArrayList<String>();
    private Map<String, String> cityMap = new ArrayMap<String, String>();
    private Map<String, String> areaMap = new ArrayMap<String, String>();


    private void log(String method) {
        Log.d(TAG, method);
    }

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
        tv = (TextView) view.findViewById(R.id.tv);
        btn1 = (Button) view.findViewById(R.id.httpurlconnectionget);
        btn2 = (Button) view.findViewById(R.id.httpurlconnectionpost);
        province = (Spinner) view.findViewById(R.id.province);
        city = (Spinner) view.findViewById(R.id.city);
        area = (Spinner) view.findViewById(R.id.county);
        log("createview");
        initJson();
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
            case R.id.httpurlconnectionget:
                ParseJson();
                break;
            case R.id.httpurlconnectionpost:
                break;
            default:
                break;
        }
    }

    private String getWeatherInfo(URL url) {
        String result = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5 * 1000);
            httpURLConnection.setReadTimeout(5 * 1000);
            httpURLConnection.setUseCaches(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                result = streamToString(httpURLConnection.getInputStream());
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

    private void initJson() {
        try {
            InputStreamReader isr = new InputStreamReader(getActivity().getAssets().open("weather_city.json"), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            jsonObject = new JSONObject(builder.toString());
            JSONArray citycode = jsonObject.getJSONArray("CityCode");
            for (int i = 0;i<citycode.length();i++){
                JSONObject provinces = citycode.getJSONObject(i);
                allProv.add(provinces.getString("provinceName"));
                JSONArray cityList = provinces.getJSONArray("cityList");
                for (int j= 0;j<cityList.length();j++){
                    JSONObject city_one = cityList.getJSONObject(j);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        jsonObject = new JSONObject()
    }
    private void ParseJson(){
            try {
                InputStreamReader isr = new InputStreamReader(getActivity().getAssets().open("weather_city.json"), "UTF-8");
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
