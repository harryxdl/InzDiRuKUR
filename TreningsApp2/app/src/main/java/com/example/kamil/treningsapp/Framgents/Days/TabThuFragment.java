package com.example.kamil.treningsapp.Framgents.Days;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.kamil.treningsapp.Adapter.ExpandableListAdapter;
import com.example.kamil.treningsapp.DBHelper;
import com.example.kamil.treningsapp.Models.AppUserData;
import com.example.kamil.treningsapp.Models.MealData;
import com.example.kamil.treningsapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.round;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabThuFragment extends Fragment {


    public TabThuFragment() {
        // Required empty public constructor
    }


    public class ViewHolder {
        TextView cal;
        TextView carbo;
        TextView fat;
        TextView protein;
        TextView calcLeft;
    }

    private final static String DAY = "czwartek";
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    ViewHolder holder;
    private List<String> listDataHeader;
    private HashMap<String, List<MealData>> listHash;
    private DBHelper dbhelper;
    private double carbo, fat, protein;
    private int cal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabmon, container, false);
        dbhelper = new DBHelper(getActivity());
        listView = (ExpandableListView) view.findViewById(R.id.lvlExpMon);
        AppUserData appUser = dbhelper.getUser(1);
        holder = new ViewHolder() ;
        fat = protein = carbo = 0;
        cal = 0;
        // adddata();
        holder.cal = (TextView) view.findViewById(R.id.foodCal3);
        holder.protein = (TextView) view.findViewById(R.id.foodProtein3);
        holder.carbo = (TextView) view.findViewById(R.id.foodCarbo3);
        holder.fat = (TextView) view.findViewById(R.id.foodFat3);
        holder.calcLeft = (TextView) view.findViewById(R.id.foodLeftCalFat3);
        holder.calcLeft.setText(Integer.toString(appUser.getEnergy()));
        initData();
        setTxt();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listHash, DAY);
        listView.setAdapter(listAdapter);

        return view;
    }

    private void setTxt() {
        holder.cal.setText(Integer.toString(cal));
        holder.protein.setText(Double.toString(round(protein)));
        holder.carbo.setText(Double.toString(round(carbo)));
        holder.fat.setText(Double.toString(round(fat)));
    }
    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Śniadanie");
        listDataHeader.add("Lunch");
        listDataHeader.add("Obiad");
        listDataHeader.add("Kolacja");
        listDataHeader.add("Przekąski");
        listDataHeader.add("Inne");
        // dbhelper.mealList("poniedziałek", "śniadanie");
        List<MealData> foodSniadanie = dbhelper.getMealList(DAY, "Śniadanie");
        List<MealData> foodLunch = dbhelper.getMealList(DAY, "Lunch");
        List<MealData> foodObiad = dbhelper.getMealList(DAY, "Obiad");
        List<MealData> foodKolacja = dbhelper.getMealList(DAY, "Kolacja");
        List<MealData> foodPrzekaski = dbhelper.getMealList(DAY, "Przekąski");
        List<MealData> foodInne = dbhelper.getMealList(DAY, "Inne");

        calcNutries(foodSniadanie);
        calcNutries(foodLunch);
        calcNutries(foodObiad);
        calcNutries(foodKolacja);
        calcNutries(foodPrzekaski);
        calcNutries(foodInne);
        listHash.put(listDataHeader.get(0), foodSniadanie);
        listHash.put(listDataHeader.get(1), foodLunch);
        listHash.put(listDataHeader.get(2), foodObiad);
        listHash.put(listDataHeader.get(3), foodKolacja);
        listHash.put(listDataHeader.get(4), foodPrzekaski);
        listHash.put(listDataHeader.get(5), foodInne);

    }

    private void calcNutries(List<MealData> meal) {
        if (!meal.isEmpty()) {
            for (Iterator<MealData> iter = meal.iterator(); iter.hasNext(); ) {
                MealData element = iter.next();
                cal += element.getEnergy() * (element.getWeight() / 100);
                protein += element.getProtein() * element.getWeight() / 100;
                fat += element.getFat() * element.getWeight() / 100;
                ;
                carbo += element.getCarbo() * element.getWeight() / 100;
                ;

            }
        }
    }

}