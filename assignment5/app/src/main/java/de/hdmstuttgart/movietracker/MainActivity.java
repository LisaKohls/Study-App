package de.hdmstuttgart.movietracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieListAdapter adapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.homeRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        adapter = new MovieListAdapter(
                this,
                new ArrayList<>(),
                (movie,position) -> {}
        );

        //wenn ein neuer Film hinzugefügt wird, wird ein neuer Adapter erstellt
        viewModel.getSavedMovies().observe(this, movies -> {
                if(movies == null) return;
                adapter = new MovieListAdapter(
                        this,
                        movies,
                        (movie, position) -> {
            //Funktion wenn auf movie geklickt wird--> Film gelöscht
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
