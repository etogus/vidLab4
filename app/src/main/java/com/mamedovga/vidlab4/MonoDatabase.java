package com.mamedovga.vidlab4;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Note.class}, version = 1)
public abstract class MonoDatabase extends RoomDatabase {
    private static MonoDatabase instance;

    public abstract MonoDao monoDao();

    public static synchronized MonoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MonoDatabase.class, "mono_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
