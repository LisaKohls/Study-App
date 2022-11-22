package de.hdmstuttgart.movietracker.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


//erstellt Tabelle, liest aus und erstellt spalten
//definiert Datenbank strukturen
@Entity
public class Movie {
    //Erstellung von Room, id festlegen
    @PrimaryKey(autoGenerate = true)
    public int uid;

    //daten der einzelnen elementen ändern sich nicht deshalb final,
    //Kann mit getter aufgerufen werden
    private final String title;
    private final String year;
    private final String poster;

    public String getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public Movie(
            String title,
            String year,
            String poster
    ) {
        this.title = title;
        this.year = year;
        this.poster = poster;
    }
}