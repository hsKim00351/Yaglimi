package com.example.yaglimi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Pill.class}, version = 1, exportSchema = false)
public abstract class PillDB extends RoomDatabase {
    /*private static PillDB database;
    private static String DATABASE_NAME = "database";

    public synchronized static PillDB getInstance(Context context)
    {
        if (database == null)
        {
            database = Room.databaseBuilder(context.getApplicationContext(), PillDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
*/
    private static PillDB INSTANCE = null;
    public abstract PillDao pillDao();

    public static PillDB getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    PillDB.class, "pill.db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
