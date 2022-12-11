package com.anasfreud.MathParser;

public class MathParserException extends Exception{


    public MathParserException(String message) {
        super(message);
    }

    public String getMessage(String expression, int indexAt) {
            return getMessage() + ":\n" +
                    expression + "\n" +
                    "~".repeat(indexAt) + "^" +
                    "~".repeat(expression.length() -  indexAt - 1);
    }
}
