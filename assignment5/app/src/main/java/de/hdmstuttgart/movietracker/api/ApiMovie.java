package de.hdmstuttgart.movietracker.api;

import de.hdmstuttgart.movietracker.model.Movie;

public class ApiMovie {
    public String Title;
    public String Year;
    public String imdbID;
    public String Type;
    public String Poster;

    public Movie toMovie(){
        return new Movie(
                Title,
                Year,
                Poster
        );
    }
}
