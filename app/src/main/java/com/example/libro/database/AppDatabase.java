package com.example.libro.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.libro.entities.Libro;
import com.example.libro.daos.LibroDao;

@Database(entities = {Libro.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LibroDao libroDao();

    public static AppDatabase getInstance(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "LIBRO_DB")
                .allowMainThreadQueries()
                .build();
    }


}
