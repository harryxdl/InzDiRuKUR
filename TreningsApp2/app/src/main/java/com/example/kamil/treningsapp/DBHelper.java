package com.example.kamil.treningsapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kamil.treningsapp.Models.AppUserData;
import com.example.kamil.treningsapp.Models.FoodData;
import com.example.kamil.treningsapp.Models.MealData;
import com.example.kamil.treningsapp.Models.MeasureData;
import com.example.kamil.treningsapp.Models.TreningData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 15.10.2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    private final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    public static final String DATABASE_NAME = "MyDBName.db";

    private static final int DATABASE_VERSION = 14;

    public static final String TRENING_TABLE_NAME = "trenings";
    public static final String TRENING_COLUMN_ID = "id";
    public static final String TRENING_COLUMN_DATE_START = "date_start";
    public static final String TRENING_COLUMN_DATE_END = "date_end";
    public static final String TRENING_COLUMN_DISTANCE= "distance";
    public static final String TRENING_COLUMN_KCAL= "kcal";

    public static final String FOOD_TABLE_NAME = "food";
    public static final String FOOD_COLUMN_ID = "id";
    public static final String FOOD_COLUMN_FOOD_NAME = "food_name";
    public static final String FOOD_COLUMN_PROTEIN = "protein";
    public static final String FOOD_COLUMN_CARBO = "carbohydrates";
    public static final String FOOD_COLUMN_TAG = "tag";
    public static final String FOOD_COLUMN_FAT = "fat";
    public static final String FOOD_COLUMN_ENERGY = "energy_value";

    public static final String DIET_TABLE_NAME = "diet";
    public static final String DIET_COLUMN_ID = "id";
    public static final String DIET_COLUMN_DAY = "day";
    public static final String DIET_COLUMN_MEAL_NAME = "meal_name";
    public static final String DIET_COLUMN_WEIGHT = "weight" ;
    public static final String DIET_COLUMN_FOOD_ID = "food_id";

    public static final String USER_TABLE_NAME = "app_user";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_SEX = "sex";
    public static final String USER_COLUMN_CARBO = "carbo";
    public static final String USER_COLUMN_PROTEIN = "protein";
    public static final String USER_COLUMN_FAT = "fat";
    public static final String USER_COLUMN_ENERGY= "energy";
    public static final String USER_COLUMN_PHYSICAL_ACTIVITY = "physical_activity_value";
    public static final String USER_COLUMN_EXPECTATIONS = "expectations";
    public static final String USER_COLUMN_AGE = "age";
    public static final String USER_COLUMN_WEIGHT = "weight";
    public static final String USER_COLUMN_HEIGHT = "height";

    public static final String BODY_TABLE_NAME = "user_body";
    public static final String BODY_COLUMN_ID = "id";
    public static final String BODY_COLUMN_WEIGHT = "weight";
    public static final String BODY_COLUMN_BICEPS = "biceps";
    public static final String BODY_COLUMN_THIGH = "thigh";
    public static final String BODY_COLUMN_WAIST = "waist";
    public static final String BODY_COLUMN_CHEST = "chest";
    public static final String BODY_COLUMN_DATE = "date_measure";
    private Context mCtx;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        mCtx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TRENING_TABLE_NAME +
                        " (" + TRENING_COLUMN_ID + " integer primary key, " +
                        TRENING_COLUMN_DATE_START + " date, " +
                        TRENING_COLUMN_DATE_END + " date, " +
                        TRENING_COLUMN_DISTANCE + " integer," +
                        TRENING_COLUMN_KCAL + " integer" +
                        ")"
        );
        db.execSQL(
                 "create table " + USER_TABLE_NAME +
                        " (" + USER_COLUMN_ID + " integer primary key," +
                        USER_COLUMN_SEX + " INTEGER, " +
                        USER_COLUMN_PROTEIN + " real, " +
                        USER_COLUMN_CARBO + " real, " +
                        USER_COLUMN_FAT + " real, " +
                        USER_COLUMN_ENERGY + " integer, " +
                         USER_COLUMN_PHYSICAL_ACTIVITY + " real, " +
                        USER_COLUMN_EXPECTATIONS + " real, " +
                        USER_COLUMN_AGE + " integer, " +
                        USER_COLUMN_WEIGHT + " real, " +
                        USER_COLUMN_HEIGHT + " integer" +
                        ")"
        );
        db.execSQL(
                 "create table " + FOOD_TABLE_NAME +
                        " (" + FOOD_COLUMN_ID + " integer primary key," +
                         FOOD_COLUMN_FOOD_NAME + " text, " +
                         FOOD_COLUMN_TAG + " text, " +
                         FOOD_COLUMN_PROTEIN + " integer, " +
                         FOOD_COLUMN_CARBO + " integer, " +
                         FOOD_COLUMN_FAT + " integer, " +
                         FOOD_COLUMN_ENERGY + " integer" +
                        ")"
        );
        db.execSQL(
                "create table " + DIET_TABLE_NAME +
                        " (" + DIET_COLUMN_ID + " integer primary key," +
                        DIET_COLUMN_DAY +" text, " +
                        DIET_COLUMN_MEAL_NAME + " text, " +
                        DIET_COLUMN_WEIGHT + " integer, " +
                        DIET_COLUMN_FOOD_ID + " integer" +
                        ")"
        );
        db.execSQL(
                  "create table " + BODY_TABLE_NAME +
                        " (" + BODY_COLUMN_ID + " integer primary key," +
                        BODY_COLUMN_WEIGHT + " real, " +
                        BODY_COLUMN_BICEPS + " real, " +
                        BODY_COLUMN_THIGH + " real, " +
                        BODY_COLUMN_WAIST + " real, " +
                        BODY_COLUMN_CHEST + " real, " +
                        BODY_COLUMN_DATE + " date" +
                        ")"
        );
        //addProducts();
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TRENING_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DIET_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FOOD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BODY_TABLE_NAME);
        onCreate(db);
    }
    // Adding new Trening
    public void addTrening(TreningData trening) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TRENING_COLUMN_DATE_START, timeFormat.format(trening.getDateStart())); // Contact Name
        values.put(TRENING_COLUMN_DATE_END, timeFormat.format(trening.getDateEnd())); // Contact Phone Number
        values.put(TRENING_COLUMN_DISTANCE, trening.getiDistacne());
        values.put(TRENING_COLUMN_KCAL, trening.getKcal());

        // Inserting Row
        db.insert(TRENING_TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    public void addCalcNutriValue(TreningData trening) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TRENING_COLUMN_DATE_START, timeFormat.format(trening.getDateStart())); // Contact Name
        values.put(TRENING_COLUMN_DATE_END, timeFormat.format(trening.getDateEnd())); // Contact Phone Number
        values.put(TRENING_COLUMN_DISTANCE, trening.getiDistacne());

        // Inserting Row
        db.insert(TRENING_TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    public void addFood(FoodData food) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FOOD_COLUMN_FOOD_NAME, food.getName());
        values.put(FOOD_COLUMN_TAG, food.getTag());
        values.put(FOOD_COLUMN_PROTEIN, food.getProtein());
        values.put(FOOD_COLUMN_CARBO, food.getCarbo());
        values.put(FOOD_COLUMN_FAT, food.getFat());
        values.put(FOOD_COLUMN_ENERGY, food.getEnergy());
        // Inserting Row
        db.insert(FOOD_TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    // Getting single contact
    public TreningData getTrening(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TRENING_TABLE_NAME, new String[] { TRENING_COLUMN_ID,
                        TRENING_COLUMN_DATE_START, TRENING_COLUMN_DATE_END, TRENING_COLUMN_DISTANCE }, TRENING_COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        TreningData trening = null;
        try {
            trening = new TreningData(Integer.parseInt(cursor.getString(0)),
                    timeFormat.parse(cursor.getString(1)), timeFormat.parse(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // return trening
        return trening;

    }

    // Getting All trenings
    public List<TreningData> getAllTrenings() {
        List<TreningData> treninglist = new ArrayList<TreningData>();
        String selectQuery = "SELECT  * FROM " + TRENING_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                TreningData trening = new TreningData();
                trening.setiId(Integer.parseInt(cursor.getString(0)));
                try {
                    trening.setDateStart(timeFormat.parse(cursor.getString(1)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    trening.setDateEnd(timeFormat.parse(cursor.getString(2)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                trening.setiDistacne(Integer.parseInt(cursor.getString(3)));

                treninglist.add(trening);

            } while (cursor.moveToNext());
        }
        return treninglist;
    }
    ///////////////////////////////////////////////////////////////////////////////
    // DIET
    public List<FoodData> mealList(String day, String mealName) {
        List<FoodData> foodList = new ArrayList<FoodData>();
        String selectQuery = "SELECT  * FROM " + DIET_TABLE_NAME + " where day='" + day + "' and meal_name='" + mealName + "'" ;//;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                FoodData food = getFood(Integer.parseInt(cursor.getString(4)));

                foodList.add(food);

            } while (cursor.moveToNext());
        }
        return foodList;
    }
    public List<MealData> getMealList(String day, String mealName) {
        List<MealData> mealList = new ArrayList<MealData>();
        String selectQuery = "SELECT  * FROM " + DIET_TABLE_NAME + " where day='" + day + "' and meal_name='" + mealName + "'" ;//;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                FoodData food = getFood(Integer.parseInt(cursor.getString(4)));
                MealData meal = new MealData(cursor.getString(1), cursor.getString(2),Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)),food.getName(),food.getTag(),food.getEnergy(),food.getProtein(),
                        food.getCarbo(),food.getFat());
                mealList.add(meal);

            } while (cursor.moveToNext());
        }
        return mealList;
    }
    public void addMeal(String day, String mealName,int weight, int foodId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DIET_COLUMN_DAY, day);
        values.put(DIET_COLUMN_MEAL_NAME, mealName);
        values.put(DIET_COLUMN_WEIGHT, weight);
        values.put(DIET_COLUMN_FOOD_ID, foodId);
        db.insert(DIET_TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    ///////////////////////////////////////////////////////////////////////////////
    // FOOOOOOOOOOOOOOOOOOOOOD
    // Getting All food
    public List<FoodData> getFoodList() {
        List<FoodData> foodList = new ArrayList<FoodData>();
        String selectQuery = "SELECT  * FROM " + FOOD_TABLE_NAME + " order by tag" ;//;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                FoodData food = new FoodData();
                food.setiId(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                food.setTag(cursor.getString(2));
                food.setProtein(Double.parseDouble(cursor.getString(3)));
                food.setCarbo(Double.parseDouble(cursor.getString(4)));
                food.setFat(Double.parseDouble(cursor.getString(5)));
                food.setEnergy(Integer.parseInt(cursor.getString(6)));
                foodList.add(food);

            } while (cursor.moveToNext());
        }
        return foodList;
    }
    public FoodData getFood(int id) {
        FoodData food = new FoodData();
        String selectQuery = "SELECT  * FROM " + FOOD_TABLE_NAME + " where id=" +id ;//;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                food.setiId(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                food.setTag(cursor.getString(2));
                food.setProtein(Double.parseDouble(cursor.getString(3)));
                food.setCarbo(Double.parseDouble(cursor.getString(4)));
                food.setFat(Double.parseDouble(cursor.getString(5)));
                food.setEnergy(Integer.parseInt(cursor.getString(6)));

            } while (cursor.moveToNext());
        }
        return food;
    }
    // Getting treining Count
    public int getTreningsCount() {

        String countQuery = "SELECT  * FROM " + TRENING_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    // Updating single treining
    //public int updateContact(Contact contact) {}

    // Deleting single training
    public void deleeContact(TreningData trening) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TRENING_TABLE_NAME, TRENING_COLUMN_ID + " = ?",
                new String[] { String.valueOf(trening.getiId()) });
        db.close();
    }

    /////////////////////////////////////////////////////////////////////////////
    // APP USER DATA
    public void AddAppUser(AppUserData user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_SEX, user.getSex());
        values.put(USER_COLUMN_CARBO, user.getCarbo());
        values.put(USER_COLUMN_PROTEIN, user.getProtein());
        values.put(USER_COLUMN_FAT, user.getFat());
        values.put(USER_COLUMN_ENERGY, user.getEnergy());
        values.put(USER_COLUMN_PHYSICAL_ACTIVITY, user.getPhysical_activity());
        values.put(USER_COLUMN_EXPECTATIONS, user.getExpectations());
        values.put(USER_COLUMN_AGE, user.getAge());
        values.put(USER_COLUMN_WEIGHT, user.getWeight());
        values.put(USER_COLUMN_HEIGHT, user.getHeight());
        // Inserting Row
        db.insert(USER_TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    // Update User
    public void UpdateAppUser(AppUserData user, int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_SEX, user.getSex());
        values.put(USER_COLUMN_CARBO, user.getCarbo());
        values.put(USER_COLUMN_PROTEIN, user.getProtein());
        values.put(USER_COLUMN_FAT, user.getFat());
        values.put(USER_COLUMN_ENERGY, user.getEnergy());
        values.put(USER_COLUMN_PHYSICAL_ACTIVITY, user.getPhysical_activity());
        values.put(USER_COLUMN_EXPECTATIONS, user.getExpectations());
        values.put(USER_COLUMN_AGE, user.getAge());
        values.put(USER_COLUMN_WEIGHT, user.getWeight());
        values.put(USER_COLUMN_HEIGHT, user.getHeight());
        // Inserting Row
        db.update(USER_TABLE_NAME, values, USER_COLUMN_ID + "=" + id, null);
        db.close(); // Closing database connection
    }
    public AppUserData getUser(int id) {

        AppUserData userData = new AppUserData();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + USER_TABLE_NAME + " where id="+id ;//;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                userData.setId(Integer.parseInt(cursor.getString(0)));
                userData.setSex(Integer.parseInt(cursor.getString(1)));
                userData.setProtein(Integer.parseInt(cursor.getString(2)));
                userData.setCarbo(Integer.parseInt(cursor.getString(3)));
                userData.setFat(Integer.parseInt(cursor.getString(4)));
                userData.setEnergy(Integer.parseInt(cursor.getString(5)));
                userData.setPhysical_activity(Double.parseDouble(cursor.getString(6)));
                userData.setExpectations(Double.parseDouble(cursor.getString(7)));
                userData.setAge(Integer.parseInt(cursor.getString(8)));
                userData.setWeight(Double.parseDouble(cursor.getString(9)));
                userData.setHeight(Integer.parseInt(cursor.getString(10)));
            } while (cursor.moveToNext());
        }
        return userData;
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    // Measure body
    public void addMeasure(MeasureData measure) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BODY_COLUMN_WEIGHT, measure.getWeight());
        values.put(BODY_COLUMN_BICEPS, measure.getBiceps());
        values.put(BODY_COLUMN_THIGH, measure.getThigh());
        values.put(BODY_COLUMN_WAIST, measure.getWaist());
        values.put(BODY_COLUMN_CHEST, measure.getChest());
        values.put(BODY_COLUMN_DATE, timeFormat.format(measure.getDate()));

        // Inserting Row
        db.insert(BODY_TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    public List<MeasureData> getAllMeasureList() {
        List<MeasureData> measureList = new ArrayList<MeasureData>();
        String selectQuery = "SELECT  * FROM " + BODY_TABLE_NAME + " order by date_measure DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                MeasureData measure = new MeasureData();
                measure.setiId(Integer.parseInt(cursor.getString(0)));
                measure.setWeight(Double.parseDouble(cursor.getString(1)));
                measure.setBiceps(Double.parseDouble(cursor.getString(2)));
                measure.setThigh(Double.parseDouble(cursor.getString(3)));
                measure.setWaist(Double.parseDouble(cursor.getString(4)));
                measure.setChest(Double.parseDouble(cursor.getString(5)));
                try {
                    measure.setDate(timeFormat.parse(cursor.getString(6)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                measureList.add(measure);

            } while (cursor.moveToNext());
        }
        return measureList;
    }
    public void addProducts() {
        InputStream products = null;
        try {
            products = mCtx.getAssets().open("products.txt");
            InputStreamReader input = new InputStreamReader(products);
            BufferedReader buffreader = new BufferedReader(input, 2 * 1024);
            String line;
            while ((line = buffreader.readLine()) != null) {
                String[] point_t = line.split(",");
                FoodData food = new FoodData(point_t[0], point_t[1], Integer.parseInt(point_t[2]),
                                    Double.parseDouble(point_t[3]), Double.parseDouble(point_t[4]),
                                    Double.parseDouble(point_t[5]));
                addFood(food);
            }
            products.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteMeasure(MeasureData measure) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BODY_TABLE_NAME, BODY_COLUMN_ID + " = ?",
                new String[]{String.valueOf(measure.getiId())});
        db.close();
    }

}
