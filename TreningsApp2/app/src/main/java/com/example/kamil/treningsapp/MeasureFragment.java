package com.example.kamil.treningsapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.kamil.treningsapp.Adapter.MeasureListAdapter;
import com.example.kamil.treningsapp.Adapter.TreningHistoryAdapter;
import com.example.kamil.treningsapp.DBData.DBHelper;
import com.example.kamil.treningsapp.DBData.MeasureData;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeasureFragment extends Fragment {

    MeasureListAdapter adapter;
    ListView list;
    Button btn;
    public MeasureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_measure, container, false);
        DBHelper db = new DBHelper(getActivity());
        list = (ListView) view.findViewById(R.id.lstMeasure);
        btn = (Button) view.findViewById(R.id.btnMeasureFragment);
        adapter = new MeasureListAdapter(getActivity(), db.getAllMeasureList());
        list.setAdapter(adapter);
        getActivity().setTitle("Mierzenie Ciała");

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AddMeasure.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
