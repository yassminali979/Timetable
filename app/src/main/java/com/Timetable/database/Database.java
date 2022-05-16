package com.Timetable.database;

import android.content.Context;

import androidx.room.Room;

public class Database {
    private Context C;
    private static Database Instance;
    private DatabaseHelper databaseHelper;
    private Database(Context C) {
        this.C = C;
        databaseHelper = Room.databaseBuilder(C, DatabaseHelper.class, "Task.db").fallbackToDestructiveMigration().build();
    }
    public static synchronized Database getInstance(Context C) {
        if (Instance == null) {
            Instance = new Database(C);
        }
        return Instance;
    }
    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }
}
