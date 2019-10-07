package com.untitled.untitled.helpers;

import java.util.ArrayList;

public class ArgumentParser {

    static final String TAG = "ArgumentParser";

    private ArrayList<Argument> arguments;

    public ArgumentParser() {
        arguments = new ArrayList<>();
    }

    public void addArgument(Argument argument) {
        arguments.add(argument);
    }

    public Argument getArgument(String key) throws NullPointerException {
        for (Argument argument : arguments) {
            if (argument.key.equals(key))
                return argument;
        }
        throw new NullPointerException(String.format("Key %s not found in arguments",key));
    }

    public int getIntPlayersArgument() {
        return Integer.parseInt(this.getArgument("players").value);
    }

    public String getInputHandlerArgument() {
        return this.getArgument("input").value;
    }

    public boolean getResetStatsArgument() {
        return Boolean.parseBoolean(this.getArgument("reset").value);
    }

    public void logArgs() {
        for (Argument argument : arguments) {
            Log.info(TAG, String.format("Argument %s: %s", argument.key, argument.value));
        }
    }
}
