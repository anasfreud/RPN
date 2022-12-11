package com.anasfreud.MathParser;

import java.util.ArrayList;
import java.util.Stack;

public class MathParser {

    private final String expression;

    private ArrayList<MathObject> parsed;

    public MathParser(String expression) {
        this.expression = expression.replaceAll("\\s+","");
        parse();
    }


    private void parse() {
        parsed = new ArrayList<>();
        Stack<Operation> operations = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {

                StringBuilder buff = new StringBuilder();

                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    buff.append(expression.charAt(i));
                    i++;
                }

                parsed.add(new Operand(Integer.parseInt(buff.toString())));
                i--;
                continue;
            }


            try {

                Operation op = new Operation(c);

                if (op.isOpenBracket()) {
                    operations.push(op);
                } else if (op.isCloseBracket()) {
                    if (operations.empty()) {
                        throw new MathParserException("Bad brackets sequence");
                    }
                    while (!operations.peek().isOpenBracket()) {
                        parsed.add(operations.peek());
                        operations.pop();
                        if (operations.empty()) {
                            throw new MathParserException("Bad brackets sequence");
                        }
                    }
                    operations.pop();
                } else {
                    if (!operations.empty()) {
                        while (!operations.empty() && op.compareTo(operations.peek()) <= 0) {
                            parsed.add(operations.peek());
                            operations.pop();
                        }
                    }
                    operations.push(op);
                }

            } catch (MathParserException ex) {
                System.out.println(ex.getMessage(expression, i));
                parsed.clear();
                return;
            }
        }

        try {
            while (!operations.empty()) {
                if (operations.peek().isOpenBracket()) {
                    throw new MathParserException("Bad brackets sequence");
                }
                parsed.add(operations.peek());
                operations.pop();
            }
        } catch (MathParserException ex) {
            System.out.println(ex.getMessage(expression, expression.length()));
            parsed.clear();
        }

    }

    public String reversePolishNotation() {
        StringBuilder rpn = new StringBuilder();
        for (MathObject o : parsed) {
            rpn.append(o.toString())
                    .append(" ");
        }
        return rpn.toString();
    }

    public int solve() {
        int result = 0;
        Stack<Operand> operands = new Stack<>();

        for (MathObject o : parsed) {
            if (o.getClass() == Operand.class) {
                operands.add((Operand)o);
            } else if (o.getClass() == Operation.class) {
                Operand o1 = operands.peek();
                operands.pop();
                Operand o2 = operands.peek();
                operands.pop();
                result = ((Operation) o).doOperation(o1.getValue(), o2.getValue());
                operands.push(new Operand(result));
            }
        }
        return result;
    }


}
