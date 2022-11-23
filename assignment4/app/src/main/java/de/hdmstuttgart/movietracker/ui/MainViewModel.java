package de.hdmstuttgart.movietracker.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import de.hdmstuttgart.movietracker.database.AppDatabase;
import de.hdmstuttgart.movietracker.database.MoviesRepository;
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

    public LiveData<List<Movie>> getSavedMovies() {
        return moviesLiveData;
    }

    public void removeMovie(Movie movie) {
        repository.delete(movie);
    }
}

