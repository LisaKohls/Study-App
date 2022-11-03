package de.hdmstuttgart.movietracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.homeRecyclerView);
        //Ausrichtung vertikal-->Linear manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        List<Movie> movies = new ArrayList<> ();
        movies.add(new Movie("Dr. No",1962,"Sean Connery"));
        movies.add(new Movie("From Russia with Love",1963,"Sean Connery"));
        movies.add(new Movie("Goldfinger",1964,"Sean Connery"));
        movies.add(new Movie("Thunderball",1965,"Sean Connery"));
        movies.add(new Movie("You Only Live Twice",1967,"Sean Connery"));

        recyclerView.setAdapter(new MovieListAdapter(movies));

    }
}