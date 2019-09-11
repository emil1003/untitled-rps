package com.untitled.untitled;

import com.untitled.untitled.handlers.ArduinoInputHandler;
import com.untitled.untitled.handlers.KeyboardInputHandler;
import com.untitled.untitled.helpers.Argument;
import com.untitled.untitled.helpers.ArgumentParser;
import com.untitled.untitled.helpers.Log;

public class Main {

    final String LOG = "Main";

    private ArgumentParser argumentParser;

    //Selects game handler
    static private GameInterface gameInterface;

    public static void main(String[] args) {
        new Main().init(args);
    }

    void init(String[] args) {
        argumentParser = new ArgumentParser();

        Log.info(LOG,"untitled Rock/Paper/Scissors game, version 0.0.1");

        String addNextAsArg = null;
        for (String arg : args) {

            if (arg.startsWith("--")) {
                addNextAsArg = arg.substring(2);
                continue;
            }

            if (arg.startsWith("-")) {
                addNextAsArg = arg.substring(1);
            }

            if (addNextAsArg != null) {

                argumentParser.addArgument(new Argument(addNextAsArg, arg));

                addNextAsArg = null;
            }
        }

        argumentParser.logArgs();

        switch (argumentParser.getInputHandlerArgument()) {
            case "keyboard":
                gameInterface = new KeyboardInputHandler();
                break;
            case "arduino":
                gameInterface = new ArduinoInputHandler();
        }

        Log.info(LOG, String.format("GameHandler: %s", gameInterface.getClass().getName()));

        if (!gameInterface.interfaceInit()) {
            Log.info(LOG,"GameHandler failed, exiting");
            System.exit(1);
        }

        gameLoop();
    }

    void gameLoop() {
        /*ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            players.add(new Player(i));
        }

        while (true) {
            for (Player player : players) {
                player.setMove(gameInterface.interfaceGetInput().toMoveType());
                Log.info(LOG + ": Player", String.format("player %s: %s", player.numPlayer, player._move._type.name()));
            }

            if (players.get(0)._move.drawsMove(players.get(1)._move._type)) {
                Log.info(LOG, "Draw");
            } else {
                if (players.get(0)._move.beatsMove(players.get(1)._move._type)) {
                    Log.info(LOG, "Player 0 won");
                } else {
                    Log.info(LOG, "Player 1 won");
                }
            }
        }*/

        GameLogic gameLogic = new GameLogic();

        gameLogic.setupPlayers(argumentParser.getIntPlayersArgument());

        while (true) {
            for (Player player : gameLogic.players) {
                Log.info(LOG,String.format("Setting move for %s",player.numPlayer));
                player.setMove(gameInterface.interfaceGetInput().toMoveType());
            }

            gameLogic.calculateBattles();
        }
    }

}
