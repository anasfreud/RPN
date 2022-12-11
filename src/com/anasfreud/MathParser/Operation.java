package com.anasfreud.MathParser;

public class Operation extends MathObject implements Comparable<Operation> {
    private static final Operation[] allowedOperations = new Operation[] {
            new Operation('(', 0),
            new Operation(')', 1),
            new Operation('+', 2),
            new Operation('-', 2),
            new Operation('*', 3),
            new Operation('/', 3),
            new Operation('^', 4)
    };

    private final char symbol;
    private final int priority;

    private Operation(char symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public Operation(char symbol) throws MathParserException {
        for (Operation o : allowedOperations) {
            if (o.getSymbol() == symbol) {
                this.symbol = o.getSymbol();
                this.priority = o.getPriority();
                return;
            }
        }
        throw new MathParserException("Undefined operation");
    }

    public int getPriority() { return priority; }
    public char getSymbol() {return symbol; }
    public boolean isOpenBracket() {
        return symbol == '(';
    }
    public boolean isCloseBracket() {
        return symbol == ')';
    }

    @Override
    public int compareTo(Operation o) {
        return Integer.compare(priority, o.getPriority());
    }

    @Override
    public String toString() {
        return symbol + "";
    }

    public int doOperation(int o1, int o2) {
        return switch (symbol) {
            case '+' -> o1 + o2;
            case '-' -> o1 - o2;
            case '*' -> o1 * o2;
            case '/' -> o1 / o2;
            case '^' -> (int) Math.pow(o1, o2);
            default -> 0;
        };
    }
}
