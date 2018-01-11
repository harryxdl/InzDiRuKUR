package com.example.kamil.treningsapp.Adapter;

/**
 * Created by Kamil on 15.12.2017.
 */

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Locale;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.view.View.OnClickListener;

        import com.example.kamil.treningsapp.DBHelper;
        import com.example.kamil.treningsapp.Models.FoodData;
        import com.example.kamil.treningsapp.R;
        import com.example.kamil.treningsapp.Activity.SingleFoodView;

public class FoodListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<FoodData> foodList = null;
    private ArrayList<FoodData> arraylist;
    private boolean fromDiet = false;
    private String mealName, day;
    private DBHelper dbHelper;
    private EditText weight;
    private Button btnSave;

    public FoodListViewAdapter(Context context, List<FoodData> worldpopulationlist, String day, String mealName, Boolean fromDiet) {
        mContext = context;
        this.fromDiet = fromDiet;
        this.mealName = mealName;
        this.day = day;
        this.foodList = worldpopulationlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<FoodData>();
        this.arraylist.addAll(foodList);
    }

    public class ViewHolder {
        TextView id;
        TextView name;
        TextView cal;
        TextView carbo;
        TextView fat;
        TextView protein;
    }
    public ViewHolder dialogHolder;
    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public FoodData getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        dbHelper = new DBHelper(mContext);
        if (view == null) {
            holder = new ViewHolder();
            dialogHolder = new ViewHolder();
            view = inflater.inflate(R.layout.foodlistview_item, null);
            // Locate the TextViews in listview_item.xml
           //holder.id = (TextView) view.findViewById(R.id.foodId);
            holder.name = (TextView) view.findViewById(R.id.foodName);
            holder.cal = (TextView) view.findViewById(R.id.foodCal);
            holder.protein = (TextView) view.findViewById(R.id.foodProtein);
            holder.carbo = (TextView) view.findViewById(R.id.foodCarbo);
            holder.fat = (TextView) view.findViewById(R.id.foodFat);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        //holder.id.setText(Integer.toString(foodList.get(position).getiId()));
        holder.name.setText(foodList.get(position).getName());
        holder.cal.setText(Integer.toString(foodList.get(position).getEnergy()));
        holder.protein.setText(Integer.toString(foodList.get(position).getProtein()));
        holder.carbo.setText(Integer.toString(foodList.get(position).getCarbo()));
        holder.fat.setText(Integer.toString(foodList.get(position).getFat()));

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(!fromDiet) {
                    // Send single item click data to SingleItemView Class
                    Intent intent = new Intent(mContext, SingleFoodView.class);
                    // Pass all data rank
                    //intent.putExtra("id",(foodList.get(position).getiId()));
                    intent.putExtra("name", (foodList.get(position).getName()));
                    intent.putExtra("cal", (foodList.get(position).getEnergy()));
                    intent.putExtra("protein", (foodList.get(position).getProtein()));
                    intent.putExtra("carbo", (foodList.get(position).getCarbo()));
                    intent.putExtra("fat", (foodList.get(position).getFat()));
                    mContext.startActivity(intent);
                }
                else{

                    final Dialog dialog = new Dialog(mContext);
                    dialog.setTitle("Dodaj Produkt do Diety");
                    dialog.setContentView(R.layout.dialog_add_meal);

                    btnSave = (Button) dialog.findViewById(R.id.btnAddMeal);
                    weight = (EditText) dialog.findViewById(R.id.foodWeight2) ;
                    dialogHolder.name = (TextView) dialog.findViewById(R.id.foodName2);
                    dialogHolder.cal = (TextView) dialog.findViewById(R.id.foodCal2);
                    dialogHolder.protein = (TextView) dialog.findViewById(R.id.foodProtein2);
                    dialogHolder.carbo = (TextView) dialog.findViewById(R.id.foodCarbo2);
                    dialogHolder.fat = (TextView) dialog.findViewById(R.id.foodFat2);

                    dialogHolder.name.setText(foodList.get(position).getName());
                    dialogHolder.cal.setText(Integer.toString(foodList.get(position).getEnergy()));
                    dialogHolder.protein.setText(Integer.toString(foodList.get(position).getProtein()));
                    dialogHolder.carbo.setText(Integer.toString(foodList.get(position).getCarbo()));
                    dialogHolder.fat.setText(Integer.toString(foodList.get(position).getFat()));
                    dialog.show();

                    btnSave.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){

                            dbHelper.addMeal(day,mealName,Integer.parseInt(String.valueOf(weight.getText())), foodList.get(position).getiId());
                            dialog.cancel();
                            ((Activity)mContext).finish();

                        }
                    });
                }
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        foodList.clear();
        if (charText.length() == 0) {
            foodList.addAll(arraylist);
        }
        else
        {
            for (FoodData wp : arraylist)
            {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText) || wp.getTag().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    foodList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void DialogAddMeal()
    {


    }
}