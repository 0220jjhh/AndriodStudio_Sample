package com.example.tabex;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //어뎁터를 만들고 어뎁터에 화면(프라그먼트)를 추가한다.
        PagerAdpater adapter = new PagerAdpater(getSupportFragmentManager());

        adapter.addFragment(new BlankFragment1(),"탭1");
        adapter.addFragment(new BlankFragment2(),"탭2");
        adapter.addFragment(new BlankFragment3(),"탭3");

        //뷰 페이저에게 어댑터를 붙인다.
        viewPager.setAdapter(adapter);

        //탭레이아웃과 뷰페이저를 연결한다.
        tabLayout.setupWithViewPager(viewPager);




        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
