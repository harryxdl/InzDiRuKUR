package com.example.kamil.treningsapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.kamil.treningsapp.Activity.FoodFinderActivity;
import com.example.kamil.treningsapp.Models.FoodData;
import com.example.kamil.treningsapp.Models.MealData;
import com.example.kamil.treningsapp.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Kamil on 11.01.2018.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private String day;
    private List<String> listDataHeader;
    private HashMap<String,List<MealData>> listHashMap;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<MealData>> listHashMap, String day) {
        this.context = context;
        this.day = day;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public MealData getChild(int i, int i1) {
        return listHashMap.get(listDataHeader.get(i)).get(i1); // i = Group Item , i1 = ChildItem
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        final String headerTitle = (String)getGroup(i);
        Button btn;
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group,null);
            btn = (Button) view.findViewById(R.id.AddMealFood);
            if(getChildrenCount(i)>0) {
                btn.setVisibility(View.GONE);
            }
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(context, FoodFinderActivity.class);
                    intent.putExtra("planDiet", true);
                    intent.putExtra("day",day);
                    intent.putExtra("mealName", headerTitle);

                    ((Activity) context).startActivityForResult(intent, 10001);
                }
            });
        }


        TextView lblListHeader = (TextView)view.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String headerTitle = (String)getGroup(i);
        final String childText = "Nazwa " + getChild(i,i1).getName().toString();
        int kalorie = (getChild(i,i1).getEnergy()*getChild(i,i1).getWeight())/100;
        final String childText2 = "Kalorie " + Integer.toString(kalorie);
        final String childText3 =  "Waga " + Integer.toString(getChild(i,i1).getWeight());
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item,null);
        }
        TextView txtListChild = (TextView)view.findViewById(R.id.lblListItemName);
        TextView txtListChild2 = (TextView)view.findViewById(R.id.lblListItemCal);
        TextView txtListChild3 = (TextView)view.findViewById(R.id.lblListItemWeight);
        txtListChild.setText(childText);
        txtListChild2.setText(childText2);
        txtListChild3.setText(childText3);
        if( listHashMap.get(listDataHeader.get(i)).size() == (i1+1)) {
            FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.fabAddMeal);
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FoodFinderActivity.class);
                    intent.putExtra("planDiet", true);
                    intent.putExtra("day", day);
                    intent.putExtra("mealName", headerTitle);
                    ((Activity) context).startActivityForResult(intent, 10001);
                }
            });
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
