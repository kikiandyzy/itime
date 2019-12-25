package com.example.itime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ViewPagerFragmentAdapter  extends FragmentStatePagerAdapter {
    ArrayList<ViewPagerFragment> datas;



    public ViewPagerFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (datas != null){
            ViewPagerFragment viewPagerFragment = datas.get(position);
            return viewPagerFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return datas == null?0:datas.size();
    }

    public void setDatas(ArrayList<ViewPagerFragment> datas) {
        this.datas = datas;
    }

    //这个选项方便以后动态添加和修改
    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
