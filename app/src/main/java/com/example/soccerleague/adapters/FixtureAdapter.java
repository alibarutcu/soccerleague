package com.example.soccerleague.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccerleague.R;
import com.example.soccerleague.models.MatchModel;
import com.example.soccerleague.models.TeamModel;

import java.util.ArrayList;
import java.util.List;

public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.FixtureHolder> {

    private ArrayList<MatchModel> matches = new ArrayList<>();


    @NonNull
    @Override
    public FixtureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_item,parent,false);
        return new FixtureAdapter.FixtureHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FixtureHolder holder, int position) {
        MatchModel currentTeam = matches.get(position);
        holder.textViewName1.setText(currentTeam.getTeam1().getName());
        holder.textViewName2.setText(currentTeam.getTeam2().getName());
    }



    @Override
    public int getItemCount() {
        return matches.size();
    }

    public void setMatches(ArrayList<MatchModel> matches){
        this.matches = matches;
        notifyDataSetChanged();

    }

    class FixtureHolder extends RecyclerView.ViewHolder{
        private TextView textViewName1;
        private TextView textViewName2;


        public FixtureHolder(@NonNull View itemView) {
            super(itemView);
            textViewName1 = itemView.findViewById(R.id.textView);
            textViewName2 = itemView.findViewById(R.id.textView2);

        }
    }
}
