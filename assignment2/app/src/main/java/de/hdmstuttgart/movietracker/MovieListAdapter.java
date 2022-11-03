package de.hdmstuttgart.movietracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    //Lädt items
    private final List<Movie> movieList;

    public MovieListAdapter(List<Movie> movieList){
        this.movieList = movieList;
    }

    //übergibt Daten an MovieViewHolder Klasse
    //layout laden und erstellen, merken mit view holder
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_layout, parent, false);
        return new MovieViewHolder(view);
    }

    //Es werden nur die items geladen, die angezeigt werden müssen
    //zum anzeigen
    //beinhaltet alle 10 elemente die gleichzeitig angezeigt werden können
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        //Elemente die angezeigt werden

        Movie movie = movieList.get(position);

        //View holder Objekt, informationen in title usw. reinsetzen
        holder.titleTextView.setText(movie.getTitle());
        holder.yearTextView.setText(String.format(Locale.getDefault(),"%d",movie.getYear()));
        holder.actorTextView.setText(movie.getActor());

        holder.itemView.setOnClickListener(v -> {
            movieList.remove(position);
            notifyDataSetChanged();

            //rechner bescheid geben von Handlung
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,1);
        });
    }

    //size von der Liste
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    //befüllt die Daten
    static class MovieViewHolder extends RecyclerView.ViewHolder {

        //zum laden, neu befüllen, kontakt zum layout

        TextView titleTextView;
        TextView yearTextView;
        TextView actorTextView;

        public MovieViewHolder(View itemView) {
            //find view by ids muss nur einmal aufgerufen werden
            //nur beim ersten Mal laden, erstellen wird Element geholt
            //merkt sich layout
            super(itemView);

            titleTextView =itemView.findViewById(R.id.title);
            yearTextView = itemView.findViewById(R.id.year);
            actorTextView = itemView.findViewById(R.id.actor);
        }
    }

}
