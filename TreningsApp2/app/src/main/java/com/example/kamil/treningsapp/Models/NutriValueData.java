package com.example.kamil.treningsapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kamil on 10.12.2017.
 */

public class NutriValueData implements Parcelable {

    int iId;
    int energy;
    int age;
    int height;
    boolean sex;
    double protein;
    double carbo;
    double psychicalActivity;
    double fat;
    double expectation;
    double weight;

    public NutriValueData(int iId, boolean sex, double protein, double carbo, double psychicalActivity, double fat, int energy, double expectation, int age, double weight, int height) {
        this.iId = iId;
        this.sex = sex;
        this.protein = protein;
        this.carbo = carbo;
        this.psychicalActivity = psychicalActivity;
        this.fat = fat;
        this.energy = energy;
        this.expectation = expectation;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public NutriValueData(boolean sex, double protein, double carbo, double psychicalActivity, double fat, int energy, double expectation, int age, double weight, int height) {
        this.sex = sex;
        this.protein = protein;
        this.carbo = carbo;
        this.psychicalActivity = psychicalActivity;
        this.fat = fat;
        this.energy = energy;
        this.expectation = expectation;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbo() {
        return carbo;
    }

    public void setCarbo(double carbo) {
        this.carbo = carbo;
    }

    public double getPsychicalActivity() {
        return psychicalActivity;
    }

    public void setPsychicalActivity(double psychicalActivity) {
        this.psychicalActivity = psychicalActivity;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public double getExpectation() {
        return expectation;
    }

    public void setExpectation(double expectation) {
        this.expectation = expectation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.iId);
        dest.writeInt(this.energy);
        dest.writeInt(this.age);
        dest.writeInt(this.height);
        dest.writeByte((byte)(this.sex? 1: 0));
        dest.writeDouble(this.fat);
        dest.writeDouble(this.carbo);
        dest.writeDouble(this.protein);
        dest.writeDouble(this.psychicalActivity);
        dest.writeDouble(this.expectation);
        dest.writeDouble(this.weight);

    }
    protected NutriValueData(Parcel in) {
        this.iId = in.readInt();

        this.energy =  in.readInt();
         this.age = in.readInt();
        this.height =  in.readInt();
        this.sex = in.readByte()!=0;
        this.protein = in.readDouble();
        this.carbo = in.readDouble();
        this.psychicalActivity = in.readDouble();;
        this.fat = in.readDouble();;
        this.expectation = in.readDouble();;
        this.weight = in.readDouble();;

    }
    public static final Parcelable.Creator<NutriValueData> CREATOR = new Parcelable.Creator<NutriValueData>() {
        @Override
        public NutriValueData createFromParcel(Parcel source) {
            return new NutriValueData(source);
        }

        @Override
        public NutriValueData[] newArray(int size) {
            return new NutriValueData[size];
        }
    };
}
