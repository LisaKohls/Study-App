package de.hdmstuttgart.movietracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdmstuttgart.movietracker.model.Movie;

public class SearchActivity extends AppCompatActivity {

    private SearchViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.searchRecyclerView);
        //Ausrichtung vertikal-->Linear manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(new MovieListAdapter(this));

        EditText searchEditText =findViewById(R.id.searchMovieTitleEditText);
        Button searchButton = findViewById(R.id.searchMovieTitleButton);

        //Bei button click, wird vom Text Feld der Inhalt geholt und kann noch bearbeitet werden
        searchButton.setOnClickListener(v ->{
            String searchText = searchEditText.getText().toString();

            //Liste der Filme
            viewModel.getMoviesByKeyword(searchText).observe(this,movies -> {
                recyclerView.setAdapter(new MovieListAdapter(this, movies, (movie, position) -> {

                    viewModel.saveMovie(movie);
                    //Activity beenden
                    finish();
            }));
            });
        });

    }
}
