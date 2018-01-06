package com.example.kamil.treningsapp.Models;

/**
 * Created by Kamil on 25.12.2017.
 */

public class AppUserData {
    int Id;
    int sex;
    int energy;
    int protein;
    int carbo;
    int fat;
    double physical_activity;
    double expectations;
    int age;
    int height;
    double weight;

    public AppUserData(int id, int sex, int energy, int protein, int carbo, int fat, double physical_activity, double expectations, int age, int height, double weight) {
        Id = id;
        this.sex = sex;
        this.energy = energy;
        this.protein = protein;
        this.carbo = carbo;
        this.fat = fat;
        this.physical_activity = physical_activity;
        this.expectations = expectations;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }
    public AppUserData(){}

    public AppUserData(int sex, int energy, int protein, int carbo, int fat, double physical_activity, double expectations, int age, int height, double weight) {
        this.sex = sex;
        this.energy = energy;
        this.protein = protein;
        this.carbo = carbo;
        this.fat = fat;
        this.physical_activity = physical_activity;
        this.expectations = expectations;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public double getPhysical_activity() {
        return physical_activity;
    }

    public void setPhysical_activity(double physical_activity) {
        this.physical_activity = physical_activity;
    }

    public double getExpectations() {
        return expectations;
    }

    public void setExpectations(double expectations) {
        this.expectations = expectations;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
