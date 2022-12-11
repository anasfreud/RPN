package com.anasfreud.MathParser;

public class Operand extends MathObject{

    private final int value;
    public Operand(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    @Override
    public String toString() {
        return value + "";
    }
}
