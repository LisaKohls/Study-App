package de.hdmstuttgart.movietracker.database;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.hdmstuttgart.movietracker.model.Movie;
//Klasse zum Datenspeichern
//version muss erhöht werden bei Veränderungen
@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        //es wird nur eine Datenbank Instanz erzeugt

        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class,"moviesDb")
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
