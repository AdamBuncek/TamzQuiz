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
    private static final int DATABASE_VERSION = 4;

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

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable(){
        Question g1 = new Question("Jaké je hlavní město ČR?", "Ostrava", "Praha", "Berlín", "New York", 2, 2);
        insertQuestion(g1);
        Question g2 = new Question("Největší stát na světě je...?", "Česká republika", "Rusko", "Čína", "Vatikán", 2, 2);
        insertQuestion(g2);
        Question g3 = new Question("Jaká je nejvyšší hora ČR?", "Praděd", "Lysá hora", "Králický Sněžník", "Sněžka", 4, 2);
        insertQuestion(g3);
        Question g4 = new Question("Na které hoře Beskyd stojí socha Radegasta?", "na Soláni", "na Lysé hoře", "na Radhošti", "na Smrku", 3, 2);
        insertQuestion(g4);
        Question g5 = new Question("Který hrad u Karlových Varů má stejný název jako část lidského těla?", "Loket", "Brada", "Kost", "Dlaň", 1, 2);
        insertQuestion(g5);
        Question g6 = new Question("Jak se jmenuje nejhlubší propast v ČR?", "Macocha", "Balcarka", "Hranická", "Malcarka", 3, 2);
        insertQuestion(g6);
        Question g7 = new Question("Co je rozlohou největší vodní plocha u nás?", "rybník Rožmberk", "Nechranická přehrada", "přehrada Šance", "Lipenská přehrada", 4, 2);
        insertQuestion(g7);
        Question g8 = new Question("Ve kterém městě se u nás koná Zlatá tretra?", "v Praze", "v Hradci Králové", "v Brně", "v Ostravě", 4, 2);
        insertQuestion(g8);
        Question g9 = new Question("Přes kterou řeku vede nejstarší dochovaný most v čechách?", "přes Ohři", "přes Vltavu", "přes Otavu", "přes Labe", 3, 2);
        insertQuestion(g9);
        Question g10 = new Question("Ve které chráněné krajinné oblasti leží zřícenina hradu Trosky?", "v Českém krasu", "v Českém ráji", "v Moravksém krasu", "v Českém Švýcarsku", 2, 2);
        insertQuestion(g10);

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
        Question h8 = new Question("Co měli ve znaku Chodové?", "hlavu koně", "hlavu orla", "hlavu psa", "hlavu hada", 3, 1);
        insertQuestion(h8);
        Question h9 = new Question("Ve kterém století přišli Slované do Čech?", "v 5. století", "v 7. století", "v 9. století", "v 3. století", 1, 1);
        insertQuestion(h9);
        Question h10 = new Question("Čím se stal Karel IV. v roce 1347?", "českým králem", "vdovcem", "nebožtíkem", "italským králem", 1, 1);
        insertQuestion(h10);

        Question s1 = new Question("Kolik hráčů je ve vodě za jeden kompletní tým ve vodním pólu?", "5", "6", "7", "9", 1, 3);
        insertQuestion(s1);
        Question s2 = new Question("Kdo trénoval českou hokejovou reprezentaci na úspěšném MS ve Vídni v roce 1995?", "Josef Augusta", "Ivan Hlinka", "Luděk Bukač", "Vladimír Růžička", 3, 3);
        insertQuestion(s2);
        Question s3 = new Question("Jak se nazývá sport, který používá k pohybu vzduchem padákový kluzák?", "kiting", "paragliding", "tubing", "parasliding", 2, 3);
        insertQuestion(s3);
        Question s4 = new Question("Který z uvedených fotbalistů nemá křestní jméno Tomáš?", "Hübschman", "Lafata", "Sivok", "Rosický", 2, 3);
        insertQuestion(s4);
        Question s5 = new Question("Který z uvedených fotbalistů nemá křestní jméno Tomáš?", "Hübschman", "Lafata", "Sivok", "Rosický", 2, 3);
        insertQuestion(s5);
        Question s6 = new Question("Po kolika letech pauzy se tenis v roce 1988 vrátil mezi olympojské sporty?", "po 20", "po 48", "po 64", "po 50", 3, 3);
        insertQuestion(s6);
        Question s7 = new Question("Který sport znamená v překladu: prázdné ruce, beze zbraně?", "sumo", "judo", "karate", "krav maga", 3, 3);
        insertQuestion(s7);
        Question s8 = new Question("Kdo byl trenérem hokejového mužstva ČR na ZOH v Naganu?", "Josef Augusta", "Ivan Hlinka", "Luděk Bukač", "Vladimír Růžička", 2, 3);
        insertQuestion(s8);
        Question s9 = new Question("V kterém roce byl Josef Masopust vyhlášen fotbalistou Evropy?", "1954", "1962", "1958", "1964", 2, 3);
        insertQuestion(s9);
        Question s10 = new Question("Jak se jmenuje naše první zlatá medailistka z olympiády?", "Dana Zátopková", "Eva Bosáková", "Olga Fikotová", "Věra Čáslavská", 1, 3);
        insertQuestion(s10);

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

    public ArrayList<Question> getQuestionsByCategory(int category)
    {
        ArrayList<Question> arrayList = new ArrayList<Question>();
        db = getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + QuestionTable.TABLE_NAME + " WHERE " + QuestionTable.COLUMN_CATEGORY + "=" + category, null );
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
