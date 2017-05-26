package com.example.tabex;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 박지현 on 2017-04-09.
 */

public class PagerAdpater extends FragmentPagerAdapter{
    //뷰페이저에 붙여줄 Fragment를 모두 가지고 있어야 한다.
    private final List<Fragment> mfragments = new ArrayList<>(); //뷰페이저에 붙여줄 Fragment
    private final List<String> mfragmentTitles = new ArrayList<>();  //탭의 제목

    public PagerAdpater(FragmentManager fm) {
        super(fm);
    }

    //데이터를 추가하는 메소드를 만들어 준다.
    public void addFragment(Fragment fragment, String title) {
        mfragments.add(fragment);
        mfragmentTitles.add(title);
    }
    @Override
    public Fragment getItem(int position) {
        return mfragments.get(position); //넘어온 인수의 프라그먼트를 리턴.
    }

    @Override
    public int getCount() {
        return mfragments.size(); //프라그먼트의 개수 리턴.
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mfragmentTitles.get(position);
    }
}
