package com.learn.tang.networkprogramming;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by tang on 2017/6/21.
 */

public class MyFragment extends Fragment {

    private static int index = 0;
    private static final String TAG = "LIFE";

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

    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
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
}
