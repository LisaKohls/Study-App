package de.hdmstuttgart.movietracker;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import de.hdmstuttgart.movietracker.api.ApiMovie;
import de.hdmstuttgart.movietracker.api.MoviesApi;
import de.hdmstuttgart.movietracker.database.AppDatabase;
import de.hdmstuttgart.movietracker.database.MovieDao;
import de.hdmstuttgart.movietracker.model.Movie;
import de.hdmstuttgart.movietracker.model.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private final MovieDao movieDao;

    private final MoviesApi moviesApi;

    private final List<Movie> movies = new ArrayList<>();

    private final LiveData<List<Movie>> moviesLiveData;

    private final MutableLiveData<List<Movie>> resultsLiveData;

    //erstellt Instanz von der Datenbank
    public MoviesRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        movieDao = db.movieDao();
        moviesLiveData = movieDao.getAll();
        resultsLiveData = new MutableLiveData<>();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        moviesApi = retrofit.create(MoviesApi.class);

    }

    public LiveData<List<Movie>> getSavedMovies() {
        return moviesLiveData;
    }

    public LiveData<List<Movie>> getMoviesByKeyword(String keyword) {

        resultsLiveData.setValue(Collections.emptyList());
        moviesApi.getSearchResult(keyword, "21e90b45").enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchResponse> call, @NonNull Response<SearchResponse> response) {
                List<ApiMovie> movieList = response.body().Search;
                resultsLiveData.postValue(movieList.stream().map(movie -> movie.toMovie()).collect(Collectors.toList()));
            }

            @Override
            public void onFailure(@NonNull Call<SearchResponse> call,@NonNull Throwable t) {
                Log.e("Oh noo.", t.getMessage());
            }

        });
        return resultsLiveData;

    }

    public void insert(Movie movie){
        AppDatabase.databaseWriteExecutor.execute(() -> movieDao.insert(movie));
    }

    public void delete(Movie movie){
        AppDatabase.databaseWriteExecutor.execute(() -> movieDao.delete(movie));
    }

}

