package com.example.kamil.treningsapp;

import android.content.DialogInterface;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private ListView historyListView;
    private List<TreningData> treningHistoryList;
    private ArrayAdapter<TreningData> treningAdapter;

    public HistoryFragment newInstance(List<TreningData> treningDataList) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("historyListInstance", (ArrayList<? extends Parcelable>) treningDataList);
        fragment.setArguments(args);
        return fragment;
    }

    public List<TreningData> getTreningDataList() {
        return getArguments().getParcelableArrayList("historyListInstance");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        getActivity().setTitle("Historia treningów");
        historyListView = (ListView) view.findViewById(R.id.historyListView);
        if(savedInstanceState != null){
            treningHistoryList = savedInstanceState.getParcelableArrayList("historyListState");
        }else{
            treningHistoryList = getTreningDataList();
        }
        treningAdapter = new TreningHistoryAdapter(getActivity().getApplicationContext(),R.layout.history_training_row,treningHistoryList);
        historyListView.setAdapter(treningAdapter);
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                deleteDialog(arg2);
            }
        });
        return view;
    }

    private void deleteDialog(final int arg2){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Usuwanie rekordu!");
        builder.setMessage("Czy chcesz usunąć rekord?");
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MainActivity) getActivity()).deleteTrening(treningHistoryList.get(arg2));
                treningHistoryList = ((MainActivity)getActivity()).getAllTrenings();
                treningAdapter.clear();
                treningAdapter = new TreningHistoryAdapter(getActivity().getApplicationContext(),R.layout.history_training_row,treningHistoryList);
                historyListView.setAdapter(treningAdapter);
                historyListView.invalidateViews();
                treningAdapter.notifyDataSetChanged();
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
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("historyListState", (ArrayList<? extends Parcelable>) treningHistoryList);
        super.onSaveInstanceState(outState);
    }
}
