package de.hdmstuttgart.movietracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.hdmstuttgart.movietracker.model.Movie;

//Wertet live Daten aus
public class MainViewModel extends AndroidViewModel {
    //informiert bei Veränderungen
    private final LiveData<List<Movie>> moviesLiveData;

    private final MoviesRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        //repository Schnittstelle zur Datenbank
        repository = new MoviesRepository(application);

        moviesLiveData = repository.getSavedMovies();
    }

    LiveData<List<Movie>> getSavedMovies() {
        return repository.getSavedMovies();
    }

    public void removeMovie(Movie movie) {
        repository.delete(movie);
    }
}

