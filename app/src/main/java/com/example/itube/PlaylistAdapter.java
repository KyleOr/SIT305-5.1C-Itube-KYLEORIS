package com.example.itube;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    private final ArrayList<String> playlistUrls;

    public PlaylistAdapter(ArrayList<String> playlistUrls) {
        this.playlistUrls = playlistUrls;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = playlistUrls.get(position);
        holder.urlTextView.setText(url);
    }

    @Override
    public int getItemCount() {
        return playlistUrls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView urlTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            urlTextView = itemView.findViewById(R.id.textViewUrl);
        }
    }
}