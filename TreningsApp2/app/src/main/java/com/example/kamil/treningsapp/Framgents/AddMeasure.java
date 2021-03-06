package com.example.kamil.treningsapp.Framgents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kamil.treningsapp.DBHelper;
import com.example.kamil.treningsapp.Models.MeasureData;
import com.example.kamil.treningsapp.R;

import java.util.Date;

public class AddMeasure extends AppCompatActivity {
    private EditText tbWeight, tbBiceps, tbThigh, tbWaist, tbChest;
    private CardView btn;
    private MeasureData measure;
    private double weight, biceps, thigh, waist, chest;
    private Date date;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measure);
        db = new DBHelper(this);
        measure = new MeasureData();

        tbWeight = (EditText) findViewById(R.id.tbAddWeight);
        tbBiceps = (EditText) findViewById(R.id.tbAddBiceps);
        tbThigh = (EditText) findViewById(R.id.tbAddThigh);
        tbWaist = (EditText) findViewById(R.id.tbAddWaist);
        tbChest = (EditText) findViewById(R.id.tbAddChest);
        btn = (CardView) findViewById(R.id.btnAddMeasure);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                date = new Date();
                measure.setWeight(Double.parseDouble((tbWeight.getText().toString())));
                measure.setBiceps(Double.parseDouble((tbBiceps.getText().toString())));
                measure.setThigh(Double.parseDouble((tbThigh.getText().toString())));
                measure.setWaist(Double.parseDouble((tbWaist.getText().toString())));
                measure.setChest(Double.parseDouble((tbChest.getText().toString())));
                measure.setWeight(Double.parseDouble((tbWeight.getText().toString())));
                measure.setDate(date);

                db.addMeasure(measure);

               finish();


            }
        });

    }
}
