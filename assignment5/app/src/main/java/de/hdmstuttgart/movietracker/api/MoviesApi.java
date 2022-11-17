package de.hdmstuttgart.movietracker.api;


import de.hdmstuttgart.movietracker.model.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {
    //Erzeugt url
    @GET(".")
    Call<SearchResponse> getSearchResult(@Query("s")String movieSearch,@Query("apikey") String apiKey);
}

