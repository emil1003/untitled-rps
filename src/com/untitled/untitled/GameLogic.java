package com.untitled.untitled;

import com.untitled.untitled.helpers.ArgumentParser;
import com.untitled.untitled.helpers.Log;

import java.util.ArrayList;

public class GameLogic {

    private ArgumentParser argumentParser;

    private boolean DEBUG = true;
    private String LOG = "GameLogic";

    private int numPlayers;
    public ArrayList<Player> players = new ArrayList<>();

    public void setupPlayers(int num) {
        for (int i = 0; i < num; i++) {
            players.add(new Player(i));
        }
    }

    public void setArgumentParser(ArgumentParser argumentParser) {
        this.argumentParser = argumentParser;
    }

    public void setMove(int player, Move move) throws NullPointerException {
        players.get(player).setMove(move._type);
    }

    public void calculateBattles() {
        if (DEBUG)
            Log.info(LOG, "Calculating battles");

        for (Player player : players) {
            for (Player opponent : players) {
                if (opponent == player)
                    continue;

                if (player._move.drawsMove(opponent._move._type)) {
                    Log.info(LOG,String.format("Player %s draws with player %s",player.numPlayer,opponent.numPlayer));
                } else {
                    if (player._move.beatsMove(opponent._move._type)) {
                        Log.info(LOG,String.format("Player %s beats player %s",player.numPlayer,opponent.numPlayer));
                        player.numWins += 1;
                    } else {
                        Log.info(LOG,String.format("Player %s lost to player %s",player.numPlayer,opponent.numPlayer));
                        player.numLosses += 1;
                    }
                }
            }
        }

        for (Player player : players) {
            Log.info(LOG,String.format("Player %s has %s wins and %s losses",player.numPlayer,player.numWins,player.numLosses));

            if (argumentParser.getResetStatsArgument()) {
                player.numWins = 0;
                player.numLosses = 0;
            }
        }

        if (argumentParser.getResetStatsArgument())
            Log.info(LOG,"Stats reset\n");
    }

}
