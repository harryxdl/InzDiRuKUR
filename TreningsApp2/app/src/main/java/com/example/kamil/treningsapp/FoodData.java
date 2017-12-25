package com.example.kamil.treningsapp;

/**
 * Created by Kamil on 15.12.2017.
 */

public class FoodData {
    int iId;
    String name;
    String tag;
    int energy;
    int protein;
    int carbo;
    int fat;

    public FoodData()
    {

    }

    public FoodData(String name, String tag, int energy, int protein, int carbo, int fat) {
        this.name = name;
        this.tag = tag;
        this.energy = energy;
        this.protein = protein;
        this.carbo = carbo;
        this.fat = fat;
    }

    public FoodData(int iId, String name, int energy, int protein, int carbo, int fat) {
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

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbo() {
        return carbo;
    }

    public void setCarbo(int carbo) {
        this.carbo = carbo;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
