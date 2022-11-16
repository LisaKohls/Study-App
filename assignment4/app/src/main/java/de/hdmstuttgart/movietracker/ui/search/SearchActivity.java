package de.hdmstuttgart.movietracker.ui.search;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdmstuttgart.movietracker.model.Movie;
import de.hdmstuttgart.movietracker.ui.MovieListAdapter;
import de.hdmstuttgart.movietracker.R;

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

        recyclerView.setAdapter(new MovieListAdapter());

        EditText searchEditText =findViewById(R.id.searchMovieTitleEditText);
        Button searchButton = findViewById(R.id.searchMovieTitleButton);

        //Bei button click, wird vom Text Feld der Inhalt geholt und kann noch bearbeitet werden
        searchButton.setOnClickListener(v ->{
            String searchText = searchEditText.getText().toString();

            //Liste der Filme
            List<Movie> movies = viewModel.getMoviesByKeyword(searchText);
            //Adapter zeigt Liste an
            recyclerView.setAdapter(new MovieListAdapter(movies, (movie, position) -> {

                viewModel.saveMovie(movie);
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

//new Thread(() -> {
//                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                            AppDatabase.class, "movieDb").build();
//
//                    db.movieDao().insert(movie);
//                }).start();