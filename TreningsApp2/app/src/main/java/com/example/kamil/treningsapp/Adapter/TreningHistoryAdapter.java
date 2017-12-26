package com.example.kamil.treningsapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kamil.treningsapp.DBData.TreningData;
import com.example.kamil.treningsapp.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Kamil on 15.10.2017.
 */

public class TreningHistoryAdapter extends ArrayAdapter<TreningData> {

    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat timerFormat = new SimpleDateFormat("mm:ss");
    private Context activity;
    private List<TreningData> treningDataList;

    public TreningHistoryAdapter(Context context, int resource, List<TreningData> data){
        super(context, resource, data);
        this.activity = context;
        this.treningDataList = data;
    }

    @Override
    public int getCount() {
        return treningDataList.size();
    }

    @Override
    public TreningData getItem(int position) {
        return treningDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.history_training_row, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.dataStart.setText("Data Start:\n"+timeFormat.format(treningDataList.get(position).getDateStart()));
        holder.dataEnd.setText("Data End:\n"+timeFormat.format(treningDataList.get(position).getDateEnd()));
        holder.distance.setText("Distance:\n "+ String.valueOf(treningDataList.get(position).getiDistacne())+ "m.");
        holder.time.setText("Time:\n "+timerFormat.format(treningDataList.get(position).getDateEnd().getTime()-treningDataList.get(position).getDateStart().getTime()));
        return convertView;
    }

    private class ViewHolder {
        private TextView dataStart;
        private TextView dataEnd;
        private TextView distance;
        private TextView time;

        public ViewHolder(View v) {
            dataStart = (TextView) v.findViewById(R.id.data_start);
            dataEnd = (TextView) v.findViewById(R.id.data_end);
            distance = (TextView) v.findViewById(R.id.distance);
            time = (TextView) v.findViewById(R.id.time);
        }
    }
}
