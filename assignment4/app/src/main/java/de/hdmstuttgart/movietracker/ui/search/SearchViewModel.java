package de.hdmstuttgart.movietracker.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import de.hdmstuttgart.movietracker.database.MoviesRepository;
import de.hdmstuttgart.movietracker.model.Movie;

public class SearchViewModel extends AndroidViewModel {

    private final MoviesRepository repository;

    public SearchViewModel(@NonNull Application application){
        super(application);
        //repository Schnittstelle zur Datenbank
       repository = new MoviesRepository(application);
    }

    List<Movie> getMoviesByKeyword(String keyword) {
        return repository.getMoviesByKeyword(keyword);
    }

    public void saveMovie(Movie movie){
        repository.insert(movie);
    }

}