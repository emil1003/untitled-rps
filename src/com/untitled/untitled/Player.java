package com.untitled.untitled;

public class Player {

    public Move _move;

    public void setMove(EnumMoveTypes type) {
        _move = MoveFactory.createMove(type);
    }

}
