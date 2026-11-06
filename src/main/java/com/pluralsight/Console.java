package com.pluralsight;

import java.util.Scanner;


public class Console {
    static final String RESET = "\u001B[0m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    private static final Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static double promptForDouble(String prompt) {

        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static int promptForInt(String prompt) {

        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    public static char promptForChar(String prompt) {

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.length() == 1) {
                return Character.toUpperCase(input.charAt(0));
            }

            System.out.println("Please enter a single character.");
        }
    }

    public static void printGreen(String message) {
        System.out.println(GREEN + message + RESET);
    }

    public static void printRed(String message) {
        System.out.println(RED + message + RESET);
    }
}