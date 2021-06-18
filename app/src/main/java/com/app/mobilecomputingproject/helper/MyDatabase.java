package com.app.mobilecomputingproject.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.mobilecomputingproject.Models.InformationData;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    Context context;
    private static final String TABLE_NAME = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DETAILS = "details";
    private static final String KEY_CATEGORY = "category";


    public MyDatabase(@Nullable Context context) {
        super(context, "Jerusalem", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_DETAILS + " TEXT,"
                + KEY_CATEGORY + " INTEGER" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);

    }

    public void addRow(String name, String details, int category) {
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_DETAILS, details);
        cValues.put(KEY_CATEGORY, category);
        getWritableDatabase().insert(TABLE_NAME, null, cValues);
        getWritableDatabase().close();
    }
    public ArrayList<InformationData> getData(int id){

        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME +" Where "+KEY_CATEGORY+" = "+id, null);
        ArrayList<InformationData> productResponses = new ArrayList<>();
        while (c.moveToNext()) {
            InformationData informationData = new InformationData();
            informationData.setId(c.getInt(0));
            informationData.setName(c.getString(1));
            informationData.setDetails(c.getString(2));
            informationData.setCategory(c.getInt(3));
            productResponses.add(informationData);
        }
        return productResponses;
    }
}
