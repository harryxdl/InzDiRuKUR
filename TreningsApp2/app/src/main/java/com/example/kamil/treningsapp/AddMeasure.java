package com.example.kamil.treningsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.kamil.treningsapp.Data.DBHelper;

public class AddMeasure extends AppCompatActivity {
    private EditText tbWeight, tb, tbAge;

    DBHelper dbHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measure);
    }
}
