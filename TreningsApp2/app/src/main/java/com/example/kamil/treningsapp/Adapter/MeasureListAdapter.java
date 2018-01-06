package com.example.kamil.treningsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kamil.treningsapp.Models.MeasureData;
import com.example.kamil.treningsapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 26.12.2017.
 */

public class MeasureListAdapter extends BaseAdapter {
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<MeasureData> measureList = null;
    private ArrayList<MeasureData> arraylist;
    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
    public MeasureListAdapter(Context context, List<MeasureData> AllmeasureList) {
        mContext = context;
        this.measureList = AllmeasureList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<MeasureData>();
        this.arraylist.addAll(measureList);
    }

    public class ViewHolder {
        TextView id;
        TextView weight;
        TextView biceps;
        TextView thigh;
        TextView waist;
        TextView chest;
        TextView date;

    }

    @Override
    public int getCount() {
        return measureList.size();
    }

    @Override
    public MeasureData getItem(int position) {
        return measureList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final MeasureListAdapter.ViewHolder holder;
        if (view == null) {
            holder = new MeasureListAdapter.ViewHolder();
            view = inflater.inflate(R.layout.measure_row, null);
            // Locate the TextViews in listview_item.xml
           // holder.id = (TextView) view.findViewById(R.id.tbmeasure);
            holder.weight = (TextView) view.findViewById(R.id.tbMeasureWeight);
            holder.biceps = (TextView) view.findViewById(R.id.tbMeasureBiceps);
            holder.thigh = (TextView) view.findViewById(R.id.tbMeasureThigh);
            holder.waist = (TextView) view.findViewById(R.id.tbMeasureWaist);
            holder.chest = (TextView) view.findViewById(R.id.tbMeasureChest);
            holder.date = (TextView) view.findViewById(R.id.tbDate_measure);
            view.setTag(holder);
        } else {
            holder = (MeasureListAdapter.ViewHolder) view.getTag();
        }
        // Set the results into TextViews
       // holder.id.setText(Integer.toString(measureList.get(position).getiId()));
        holder.weight.setText(Double.toString(measureList.get(position).getWeight()));
        holder.biceps.setText(Double.toString(measureList.get(position).getBiceps()));
        holder.thigh.setText(Double.toString(measureList.get(position).getThigh()));
        holder.waist.setText(Double.toString(measureList.get(position).getWaist()));
        holder.chest.setText(Double.toString(measureList.get(position).getChest()));
        holder.date.setText("Data Start:\n"+timeFormat.format(measureList.get(position).getDate()));
        // Listen for ListView Item Click
//        view.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // Send single item click data to SingleItemView Class
//                Intent intent = new Intent(mContext, SingleFoodView.class);
//                // Pass all data rank
//                intent.putExtra("id",(foodList.get(position).getiId()));
//                intent.putExtra("name",(foodList.get(position).getName()));
//                intent.putExtra("cal",(foodList.get(position).getEnergy()));
//                intent.putExtra("protein",(foodList.get(position).getProtein()));
//                intent.putExtra("carbo",(foodList.get(position).getCarbo()));
//                intent.putExtra("fat",(foodList.get(position).getFat()));
//                mContext.startActivity(intent);
//            }
//        });

        return view;
    }

}
