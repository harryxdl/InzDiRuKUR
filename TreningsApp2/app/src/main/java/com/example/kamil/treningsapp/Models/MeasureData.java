package com.example.kamil.treningsapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Kamil on 10.12.2017.
 */

public class MeasureData implements Parcelable {
    int iId;
    double weight;
    double biceps;
    double thigh;
    double waist;
    double chest;
    Date date;

    public MeasureData(int iId, double weight, double biceps, double thigh, double waist, double chest, Date date) {
        this.iId = iId;
        this.weight = weight;
        this.biceps = biceps;
        this.thigh = thigh;
        this.waist = waist;
        this.chest = chest;
        this.date = date;
    }

    public MeasureData(double weight, double biceps, double thigh, double waist, double chest, Date date) {
        this.weight = weight;
        this.biceps = biceps;
        this.thigh = thigh;
        this.waist = waist;
        this.chest = chest;
        this.date = date;
    }
    public MeasureData(){}
    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBiceps() {
        return biceps;
    }

    public void setBiceps(double biceps) {
        this.biceps = biceps;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.iId);
        dest.writeDouble(this.weight);
        dest.writeDouble(this.biceps);
        dest.writeDouble(this.thigh);
        dest.writeDouble(this.waist);
        dest.writeDouble(this.chest);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);


    }
    protected MeasureData(Parcel in) {
        this.iId = in.readInt();

        this.weight =  in.readDouble();

        this.biceps = in.readDouble();
        this.thigh = in.readDouble();
        this.waist = in.readDouble();;
        this.chest = in.readDouble();;
        this.weight = in.readDouble();
        long tmp_date= in.readLong();
        this.date = tmp_date == -1 ? null : new Date(tmp_date);

    }
    public static final Parcelable.Creator<MeasureData> CREATOR = new Parcelable.Creator<MeasureData>() {
        @Override
        public MeasureData createFromParcel(Parcel source) {
            return new MeasureData(source);
        }

        @Override
        public MeasureData[] newArray(int size) {
            return new MeasureData[size];
        }
    };
}
