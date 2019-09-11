package com.untitled.untitled.handlers;

import com.untitled.untitled.GameInterface;
import com.untitled.untitled.Input;
import com.untitled.untitled.helpers.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardInputHandler implements GameInterface {

    final String LOG = "KeyboardInputHandler";

    BufferedReader in;

    @Override
    public Boolean interfaceInit() {
        Log.info(LOG,"init");

        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Input interfaceGetInput() {
        String input;
        try {
            input = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        switch (input.toLowerCase()) {
            case "r":
            case "rock":
                return new Input(Input.InputTypes.INPUT_MOVE_ROCK);
            case "p":
            case "paper":
                return new Input(Input.InputTypes.INPUT_MOVE_PAPER);
            case "s":
            case "scissors":
                return new Input(Input.InputTypes.INPUT_MOVE_SCISSORS);
            default:
                return null;
        }
    }
}
