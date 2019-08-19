package com.untitled.untitled;

public abstract class Move {

    public EnumMoveTypes _type;

    public void setType(EnumMoveTypes type) {
        _type = type;
    }

    public Boolean beatsMove(EnumMoveTypes type) {
        switch (_type) {
            case TYPE_ROCK:
                return type.equals(EnumMoveTypes.TYPE_SCISSORS);
            case TYPE_PAPER:
                return type.equals(EnumMoveTypes.TYPE_ROCK);
            case TYPE_SCISSORS:
                return type.equals(EnumMoveTypes.TYPE_PAPER);
            default:
                throw new IllegalStateException(String.format("Type %s is not value of EnumMoveTypes", _type.name()));
        }
    }

    public Boolean losesToMove(EnumMoveTypes type) {
        switch (_type) {
            case TYPE_ROCK:
                return type.equals(EnumMoveTypes.TYPE_PAPER);
            case TYPE_PAPER:
                return type.equals(EnumMoveTypes.TYPE_SCISSORS);
            case TYPE_SCISSORS:
                return type.equals(EnumMoveTypes.TYPE_ROCK);
            default:
                throw new IllegalStateException(String.format("Type %s is not value of EnumMoveTypes", _type.name()));
        }
    }

    public Boolean drawsMove(EnumMoveTypes type) {
        return _type.equals(type);
    }

}
