package de.hdmstuttgart.movietracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import de.hdmstuttgart.movietracker.model.Movie;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> getAll();

    @Insert
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);
}
