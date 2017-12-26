package com.example.kamil.treningsapp;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kamil.treningsapp.DBData.AppUserData;
import com.example.kamil.treningsapp.DBData.DBHelper;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowCalcNutri#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ShowCalcNutri extends Fragment {

    PieChart pieChart;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private float[] yData;
    private String[] xData = {"Białko", "Węglowodany" , "Tłuszcze" };
    public ShowCalcNutri() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowCalcNutri.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowCalcNutri newInstance(String param1, String param2) {
        ShowCalcNutri fragment = new ShowCalcNutri();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_calc_nutri, container, false);
        DBHelper dbHelper = new DBHelper(getActivity());
        AppUserData user = dbHelper.getUser(1);

        int protein = user.getProtein();
        int fat = user.getFat();
        int carbo = user.getCarbo();
        int kcal = user.getEnergy();
        pieChart = (PieChart) view.findViewById(R.id.idPieChart);
        pieChart.setDescription("Zapotrzebowanie ");
        pieChart.setRotationEnabled(false);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(20f);
        pieChart.setTransparentCircleAlpha(0);
        //pieChart.setCenterText("Zapotrzebowanie na Wartośći odżywcze");
        pieChart.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!
        float[] nutries = {(float)(protein),(float)(carbo),(float)(fat)};
        yData = nutries;

        addDataSet();
        return view;
    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Zapotrzebowanie");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(Color.RED);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legend.setCustom(colors, Arrays.asList(xData));
        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
