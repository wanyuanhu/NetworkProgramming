package com.learn.tang.networkprogramming;

import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FragmentTabHost tabhost = null;
    private ViewPager vp;
    private ShareActionProvider share;
    private List<Fragment> list = new ArrayList<Fragment>();
    private String[] textArray = null;
    private ImageView imageView;
    private int bmpw = 0; // 游标宽度
    private int offset = 0;// // 动画图片偏移量
    int currIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        textArray = getResources().getStringArray(R.array.pages);
        initView();
        initCursorPos();
        setListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        MenuItem item = menu.findItem(R.id.m2);
        share = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "hello");
        intent.setType("text/plain");
        if (null != share) {
            share.setShareIntent(intent);
        }
        return true;
    }

    private void initCursorPos() {
        imageView = (ImageView) findViewById(R.id.iv);
        bmpw = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (screenW / list.size() - bmpw) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.page);
        vp.setOffscreenPageLimit(2);
        setTitle("network");
        toolbar.setLogo(R.drawable.ic_dashboard_black_24dp);
        toolbar.setSubtitle("base");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_home_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        MyFragment m1 = new MyFragment();
        MyOkHttp3Fragment m2 = new MyOkHttp3Fragment();
        MyRetrofitFragment m3 = new MyRetrofitFragment();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(new MyDesignPatternFragment());
        tabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabhost.setup(this, getSupportFragmentManager(), R.id.page);
        for (int i = 0; i < list.size(); i++) {
            tabhost.addTab(tabhost.newTabSpec(textArray[i]).setIndicator(getTabIndicator(textArray[i])), list.get(i).getClass(), null);
        }
        tabhost.getTabWidget().getChildTabViewAt(0).setBackgroundResource(R.drawable.tab_select);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    private void setListener() {
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabWidget widget = tabhost.getTabWidget();
                int oldFocusability = widget.getDescendantFocusability();
                widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
                tabhost.setCurrentTab(position);
                widget.setDescendantFocusability(oldFocusability);
                setSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                vp.setCurrentItem(tabhost.getCurrentTab());
            }
        });
    }

    private View getTabIndicator(String title_res) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_tab, null, false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.tabhosttitle);
        textViewTitle.setText(title_res);
        return view;
    }

    private void setSelect(int index) {
        for (int i = 0; i < list.size(); i++) {
            if (i == index) {
                tabhost.getTabWidget().getChildTabViewAt(index).setBackgroundResource(R.drawable.tab_select);
            } else {
                tabhost.getTabWidget().getChildTabViewAt(i).setBackgroundResource(R.drawable.tab_normal);
            }
        }
    }
}
