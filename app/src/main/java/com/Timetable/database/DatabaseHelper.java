package com.Timetable.database;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.Timetable.Task;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public  abstract class DatabaseHelper extends RoomDatabase {

    public abstract DatabaseAction databaseAction();
    private static volatile DatabaseHelper DatabaseHelper;
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
    @Override
    public void clearAllTables() {

    }
}

