package com.example.soccerleague.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TeamModel implements Parcelable {

    //Model class for our teams
    private int id;
    private String name;

    public TeamModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected TeamModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TeamModel> CREATOR = new Creator<TeamModel>() {
        @Override
        public TeamModel createFromParcel(Parcel in) {
            return new TeamModel(in);
        }

        @Override
        public TeamModel[] newArray(int size) {
            return new TeamModel[size];
        }
    };
    //Getters..
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}