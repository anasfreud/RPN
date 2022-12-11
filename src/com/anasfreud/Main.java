package com.anasfreud;

import com.anasfreud.MathParser.MathParser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String expression = sc.nextLine();
        MathParser parser = new MathParser(expression);
        System.out.println(parser.reversePolishNotation());
        System.out.println(parser.solve());
    }
}
