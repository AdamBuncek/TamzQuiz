package com.example.myquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myquiz.QuizDB.*;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "QUIZGAMEDB.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QuestionTable.TABLE_NAME + "(" + QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " + QuestionTable.COLUMN_OPTION1 + " TEXT, " + QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " + QuestionTable.COLUMN_OPTION4 + " TEXT, " + QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionTable.COLUMN_CATEGORY + " INTEGER" + ")";

       // final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " + CategoryTable.TABLE_NAME + "( idC INTEGER PRIMARY KEY, " + CategoryTable.COLUMN_NAME + " TEXT " + ")";

       // db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        //fillCategoryTable();
        fillQuestionsTable();
        //db.execSQL("CREATE TABLE questions " + "(id_q INTEGER PRIMARY KEY, question TEXT, answer1 TEXT, answer2 TEXT, answer3 TEXT, answerTrue TEXT, category INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoryTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }
/*
    private void fillCategoryTable(){
        Category history = new Category("history");
        insertCategory(history);

        Category geography = new Category("geography");
        insertCategory(geography);

        //Category history = new Category("history");
    }

    private void insertCategory(Category category){
        ContentValues cv = new ContentValues();
        cv.put(CategoryTable.COLUMN_NAME, category.getName());
        db.insert(CategoryTable.TABLE_NAME, null, cv);

    }
*/
    private void fillQuestionsTable(){
        Question g1 = new Question("Jaké je hlavní město ČR?", "Ostrava", "Praha", "Berlín", "New York", 2, 2);
        insertQuestion(g1);
        Question g2 = new Question("Největší stát na světě je...?", "Česká republika", "Rusko", "Čína", "Vatikán", 2, 2);
        insertQuestion(g2);
        Question g3 = new Question("Jaká je nejvyšší hora ČR?", "Praděd", "Lysá hora", "Králický Sněžník", "Sněžka", 2, 2);
        insertQuestion(g3);

        Question h1 = new Question("Kdy skončila 2. světová válka?", "1939", "2019", "1945", "1918", 3, 1);
        insertQuestion(h1);
        Question h2 = new Question("V kterém roce proběhla Bitva na Bílé hoře?", "1620", "1720", "2020", "1020", 1, 1);
        insertQuestion(h2);
        Question h3 = new Question("Jaké přízvisko měl český kníže Boleslav II.?", "ukrutný", "pobožný", "chrabrý", "ošklivý", 2, 1);
        insertQuestion(h3);
        Question h4 = new Question("Který panovník nechal postavit pevnost Terezín?", "Jindřich VIII.", "Napoleon", "Leonidas", "Josef II.", 4, 1);
        insertQuestion(h4);
        Question h5 = new Question("Jak se jmenoval hustiský vojevůdce?", "Jan Žižka", "Julius Caesar", "Max Žižka", "Jan Hus", 1, 1);
        insertQuestion(h5);
        Question h6 = new Question("Jak se nazývalo sídlo panovníků Velké Moravy?", "Vyšehrad", "Vyškov", "Vizovice", "Velehrad", 4, 1);
        insertQuestion(h6);
        Question h7 = new Question("Které řemeslo se nejprve učil Tomáš G. Masaryk?", "kovářské", "zahradnické", "hornické", "krejčovské", 1, 1);
        insertQuestion(h7);

    }

    private void insertQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_CATEGORY, question.getCategory());
        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions()
    {
        ArrayList<Question> arrayList = new ArrayList<Question>();
        db = getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + QuestionTable.TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Question question = new Question();
            question.setQuestion(res.getString(res.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
            question.setOption1(res.getString(res.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
            question.setOption2(res.getString(res.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
            question.setOption3(res.getString(res.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
            question.setOption4(res.getString(res.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
            question.setAnswerNr(res.getInt(res.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
            question.setCategory(res.getInt(res.getColumnIndex(QuestionTable.COLUMN_CATEGORY)));
            arrayList.add(question);
            res.moveToNext();
        }

        res.close();
        return arrayList;
    }
}
