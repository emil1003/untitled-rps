package com.untitled.untitled;

public class Input {

    public InputTypes type;
    public String value;

    public enum InputTypes {
        INPUT_CONFIRM,
        INPUT_CANCEL,
        INPUT_INCREMENT,
        INPUT_DECREMENT,
        INPUT_MOVE_ROCK,
        INPUT_MOVE_PAPER,
        INPUT_MOVE_SCISSORS
    }

    public Input(InputTypes type) {
        this.type = type;
    }

    public Input(InputTypes type, String value) {
        this.type = type;
        this.value = value;
    }

    public EnumMoveTypes toMoveType() {
        switch (type) {
            case INPUT_MOVE_ROCK:
                return EnumMoveTypes.TYPE_ROCK;
            case INPUT_MOVE_PAPER:
                return EnumMoveTypes.TYPE_PAPER;
            case INPUT_MOVE_SCISSORS:
                return EnumMoveTypes.TYPE_SCISSORS;
            default:
                return null;
        }
    }

}
