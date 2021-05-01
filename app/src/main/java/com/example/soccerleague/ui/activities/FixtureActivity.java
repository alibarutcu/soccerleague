package com.example.soccerleague.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.soccerleague.R;
import com.example.soccerleague.adapters.TabAdapter;
import com.example.soccerleague.models.TeamModel;
import com.example.soccerleague.viewmodels.TeamListViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FixtureActivity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tab;
    private ViewPager viewPager;



    ArrayList<TeamModel> teamList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixture);

        teamList = (ArrayList<TeamModel>) getIntent().getSerializableExtra("key");


        viewPager =  findViewById(R.id.viewPager);
        tab = findViewById(R.id.tabLayout);

        int listSize;

        if(teamList.size() % 2 == 0)
            listSize = teamList.size();
        else
            listSize = teamList.size()+1;//bir takım bye geçeceği için ghost bir takım oluşturdum

        for (int k = 1; k <= (listSize-1)*2  ; k++) {
            tab.addTab(tab.newTab().setText("Week " + k));
        }

        adapter = new TabAdapter
                (getSupportFragmentManager(), (listSize-1)*2);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
    }
    public ArrayList<TeamModel> sendData() {
        return teamList;
    }

    public static Intent newIntent(Context caller, ArrayList<TeamModel> teams) {
        Intent intent = new Intent(caller, FixtureActivity.class);
        intent.putParcelableArrayListExtra("key",  teams);
        return intent;
    }

}
