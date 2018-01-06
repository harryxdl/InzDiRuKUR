package com.example.kamil.treningsapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kamil.treningsapp.Framgents.Days.TabMonFragment;
import com.example.kamil.treningsapp.Framgents.Days.TabTueFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 06.01.2018.
 */

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentsList = new ArrayList<>();
    private final List<String> titleFragment = new ArrayList<>();

    public ViewPageAdapter (FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);

    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }
    public void addFragment(android.support.v4.app.Fragment fragment, String title) {
        fragmentsList.add(fragment);
        titleFragment.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
