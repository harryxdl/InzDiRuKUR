package com.example.kamil.treningsapp;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.kamil.treningsapp.Data.AppUserData;
import com.example.kamil.treningsapp.Data.DBHelper;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;

public class CalcNutriValueActivity extends Fragment implements View.OnClickListener{

    private Spinner spinner, spinner2;
    private Button btnCalc;
    private RadioButton chboxFemale;
    private AppUserData user = new AppUserData();
    private static String TAG = "MainActivity";
    private EditText tbHeight, tbWeight, tbAge;
    DBHelper dbHelper ;
    Bundle bundle ;
    private Boolean edit;
    private double exercise = 1.0, expectation = 0.8;
    private int protein, carbo, fat, kcal, weight, hight, age;
    private float[] yData ;
    private String[] xData = {"Białko", "Węglowodany" , "Tłuszcze" };
    PieChart pieChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calc_nutri_value, container, false);
        dbHelper = new DBHelper(getActivity());
        try{
            bundle = getArguments();
        }
        catch(Exception ex)
        {

        }
        if(bundle!=null)
            edit = bundle.getBoolean("edit");
        else
            edit = false;
        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        btnCalc = (Button) view.findViewById(R.id.btnCalculate);
        tbHeight = (EditText) view.findViewById(R.id.tbxHeight);
        tbWeight = (EditText) view.findViewById(R.id.tbxWeight);
        tbAge = (EditText) view.findViewById(R.id.tbxAge);
        chboxFemale = (RadioButton) view.findViewById(R.id.chboxFemale);
        //////////////////////////////////////////////////////
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
        int sex;
        if(chboxFemale.isChecked())
        {   sex = 0;
            kcal =  (10 * weight )+(int)(6.25 * hight )-(5 * age) - 161;
        }
        else
        {
            sex = 1;
            kcal = (10 * weight)+ (int)(6.25 * hight)-(5 * age) + 5;
        }
        kcal = (int)(kcal*exercise*expectation);
        protein = kcal/4 *20/100;
        fat = (kcal/9 *25/100);
        carbo = kcal/4 *55/100;
        float[] nutries = {(float)(protein),(float)(carbo),(float)(fat)};
        yData = nutries;

        user.setCarbo(carbo);
        user.setEnergy(kcal);
        user.setProtein(protein);
        user.setFat(fat);
        user.setHeight(hight);
        user.setAge(age);
        user.setWeight(weight);
        user.setSex(sex);
        user.setExpectations(expectation);
        user.setPhysical_activity(exercise);

        if(!edit){
            dbHelper.AddAppUser(user);
        }
        else{
            dbHelper.UpdateAppUser(user,1);
        }

        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.content_main, new ShowCalcNutri());
        ft.commit();

    }
}
//436435
//Tłuszcze 16%
// Białko 20%
//węgla 64%