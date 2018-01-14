package com.example.kamil.treningsapp.Activity;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;

import com.example.kamil.treningsapp.Adapter.FoodListViewAdapter;
import com.example.kamil.treningsapp.Framgents.AddFood;
import com.example.kamil.treningsapp.DBHelper;
import com.example.kamil.treningsapp.Models.FoodData;
import com.example.kamil.treningsapp.R;

public class FoodFinderActivity extends AppCompatActivity {

    // Declare Variables
    ListView list;
    FoodListViewAdapter adapter;
    EditText editsearch;
    Button btn;
    int[] ids;
    String[] names;
    String[] tags;
    int[] calories;
    int[] proteints;
    int[] carbos;
    int[] fats;
    boolean fromDiet = false;
    String day;
    String mealName;
    ArrayList<FoodData> arraylist = new ArrayList<FoodData>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Intent intent = getIntent();
            mealName = intent.getStringExtra("mealName");
            day = intent.getStringExtra("day");
            fromDiet = intent.getBooleanExtra("planDiet", false);
        }
        catch(Exception ex){

        }
        setContentView(R.layout.foodlistview_main);
        DBHelper db = new DBHelper(this);
        // Generate sample data
        ids = new int[]{1,2,3,4,5,6,7,8,9,10};
        names = new String[]{"Produkt1", "Produkt2", "Produkt3 States",
                "Produkt4", "Produkt5", "Produkt6", "Produkt7", "Produkt8",
                "Produkt9", "Produkt10"};
        tags = new String[]{"warzywa", "warzywa", "warzywa",
                "owoce", "owoce", "owoce", "mięso", "mięso",
                "fastfood", "fastfood"};
        calories = new int[]{100,200,322,444,555,677,733,822,922,103};
//        proteints = new int[]{100,200,322,444,555,677,733,822,922,103};
//        carbos = new int[]{100,200,322,444,555,677,733,822,922,103};
//        fats = new int[]{100,200,322,444,555,677,733,822,922,103};
        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);
        btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FoodFinderActivity.this, AddFood.class);
                startActivity(intent);
            }
    });
//        for (int i = 0; i < ids.length; i++) {
//            FoodData food = new FoodData(names[i], tags[i], calories[i],
//                    proteints[i], carbos[i],fats[i]);
//            db.addFood(food);
//        }

        // Pass results to FoodListViewAdapter Class
        adapter = new FoodListViewAdapter(this, db.getFoodList(), day, mealName, fromDiet);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (EditText) findViewById(R.id.search);



        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });


    }

    // Not using options menu in this tutorial
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

