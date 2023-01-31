package com.example.mynotes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1,entities = {DataClass.class})
public abstract  class AppDatabase extends RoomDatabase {

    private static AppDatabase noteDB;
    public  abstract TaskDao taskDao();

    public static AppDatabase getInstance(Context context)
    {
        if (noteDB == null)
        {
            noteDB = creatingInstance(context);
        }
        return noteDB;

    }

    private static AppDatabase creatingInstance(Context context) {

        return Room.databaseBuilder(context,AppDatabase.class,"NoteApp").allowMainThreadQueries().build();
    }
}
