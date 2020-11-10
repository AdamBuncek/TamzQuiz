package com.example.myquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "QUIZGAME.db";

    public static final String COLUMN_QUESTIONS_QUESTION = "question";
    public static final String COLUMN_QUESTIONS_ANSWER1 = "answer1";
    public static final String COLUMN_QUESTIONS_ANSWER2 = "answer2";
    public static final String COLUMN_QUESTIONS_ANSWER3 = "answer3";
    public static final String COLUMN_QUESTIONS_ANSWERTRUE = "answerTrue";
    public static final String COLUMN_QUESTIONS_CATEGORY = "category";

    public static final String COLUMN_CATEGORY_NAME = "name";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE category " + "(id_c INTEGER PRIMARY KEY, name TEXT)");
        db.execSQL("CREATE TABLE questions " + "(id_q INTEGER PRIMARY KEY, question TEXT, answer1 TEXT, answer2 TEXT, answer3 TEXT, answerTrue TEXT, category INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS questions");
        onCreate(db);
    }

    public boolean insertCategory(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", "history");
        long insertedId = db.insert("category", null, cv);
        if (insertedId == -1) return false;

        cv.put("name", "geography");
        insertedId = db.insert("category", null, cv);
        if (insertedId == -1) return false;

        cv.put("name", "it");
        insertedId = db.insert("category", null, cv);
        if (insertedId == -1) return false;
        return true;
    }

    public boolean insertQuestion(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("question", "Hlavni mesto prajzske?");
        cv.put("answer1", "Ostrava");
        cv.put("answer2", "Opava");
        cv.put("answer3", "Brno");
        cv.put("answerTrue", "Hlucin");
        cv.put("category", 2);
        long insertedId = db.insert("questions", null, cv);
        if (insertedId == -1) return false;

        cv.put("question", "Kdy zacala 2. svetova valka?");
        cv.put("answer1", "2020");
        cv.put("answer2", "1620");
        cv.put("answer3", "1914");
        cv.put("answerTrue", "1939");
        cv.put("category", 1);
        insertedId = db.insert("questions", null, cv);
        if (insertedId == -1) return false;
        return true;
    }

    public ArrayList<String> getItemList()
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from questions", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String name = res.getString(res.getColumnIndex(COLUMN_QUESTIONS_QUESTION));
            String answer = res.getString(res.getColumnIndex(COLUMN_QUESTIONS_ANSWERTRUE));
            arrayList.add(name + " " + answer);
            res.moveToNext();
        }

        return arrayList;
    }
}
