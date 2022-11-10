package de.hdmstuttgart.movietracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

//Verhindert das Instanziieren von mehr als ein mal
//man bekommt immer nur eine Instanz
//Daten können synchronisiert und geteilt werden
class MovieDb {

    //nur eine Instanz erzeugt
    private static MovieDb instance;

    //gespeicherte Filme
    public List<Movie> savedMovies = new ArrayList<> ();
    //liste von beginn an befüllt
    public List<Movie> movies = new ArrayList<> ();

    private MovieDb() {
    }

    public static MovieDb getInstance() {
        //ob es existiert
        if (instance == null) {
            //wenn nicht neue instanz erzeugt
            instance = new MovieDb();

            //static
            instance.movies.add(new Movie("Dr. No",1962,"Sean Connery"));
            instance.movies.add(new Movie("From Russia with Love",1963,"Sean Connery"));
            instance.movies.add(new Movie("Goldfinger",1964,"Sean Connery"));
            instance.movies.add(new Movie("Thunderball",1965,"Sean Connery"));
            instance.movies.add(new Movie("You Only Live Twice",1967,"Sean Connery"));
        }
        //instanz direkt zurückgegeben
        return instance;
    }

    void saveMovie(Movie movie){
        savedMovies.add(movie);
    }

    void removeMovie(Movie movie){
        savedMovies.remove(movie);
    }

    List<Movie> searchMoviesByKeyWord(String keyword) {
        //Filme nach keyword durchsucht und Ergebnisse ausgegeben
        return movies.stream()
                //creates a new array filled with elements that pass a test provided by a function
                .filter(movie -> movie.getTitle()
                        .toLowerCase()
                        .contains(keyword.toLowerCase()))
                        .collect(Collectors.toList());
    }

}
