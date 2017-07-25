package com.learn.tang.networkprogramming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.learn.tang.networkprogramming.dagger2.DaggerActivityComponent;
import com.learn.tang.networkprogramming.dagger2.TestSingleton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tang on 2017/7/25.
 */

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.button6)
    Button button6;
    @Inject
    TestSingleton singleton1;
    @Inject
    TestSingleton singleton2;
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;

    @Inject
    int randomvalue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button6,R.id.button7,R.id.button8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button6:
                DaggerActivityComponent.builder().build().inject(this);
                Toast.makeText(this, "single1:" + singleton1 + ",single2:" + singleton2, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button7:
                String name = DaggerActivityComponent.builder().build().getTestLazy().getName();
                Toast.makeText(this, "lazy::" + name, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button8:
                randomvalue = DaggerActivityComponent.builder().build().getProvider().getRandomValue();
                Toast.makeText(this, "againload::" + randomvalue, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

}
