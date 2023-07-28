package com.jihyungpark.gpacalculater;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.app.ActionBar;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "subject.db";
    public static final String TABLE_NAME = "subject_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "SUBJECTNAME";

    public static final String COL3 = "ITEM1";
    public static final String COL4 = "MARK1";
    public static final String COL5 = "FULLMARK1";
    public static final String COL6 = "PERCENTAGE1";
    public static final String COL7 = "CONVERT1";

    public static final String COL8 = "ITEM2";
    public static final String COL9 = "MARK2";
    public static final String COL10 = "FULLMARK2";
    public static final String COL11 = "PERCENTAGE2";
    public static final String COL12 = "CONVERT2";

    public static final String COL13 = "ITEM3";
    public static final String COL14 = "MARK3";
    public static final String COL15 = "FULLMARK3";
    public static final String COL16 = "PERCENTAGE3";
    public static final String COL17 = "CONVERT3";

    public static final String COL18 = "ITEM4";
    public static final String COL19 = "MARK4";
    public static final String COL20 = "FULLMARK4";
    public static final String COL21 = "PERCENTAGE4";
    public static final String COL22 = "CONVERT4";

    public static final String COL23 = "ITEM5";
    public static final String COL24 = "MARK5";
    public static final String COL25 = "FULLMARK5";
    public static final String COL26 = "PERCENTAGE5";
    public static final String COL27 = "CONVERT5";

    public static final String COL28 = "ITEM6";
    public static final String COL29 = "MARK6";
    public static final String COL30 = "FULLMARK6";
    public static final String COL31 = "PERCENTAGE6";
    public static final String COL32 = "CONVERT6";

    public static final String COL33 = "ITEM7";
    public static final String COL34 = "MARK7";
    public static final String COL35 = "FULLMARK7";
    public static final String COL36 = "PERCENTAGE7";
    public static final String COL37 = "CONVERT7";

    public static final String COL38 = "ITEM8";
    public static final String COL39 = "MARK8";
    public static final String COL40 = "FULLMARK8";
    public static final String COL41 = "PERCENTAGE8";
    public static final String COL42 = "CONVERT8";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                            " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            " SUBJECTNAME TEXT, " +
                            " ITEM1 TEXT, MARK1 INTEGER, FULLMARK1 INTEGER, PERCENTAGE1 INTEGER, CONVERT1 DOUBLE, " +
                            " ITEM2 TEXT, MARK2 INTEGER, FULLMARK2 INTEGER, PERCENTAGE2 INTEGER, CONVERT2 DOUBLE, " +
                            " ITEM3 TEXT, MARK3 INTEGER, FULLMARK3 INTEGER, PERCENTAGE3 INTEGER, CONVERT3 DOUBLE, " +
                            " ITEM4 TEXT, MARK4 INTEGER, FULLMARK4 INTEGER, PERCENTAGE4 INTEGER, CONVERT4 DOUBLE, " +
                            " ITEM5 TEXT, MARK5 INTEGER, FULLMARK5 INTEGER, PERCENTAGE5 INTEGER, CONVERT5 DOUBLE, " +
                            " ITEM6 TEXT, MARK6 INTEGER, FULLMARK6 INTEGER, PERCENTAGE6 INTEGER, CONVERT6 DOUBLE, " +
                            " ITEM7 TEXT, MARK7 INTEGER, FULLMARK7 INTEGER, PERCENTAGE7 INTEGER, CONVERT7 DOUBLE, " +
                            " ITEM8 TEXT, MARK8 INTEGER, FULLMARK8 INTEGER, PERCENTAGE8 INTEGER, CONVERT8 DOUBLE )";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(("DROP IF TABLE EXISTS ") + TABLE_NAME);
        onCreate(db);
        db.close();
    }


    public boolean addData(String subjectName, String item1, String mark1, String fullMark1, String percentage1, String convert1,
                           String item2, String mark2, String fullMark2, String percentage2, String convert2,
                           String item3, String mark3, String fullMark3, String percentage3, String convert3,
                           String item4, String mark4, String fullMark4, String percentage4, String convert4,
                           String item5, String mark5, String fullMark5, String percentage5, String convert5,
                           String item6, String mark6, String fullMark6, String percentage6, String convert6,
                           String item7, String mark7, String fullMark7, String percentage7, String convert7,
                           String item8, String mark8, String fullMark8, String percentage8, String convert8){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, subjectName);
        contentValues.put(COL3, item1);
        contentValues.put(COL4, mark1);
        contentValues.put(COL5, fullMark1);
        contentValues.put(COL6, percentage1);
        contentValues.put(COL7, convert1);
        contentValues.put(COL8, item2);
        contentValues.put(COL9, mark2);
        contentValues.put(COL10, fullMark2);
        contentValues.put(COL11, percentage2);
        contentValues.put(COL12, convert2);
        contentValues.put(COL13, item3);
        contentValues.put(COL14, mark3);
        contentValues.put(COL15, fullMark3);
        contentValues.put(COL16, percentage3);
        contentValues.put(COL17, convert3);
        contentValues.put(COL18, item4);
        contentValues.put(COL19, mark4);
        contentValues.put(COL20, fullMark4);
        contentValues.put(COL21, percentage4);
        contentValues.put(COL22, convert4);
        contentValues.put(COL23, item5);
        contentValues.put(COL24, mark5);
        contentValues.put(COL25, fullMark5);
        contentValues.put(COL26, percentage5);
        contentValues.put(COL27, convert5);
        contentValues.put(COL28, item6);
        contentValues.put(COL29, mark6);
        contentValues.put(COL30, fullMark6);
        contentValues.put(COL31, percentage6);
        contentValues.put(COL32, convert6);
        contentValues.put(COL33, item7);
        contentValues.put(COL34, mark7);
        contentValues.put(COL35, fullMark7);
        contentValues.put(COL36, percentage7);
        contentValues.put(COL37, convert7);
        contentValues.put(COL38, item8);
        contentValues.put(COL39, mark8);
        contentValues.put(COL40, fullMark8);
        contentValues.put(COL41, percentage8);
        contentValues.put(COL42, convert8);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor showData(String subjectName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +COL2 +" = ?", new String[] { subjectName});

        return data;
    }

    public boolean updateData(String id, String subjectName, String item1, String mark1, String fullMark1, String percentage1, String convert1,
                           String item2, String mark2, String fullMark2, String percentage2, String convert2,
                           String item3, String mark3, String fullMark3, String percentage3, String convert3,
                           String item4, String mark4, String fullMark4, String percentage4, String convert4,
                           String item5, String mark5, String fullMark5, String percentage5, String convert5,
                           String item6, String mark6, String fullMark6, String percentage6, String convert6,
                           String item7, String mark7, String fullMark7, String percentage7, String convert7,
                           String item8, String mark8, String fullMark8, String percentage8, String convert8){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, subjectName);
        contentValues.put(COL3, item1);
        contentValues.put(COL4, mark1);
        contentValues.put(COL5, fullMark1);
        contentValues.put(COL6, percentage1);
        contentValues.put(COL7, convert1);
        contentValues.put(COL8, item2);
        contentValues.put(COL9, mark2);
        contentValues.put(COL10, fullMark2);
        contentValues.put(COL11, percentage2);
        contentValues.put(COL12, convert2);
        contentValues.put(COL13, item3);
        contentValues.put(COL14, mark3);
        contentValues.put(COL15, fullMark3);
        contentValues.put(COL16, percentage3);
        contentValues.put(COL17, convert3);
        contentValues.put(COL18, item4);
        contentValues.put(COL19, mark4);
        contentValues.put(COL20, fullMark4);
        contentValues.put(COL21, percentage4);
        contentValues.put(COL22, convert4);
        contentValues.put(COL23, item5);
        contentValues.put(COL24, mark5);
        contentValues.put(COL25, fullMark5);
        contentValues.put(COL26, percentage5);
        contentValues.put(COL27, convert5);
        contentValues.put(COL28, item6);
        contentValues.put(COL29, mark6);
        contentValues.put(COL30, fullMark6);
        contentValues.put(COL31, percentage6);
        contentValues.put(COL32, convert6);
        contentValues.put(COL33, item7);
        contentValues.put(COL34, mark7);
        contentValues.put(COL35, fullMark7);
        contentValues.put(COL36, percentage7);
        contentValues.put(COL37, convert7);
        contentValues.put(COL38, item8);
        contentValues.put(COL39, mark8);
        contentValues.put(COL40, fullMark8);
        contentValues.put(COL41, percentage8);
        contentValues.put(COL42, convert8);

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        db.close();
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

    public List<String> getAllSubjectName(){

        List<String> list = new ArrayList<String>();
        list.add("Select Subject");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()){
            do{
                list.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }
}
