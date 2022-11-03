package de.hdmstuttgart.movietracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        RecyclerView recyclerView = findViewById(R.id.searchRecyclerView);
        //Ausrichtung vertikal-->Linear manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(new MovieListAdapter());

        EditText searchEditText =findViewById(R.id.searchMovieTitleEditText);
        Button searchButton = findViewById(R.id.searchMovieTitleButton);

        //Bei button click, wird vom Text Feld der Inhalt geholt und kann noch bearbeitet werden
        searchButton.setOnClickListener(v ->{
            String searchText = searchEditText.getText().toString();

            //Liste der Filme
            List<Movie> movies = MovieDb.getInstance().searchMoviesByKeyWord(searchText);
            //Adapter zeigt Liste an
            recyclerView.setAdapter(new MovieListAdapter(movies, (movie, position) -> {
                MovieDb.getInstance().saveMovie(movie);
                //Activity beenden
                finish();
            }));
        });

    }
}
//notifyDataSetChanged();
//
//            //rechner bescheid geben von Handlung
//            notifyItemRemoved(position);
//            notifyItemRangeChanged(position,1);