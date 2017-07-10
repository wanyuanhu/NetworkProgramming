package com.learn.tang.networkprogramming;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn.tang.ObserverTest.MySubject;
import com.learn.tang.ObserverTest.Observer;
import com.learn.tang.ObserverTest.Observer1;
import com.learn.tang.ObserverTest.Observer2;
import com.learn.tang.ObserverTest.Subject;
import com.learn.tang.adapter.Phone;
import com.learn.tang.adapter.Phone1;
import com.learn.tang.adapter.Phone2;
import com.learn.tang.adapter.XiaoMi;
import com.learn.tang.adapter.XiaomiWrapper;
import com.learn.tang.builder.Computer;
import com.learn.tang.builder.MoonComputerBuilder;
import com.learn.tang.decorator.Decorator;
import com.learn.tang.decorator.Source;
import com.learn.tang.decorator.Sourceable;
import com.learn.tang.proxy.Proxy;
import com.learn.tang.proxy.SourceProxy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by tang on 2017/6/21.
 * Fiddler
 * http://www.weather.com.cn/data/sk/101010100.html
 * http://www.weather.com.cn/data/cityinfo/101010100.html
 */

public class MyDesignPatternFragment extends Fragment {

    private static int index = 0;
    private static final String TAG = "LIFE";
    @BindView(R.id.builder)
    Button builder;
    @BindView(R.id.observer)
    Button observer;
    @BindView(R.id.adapter)
    Button adapter;
    @BindView(R.id.decorator)
    Button decorator;
    @BindView(R.id.proxy)
    Button proxy;
    @BindView(R.id.pattern_tv)
    TextView patternTv;
    @BindView(R.id.draw)
    ImageView draw;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pattern, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        patternTv.setText("第" + index++ + "页");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private int clickNum = 0;

    @OnClick({R.id.builder, R.id.observer, R.id.adapter, R.id.decorator, R.id.proxy})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.builder:
                builder();
                break;
            case R.id.observer:
                observer();
                break;
            case R.id.adapter:
                switch (clickNum++ % 3) {
                    case 0:
                        classAdapter();
                        break;
                    case 1:
                        objectAdapter();
                        break;
                    case 2:
                        interfaceAdapter();
                        break;
                    default:
                        break;
                }
                break;
            case R.id.decorator:
                Sourceable sourceable = new Decorator(new Source());
                patternTv.setText(sourceable.method());
                break;
            case R.id.proxy:
                SourceProxy sourceProxy = new Proxy();
                patternTv.setText(sourceProxy.method());
                break;
        }
    }

    private void observer() {
        Subject sub = new MySubject();
        Observer ob1 = new Observer1();
        Observer ob2 = new Observer2();
        sub.add(ob1);
        sub.add(ob2);
        sub.operation();
        patternTv.setText("1:" + ob1.getNum() + ",2:" + ob2.getNum());
    }

    private void builder() {
        Computer computer = new MoonComputerBuilder().buildCpu("i5-3470")
                .buildMainboard("华硕ROG")
                .buildRam("SK").create();
        patternTv.setText(computer.toString());
    }

    private void classAdapter() {
        XiaoMi xiaoMi = new XiaoMi();
        StringBuilder sb = new StringBuilder();
        sb.append(xiaoMi.store());
        sb.append(xiaoMi.takeAlong());
        patternTv.setText(sb.toString());
    }

    private void objectAdapter() {
        XiaomiWrapper xiaomiWrapper = new XiaomiWrapper(new Phone());
        StringBuilder sb = new StringBuilder();
        sb.append(xiaomiWrapper.store());
        sb.append(xiaomiWrapper.takeAlong());
        patternTv.setText(sb.toString());
    }

    private void interfaceAdapter() {
        StringBuilder sb = new StringBuilder();
        Phone1 p1 = new Phone1();
        Phone2 p2 = new Phone2();
        sb.append(p1.store());
        sb.append(p1.takeAlong());
        sb.append(p2.store());
        sb.append(p2.takeAlong());
        patternTv.setText(sb.toString());
    }
}
