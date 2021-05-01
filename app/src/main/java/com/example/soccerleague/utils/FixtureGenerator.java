package com.example.soccerleague.utils;

import java.util.Arrays;

public class FixtureGenerator {


    public static String[][] generateFixture(int size) {

        // Find out how many teams we want fixtures for.
        int teams = size;


        // If odd number of teams add a "ghost".
        boolean ghost = false;
        if (teams % 2 == 1) {
            teams++;
            ghost = true;
        }

        // Generate the fixtures using the cyclic algorithm.
        int totalRounds = teams - 1;
        int matchesPerRound = teams / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];

        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                rounds[round][match] = (home + 1) + " v " + (away + 1);
            }
        }

        // Interleave so that home and away games are fairly evenly dispersed.
        String[][] interleaved = new String[totalRounds][matchesPerRound];

        int evn = 0;
        int odd = (teams / 2);
        for (int i = 0; i < rounds.length; i++) {
            if (i % 2 == 0) {
                interleaved[i] = rounds[evn++];
            } else {
                interleaved[i] = rounds[odd++];
            }
        }

        rounds = interleaved;

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int round = 0; round < rounds.length; round++) {
            if (round % 2 == 1) {
                rounds[round][0] = flip(rounds[round][0]);
            }
        }


        return rounds;
    }

    private static String flip(String match) {
        String[] components = match.split(" v ");
        return components[1] + " v " + components[0];
    }

}
