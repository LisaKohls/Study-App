package de.hdmstuttgart.movietracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import de.hdmstuttgart.movietracker.R;
import de.hdmstuttgart.movietracker.ui.MainViewModel;
import de.hdmstuttgart.movietracker.ui.MovieListAdapter;
import de.hdmstuttgart.movietracker.ui.search.SearchActivity;

public class MainActivity extends AppCompatActivity {
    //1:37:01
    private RecyclerView recyclerView;
    private MovieListAdapter adapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.homeRecyclerView);
        //Ausrichtung vertikal-->Linear manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        adapter = new MovieListAdapter(
                new ArrayList<>(),
                (movie,position) -> {}
        );

        //wenn ein neuer Film hinzugefügt wird, wird ein neuer Adapter erstellt
        viewModel.getSavedMovies().observe(this, movies -> {
                if(movies == null) return;
                adapter = new MovieListAdapter(
                        movies,
                        (movie, position)  -> {
                            MovieListAdapter adapter = (MovieListAdapter) recyclerView.getAdapter();
                            if (adapter == null) {
                                return;
                            }
                            viewModel.removeMovie(movie);
                            adapter.notifyItemRemoved(position);
                            adapter.notifyItemRangeChanged(position, 1);
                    }
        );
                 recyclerView.setAdapter(adapter);

        });

        recyclerView.setAdapter(adapter);


        Button searchButton = findViewById(R.id.searchBtn);


        recyclerView.setAdapter(adapter);

        //Bei Klick neue Activity
        searchButton.setOnClickListener(v -> {
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
        });
    }

        //sortieren Element am Ende der Liste hinzufügen
        public void onResume(){
            super.onResume();
            adapter.notifyDataSetChanged();
        }

    }
