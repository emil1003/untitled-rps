package com.untitled.untitled;

import com.untitled.untitled.moves.Paper;
import com.untitled.untitled.moves.Rock;
import com.untitled.untitled.moves.Scissors;

abstract class MoveFactory {

    private static Rock createRock() {
        return new Rock();
    }

    private static Paper createPaper() {
        return new Paper();
    }

    private static Scissors createScissors() {
        return new Scissors();
    }

    static Move createMove(EnumMoveTypes type) {
        switch (type) {
            case TYPE_ROCK:
                return createRock();
            case TYPE_PAPER:
                return createPaper();
            case TYPE_SCISSORS:
                return createScissors();
            default:
                throw new IllegalStateException(String.format("Type %s is not of value EnumMoveTypes", type.name()));
        }
    }

}
