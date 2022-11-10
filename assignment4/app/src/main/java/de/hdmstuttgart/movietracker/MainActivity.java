package de.hdmstuttgart.movietracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.homeRecyclerView);
        //Ausrichtung vertikal-->Linear manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new MovieListAdapter(
                MovieDb.getInstance().savedMovies,
                (movie, position)  -> {
                    MovieListAdapter adapter = (MovieListAdapter) recyclerView.getAdapter();
                    if (adapter == null) {
                        return;
                    }
                    MovieDb.getInstance().removeMovie(movie);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, 1);
                }
                );
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
