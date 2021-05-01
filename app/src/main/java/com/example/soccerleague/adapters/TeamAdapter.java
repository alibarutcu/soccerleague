package com.example.soccerleague.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccerleague.R;
import com.example.soccerleague.models.TeamModel;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamHolder> {
    private List<TeamModel> teams = new ArrayList<>();


    @NonNull
    @Override
    public TeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_item,parent,false);
        return new TeamHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamHolder holder, int position) {
        TeamModel currentTeam = teams.get(position);
        holder.textViewName.setText(position+1 +" - "+ currentTeam.getName());

    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void setTeams(List<TeamModel> teams){
        this.teams = teams;
        notifyDataSetChanged();

    }

    class TeamHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;


        public TeamHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
        }
    }
}
