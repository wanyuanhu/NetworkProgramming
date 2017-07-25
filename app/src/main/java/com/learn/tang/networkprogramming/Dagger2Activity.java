package com.learn.tang.networkprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.learn.tang.networkprogramming.dagger2.DaggerPlatform;
import com.learn.tang.networkprogramming.dagger2.DaggerWaimaiPingTai;
import com.learn.tang.networkprogramming.dagger2.ShangjiaAModule;
import com.learn.tang.networkprogramming.dagger2.zhainan;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tang on 2017/7/24.
 */

public class Dagger2Activity extends AppCompatActivity {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    private zhainan zhainan;
    @Inject
    int testvalue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dagger2_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4,R.id.button5})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.button:
                zhainan = DaggerPlatform.builder().shangjiaAModule(new ShangjiaAModule("王小二包子店")).build().waimai();
                Toast.makeText(this, zhainan.eat(), Toast.LENGTH_LONG).show();
                break;
            case R.id.button2:
                zhainan = DaggerWaimaiPingTai.builder().shangjiaAModule(new ShangjiaAModule("衡阳鱼粉店")).build().waimai();
                Toast.makeText(this, zhainan.eat(), Toast.LENGTH_LONG).show();
                break;
            case R.id.button3:
                zhainan = new zhainan();
                DaggerWaimaiPingTai.builder().shangjiaAModule(new ShangjiaAModule("公司食堂")).build().zhuru(zhainan);
                Toast.makeText(this, zhainan.eat(), Toast.LENGTH_LONG).show();
                break;
            case R.id.button4:
                DaggerWaimaiPingTai.builder().shangjiaAModule(new ShangjiaAModule("常德津市牛肉粉")).build().inject(this);
                Toast.makeText(this, "testvalue:" + testvalue, Toast.LENGTH_LONG).show();
                break;
            case R.id.button5:
                startActivity(new Intent("com.tang.secondActivity"));
                break;
            default:
                break;
        }
    }
}
