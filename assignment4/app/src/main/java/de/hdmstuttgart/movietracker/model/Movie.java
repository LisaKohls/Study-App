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
    private final int year;
    private final String actor;

    public Integer getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getActor() {
        return actor;
    }

    public Movie(
            String title,
            int year,
            String actor
    ) {
        this.title = title;
        this.year = year;
        this.actor = actor;
    }
}