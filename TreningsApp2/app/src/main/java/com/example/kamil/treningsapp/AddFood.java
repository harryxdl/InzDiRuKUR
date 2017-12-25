package com.example.kamil.treningsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddFood extends AppCompatActivity {

    private EditText tbName, tbTag, tbCal, tbProtein, tbCarbo, tbFat;
    private FoodData food;
    private Button btn;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        food = new FoodData();
        db = new DBHelper(this);
        tbName = (EditText) findViewById(R.id.editbxFoodName);
        tbTag = (EditText) findViewById(R.id.editbxFoodTag);
        tbCal = (EditText) findViewById(R.id.editbxFoodCal);
        tbProtein = (EditText) findViewById(R.id.editbxFoodProtein);
        tbCarbo = (EditText) findViewById(R.id.editbxFoodCarbo);
        tbFat = (EditText) findViewById(R.id.editbxFoodFat);
        btn = (Button) findViewById(R.id.btnAddFood) ;


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                food.setName(tbName.getText().toString());
                food.setTag(tbTag.getText().toString());
                food.setEnergy(Integer.parseInt(tbCal.getText().toString()));
                food.setProtein(Integer.parseInt(tbProtein.getText().toString()));
                food.setCarbo(Integer.parseInt(tbCarbo.getText().toString()));
                food.setFat(Integer.parseInt(tbFat.getText().toString()));

                db.addFood(food);

                Intent intent = new Intent(AddFood.this, FoodFinderActivity.class);
                finish();
                startActivity(intent);


            }
        });
    }
}
