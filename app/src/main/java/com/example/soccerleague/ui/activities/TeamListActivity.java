package com.example.soccerleague.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.soccerleague.R;
import com.example.soccerleague.adapters.TeamAdapter;
import com.example.soccerleague.models.TeamModel;
import com.example.soccerleague.viewmodels.TeamListViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TeamListActivity extends AppCompatActivity {

    //ViewModel
    private TeamListViewModel teamListViewModel;

    private FloatingActionButton floatingActionButton;
    private ArrayList<TeamModel> teamList = new ArrayList<>();

    Switch switchDM;
    SharedPreferences sharedPreferences = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        teamListViewModel = new ViewModelProvider(this).get(TeamListViewModel.class);


        getTeamsApi();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        TeamAdapter adapter =  new TeamAdapter();
        recyclerView.setAdapter(adapter);

        //Calling the observers
        ObserveAnyChange(adapter);


        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FixtureActivity.class);
                intent.putParcelableArrayListExtra("key",  teamList);
                startActivity(FixtureActivity.newIntent(TeamListActivity.this, teamList));

            }
        });

    }




    //Observing any data change
    private void ObserveAnyChange(TeamAdapter adapter){
        teamListViewModel.getTeams().observe(this, new Observer<List<TeamModel>>() {
            @Override
            public void onChanged(List<TeamModel> teamModels) {
                //Observing for data change
                if(teamModels != null) {
                    adapter.setTeams(teamModels);
                    teamList.clear();
                    teamList.addAll(teamModels);
                }
                else{
                    Toast.makeText(TeamListActivity.this, "Empty List", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        switchDM = (Switch)menu.findItem(R.id.item1)
                .getActionView();

        sharedPreferences = getSharedPreferences("theme_settings",0);
        Boolean nightMode = sharedPreferences.getBoolean("night_mode",false);
        if(nightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switchDM.setChecked(nightMode);

        switchDM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    switchDM.setChecked(true);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode",true);
                    editor.commit();
                }
                else{

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    switchDM.setChecked(false);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode",false);
                    editor.commit();
                }
            }
        });


        return true;
    }

    //4-Calling method in MainActivity
    private void getTeamsApi(){
        teamListViewModel.getTeamsApi();
    }


}






