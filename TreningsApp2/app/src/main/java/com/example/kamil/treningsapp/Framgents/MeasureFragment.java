package com.example.kamil.treningsapp.Framgents;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.kamil.treningsapp.Activity.MainActivity;
import com.example.kamil.treningsapp.Adapter.MeasureListAdapter;
import com.example.kamil.treningsapp.Adapter.TreningHistoryAdapter;
import com.example.kamil.treningsapp.DBHelper;
import com.example.kamil.treningsapp.Models.MeasureData;
import com.example.kamil.treningsapp.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeasureFragment extends Fragment {

    MeasureListAdapter adapter;
    ListView list;
    private List<MeasureData> measureList;
    CardView btn;
    public MeasureFragment() {
        // Required empty public constructor
    }
    DBHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_measure, container, false);
        db = new DBHelper(getActivity());
        list = (ListView) view.findViewById(R.id.lstMeasure);
        btn = (CardView) view.findViewById(R.id.btnMeasureFragment);
        measureList = db.getAllMeasureList();
        adapter = new MeasureListAdapter(getActivity(), measureList);
        list.setAdapter(adapter);
        getActivity().setTitle("Mierzenie Ciała");

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AddMeasure.class);
                startActivity(intent);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                deleteDialog(arg2);
            }}
        );

        return view;
    }
    private void deleteDialog(final int arg2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Usuwanie rekordu!");
        builder.setMessage("Czy chcesz usunąć rekord?");
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MainActivity) getActivity()).deleteMeasure(measureList.get(arg2));
                measureList = ((MainActivity) getActivity()).getAllMeasure();
                adapter = null;
                adapter = new MeasureListAdapter(getActivity(), measureList);
                list.setAdapter(adapter);
                list.invalidateViews();
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public void onResume() {
        super.onResume();
        measureList = db.getAllMeasureList();
        adapter = new MeasureListAdapter(getActivity(), measureList);
        list.setAdapter(adapter);
    }
}
