package com.example.kamil.treningsapp.Framgents;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kamil.treningsapp.Adapter.ViewPageAdapter;
import com.example.kamil.treningsapp.Framgents.Days.TabFriFragment;
import com.example.kamil.treningsapp.Framgents.Days.TabMonFragment;
import com.example.kamil.treningsapp.Framgents.Days.TabSatFragment;
import com.example.kamil.treningsapp.Framgents.Days.TabSunFragment;
import com.example.kamil.treningsapp.Framgents.Days.TabThuFragment;
import com.example.kamil.treningsapp.Framgents.Days.TabTueFragment;
import com.example.kamil.treningsapp.Framgents.Days.TabWedFragment;
import com.example.kamil.treningsapp.R;

import java.util.Calendar;

/**
 * Created by szymon.piszczatowski on 06.01.2018.
 */

public class PlanDietManagerFragment extends Fragment {

    private CoordinatorLayout coordinatorLayout;
    private AppBarLayout appBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.plan_diet_manager_fragment, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.dietPager);

        initTabs(container);
        toolbarViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void initTabs(ViewGroup container) {
        View view = (View) container.getParent();
        appBar = (AppBarLayout) view.findViewById(R.id.appbar);
        tabLayout = new TabLayout(getActivity());
        tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.white));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        appBar.addView(tabLayout);
    }

    private void toolbarViewPager(ViewPager viewPager) {
        ViewPageAdapter adapter = new ViewPageAdapter(getChildFragmentManager());
        adapter.addFragment(new TabMonFragment(), "Pon");
        adapter.addFragment(new TabTueFragment(), "Wt");
        adapter.addFragment(new TabWedFragment(), "Śr");
        adapter.addFragment(new TabThuFragment(), "Cz");
        adapter.addFragment(new TabFriFragment(), "Pt");
        adapter.addFragment(new TabSunFragment(), "Sob");
        adapter.addFragment(new TabSatFragment(), "Nie");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int iDay = 0 ;
        switch (day) {
            case Calendar.SUNDAY:
                iDay = 6;
                break;
            case Calendar.MONDAY:
                iDay = 0;
                break;

            case Calendar.WEDNESDAY:
                iDay = 1;
                break;
            case Calendar.TUESDAY:
                iDay = 2;
                break;

            case Calendar.THURSDAY:
                iDay = 3;
                break;

            case Calendar.FRIDAY:
                iDay = 4;
                break;

            case Calendar.SATURDAY:
                iDay = 5;
                break;


        }
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(iDay,true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (appBar != null)
            appBar.removeView(tabLayout);
    }
}
