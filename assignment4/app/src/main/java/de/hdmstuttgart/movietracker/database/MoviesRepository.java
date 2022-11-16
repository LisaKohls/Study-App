package de.hdmstuttgart.movietracker.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.hdmstuttgart.movietracker.model.Movie;

public class MoviesRepository {

    private final MovieDao movieDao;

    private final List<Movie> movies = new ArrayList<>();
    private final LiveData<List<Movie>> moviesLiveData;
    //erstellt Instanz von der Datenbank
    public MoviesRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        movieDao = db.movieDao();
        moviesLiveData = movieDao.getAll();

        movies.add(new Movie("Dr. No",1962,"Sean Connery"));
        movies.add(new Movie("From Russia with Love",1963,"Sean Connery"));
        movies.add(new Movie("Goldfinger",1964,"Sean Connery"));
        movies.add(new Movie("Thunderball",1965,"Sean Connery"));
        movies.add(new Movie("You Only Live Twice",1967,"Sean Connery"));
    }

    public LiveData<List<Movie>> getSavedMovies() {
        return moviesLiveData;
    }

    public List<Movie> getMoviesByKeyword(String keyword){
        return movies.stream()
                //creates a new array filled with elements that pass a test provided by a function
                .filter(movie -> movie.getTitle()
                        .toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void insert(Movie movie){
        AppDatabase.databaseWriteExecutor.execute(() -> movieDao.insert(movie));
    }

    public void delete(Movie movie){
        AppDatabase.databaseWriteExecutor.execute(() -> movieDao.delete(movie));
    }

}

