package de.hdmstuttgart.movietracker;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Movie {

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