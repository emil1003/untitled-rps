package com.untitled.untitled;

import com.untitled.untitled.moves.Rock;

public abstract class MoveFactory {

    Rock createRock() {
        return new Rock();
    }

}
