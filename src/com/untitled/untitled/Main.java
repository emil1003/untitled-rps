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

        GameLogic gameLogic = new GameLogic();

        gameLogic.setArgumentParser(argumentParser);

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
