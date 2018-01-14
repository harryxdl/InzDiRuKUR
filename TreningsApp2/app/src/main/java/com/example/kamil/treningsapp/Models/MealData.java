package com.example.kamil.treningsapp.Models;

/**
 * Created by Kamil on 11.01.2018.
 */

public class MealData {
    int iId;
    String day;
    String mealName;
    int weight;
    int foodId;
    String name;
    String tag;
    int energy;
    double protein;
    double carbo;
    double fat;

    public MealData(){}

    public MealData(int iId, String day, String mealName, int weight, int foodId, String name, String tag, int energy, double protein, double carbo, double fat) {
        this.iId = iId;
        this.day = day;
        this.mealName = mealName;
        this.weight = weight;
        this.foodId = foodId;
        this.name = name;
        this.tag = tag;
        this.energy = energy;
        this.protein = protein;
        this.carbo = carbo;
        this.fat = fat;
    }
    public MealData(String day, String mealName, int weight, int foodId, String name, String tag, int energy, double protein, double carbo, double fat) {
        this.day = day;
        this.mealName = mealName;
        this.weight = weight;
        this.foodId = foodId;
        this.name = name;
        this.tag = tag;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
}
