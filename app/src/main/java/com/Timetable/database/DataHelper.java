package com.Timetable.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABaSE_NAME = "T";
    SQLiteDatabase T;
    public DataHelper(Context context) {
        super(context, DATABaSE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table Tasks(humanID integer primary key autoincrement, humanname text, Username text, Password text, Gender text, Birthdate text, Job text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Tasks");
    }
    public boolean insert(String humanname,String Username,String Password, String Gender,String Birthdate,String Job) {
        ContentValues row = new ContentValues();
        row.put("humanname", humanname);
        row.put("Username", Username);
        row.put("Password", Password);
        row.put("Gender", Gender);
        row.put("Birthdate", Birthdate);
        row.put("Job", Job);
        T = this.getWritableDatabase();
        long r = T.insert("Tasks", null, row);
        if (r == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean checkUser(String Username) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from Tasks where Username =?",new String[]{Username});
        if(c.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean CheckUser(String Username, String Password) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from Tasks where Username =? and Password =?",new String[]{Username,Password});
        if(c.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean updatepassword(String Username,String Password)
    {
        ContentValues row=new ContentValues();
        row.put("Password",Password);
        T=this.getWritableDatabase();
        long r=T.update("Tasks",row,"Username = ?",new String[]{Username});
        if(r==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
