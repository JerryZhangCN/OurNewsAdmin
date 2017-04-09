package com.example.peter.newsadmin.common.adpter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.peter.newsadmin.fragment.pageFragment;

/**
 * Created by Administrator on 2015/7/30.
 */
public class FragmentAdeptar extends FragmentPagerAdapter {

    final int PAGE_COUNT = 6;
    private String tabTitles[] = new String[]{"推荐","ACG","游戏","社会","娱乐","科技"};
    private Context context;

    public FragmentAdeptar(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return pageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}