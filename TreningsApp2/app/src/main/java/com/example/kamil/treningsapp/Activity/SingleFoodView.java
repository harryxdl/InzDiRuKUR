package com.example.kamil.treningsapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kamil.treningsapp.R;

/**
 * Created by Kamil on 15.12.2017.
 */

public class SingleFoodView extends Activity {
    TextView txtid;
    TextView txtname;
    TextView txtcal;
    TextView txtcarbo;
    TextView txtfat;
    TextView txtprotein;
    int iId;
    String name;
    int energy;
    int protein;
    int carbo;
    int fat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlefoodview);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of id
        iId = i.getIntExtra("id", 0);
        // Get the results of name
        name = i.getStringExtra("name");
        // Get the results of calories
        energy = i.getIntExtra("cal", 0);
        protein = i.getIntExtra("protein", 0);
        carbo = i.getIntExtra("carbo", 0);
        fat = i.getIntExtra("fat", 0);

        // Locate the TextViews in singleitemview.xml
        txtid = (TextView) findViewById(R.id.foodId);
        txtname = (TextView) findViewById(R.id.foodName);
        txtcal = (TextView) findViewById(R.id.foodCal);
        txtcarbo = (TextView) findViewById(R.id.foodCarbo);
        txtfat = (TextView) findViewById(R.id.foodFat);
        txtprotein = (TextView) findViewById(R.id.foodProtein);

        // Load the results into the TextViews
        txtid.setText(Integer.toString(iId));
        txtname.setText(name);
        txtcal.setText(Integer.toString(energy));
        txtcarbo.setText(Double.toString(carbo));
        txtfat.setText(Double.toString(fat));
        txtprotein.setText(Double.toString(protein));
    }
}
