package com.example.kamil.treningsapp.Framgents.Days;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kamil.treningsapp.R;

/**
 * Created by Kamil on 06.01.2018.
 */

public class TabMonFragment extends Fragment {

    public TabMonFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabmon, container, false);

        return view;
    }
}
