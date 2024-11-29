package com.example.capitaleseuropajava;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Capital.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    // Método para obtener la instancia de la bd
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "db"
                    ).build();
        }
        return INSTANCE;
    }
    //Devuelve un DAO
    public abstract CapitalDao getCapitalDao();
}
