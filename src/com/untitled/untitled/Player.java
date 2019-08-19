package com.untitled.untitled;

public class Player {

    public Move _move;
    public int numPlayer;
    public int numWins;
    public int numLosses;

    public void setMove(EnumMoveTypes type) {
        _move = MoveFactory.createMove(type);
    }

    public Player(int num) {
        numPlayer = num;
    }

}
