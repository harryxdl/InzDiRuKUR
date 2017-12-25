package com.example.kamil.treningsapp;

/**
 * Created by Kamil on 15.12.2017.
 */

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Locale;

        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;
        import android.view.View.OnClickListener;

public class FoodListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<FoodData> foodList = null;
    private ArrayList<FoodData> arraylist;

    public FoodListViewAdapter(Context context, List<FoodData> worldpopulationlist) {
        mContext = context;
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
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.foodlistview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.id = (TextView) view.findViewById(R.id.foodId);
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
        holder.id.setText(Integer.toString(foodList.get(position).getiId()));
        holder.name.setText(foodList.get(position).getName());
        holder.cal.setText(Integer.toString(foodList.get(position).getEnergy()));
        holder.protein.setText(Integer.toString(foodList.get(position).getProtein()));
        holder.carbo.setText(Integer.toString(foodList.get(position).getCarbo()));
        holder.fat.setText(Integer.toString(foodList.get(position).getFat()));

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleFoodView.class);
                // Pass all data rank
                intent.putExtra("id",(foodList.get(position).getiId()));
                intent.putExtra("name",(foodList.get(position).getName()));
                intent.putExtra("cal",(foodList.get(position).getEnergy()));
                intent.putExtra("protein",(foodList.get(position).getProtein()));
                intent.putExtra("carbo",(foodList.get(position).getCarbo()));
                intent.putExtra("fat",(foodList.get(position).getFat()));
                mContext.startActivity(intent);
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

}