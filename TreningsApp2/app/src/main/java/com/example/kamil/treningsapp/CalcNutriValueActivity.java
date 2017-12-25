package com.example.kamil.treningsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CalcNutriValueActivity extends Fragment implements View.OnClickListener{

    private Spinner spinner, spinner2;
    private Button btnCalc;
    private RadioButton chboxFemale;
    private static String TAG = "MainActivity";
    private EditText tbHeight, tbWeight, tbAge;
    Bundle bundle = new Bundle();
    private double exercise = 1.0, expectation = 0.8;
    private int protein, carbo, fat, kcal, weight, hight, age;
    private float[] yData ;
    private String[] xData = {"Białko", "Węglowodany" , "Tłuszcze" };
    PieChart pieChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calc_nutri_value, container, false);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        btnCalc = (Button) view.findViewById(R.id.btnCalculate);
        tbHeight = (EditText) view.findViewById(R.id.tbxHeight);
        tbWeight = (EditText) view.findViewById(R.id.tbxWeight);
        tbAge = (EditText) view.findViewById(R.id.tbxAge);
        chboxFemale = (RadioButton) view.findViewById(R.id.chboxFemale);
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

        // Spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),R.array.sportActivity, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),R.array.expectation, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:
                        exercise = 1.2;
                        break;
                    case 1:
                        exercise = 1.4;
                        break;
                    case 2:
                        exercise = 1.6;
                        break;
                    case 3:
                        exercise = 1.8;
                        break;
                    case 4:
                        exercise = 2.0;
                        break;
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 2:
                       expectation = 0.8;
                        break;
                    case 1:
                        expectation = 1.0;
                        break;
                    case 0:
                        expectation = 1.2;
                        break;
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Button

        btnCalc.setOnClickListener(this);




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

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnCalculate:
                Calculate();
              //  Intent intent = new Intent()
        }
    }

    private void Calculate() {
        weight = Integer.parseInt(String.valueOf(tbWeight.getText()));
        hight = Integer.parseInt(String.valueOf(tbHeight.getText()));
        age = Integer.parseInt(String.valueOf(tbAge.getText()));
        if(chboxFemale.isChecked())
        {
            kcal =  (10 * weight )+(int)(6.25 * hight )-(5 * age) - 161;
        }
        else
        {
            kcal = (10 * weight)+ (int)(6.25 * hight)-(5 * age) + 5;
        }
        kcal = (int)(kcal*exercise*expectation);
        protein = kcal/4 *20/100;
        fat = kcal/9 *25/100;
        carbo = kcal/4 *55/100;
        float[] nutries = {(float)(protein),(float)(carbo),(float)(fat)};
        yData = nutries;
        addDataSet();
    }
}
//436435
//Tłuszcze 16%
// Białko 20%
//węgla 64%