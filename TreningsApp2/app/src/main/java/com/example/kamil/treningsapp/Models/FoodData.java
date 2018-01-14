package com.example.kamil.treningsapp.Models;

/**
 * Created by Kamil on 15.12.2017.
 */

public class FoodData {
    int iId;
    String name;
    String tag;
    int energy;
    double protein;
    double carbo;
    double fat;

    public FoodData()
    {

    }

    public FoodData(String name, String tag, int energy, double protein, double carbo, double fat) {
        this.name = name;
        this.tag = tag;
        this.energy = energy;
        this.protein = protein;
        this.carbo = carbo;
        this.fat = fat;
    }

    public FoodData(int iId, String name, int energy, double protein, double carbo, double fat) {
        this.iId = iId;
        this.name = name;
        this.energy = energy;
        this.protein = protein;
        this.carbo = carbo;
        this.fat = fat;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
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

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
