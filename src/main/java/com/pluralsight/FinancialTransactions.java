package com.pluralsight;

import java.util.Scanner;

public class FinancialTransactions {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        MainMenu();
    }
    public static void MainMenu() {
        boolean running = true;
        while(running) {
            System.out.println("** Welcome to FinancialTransactions Menu **");
            System.out.println("1- Enter D to Add Deposit");
            System.out.println("2- Enter P to Make Payment");
            System.out.println("3- Enter L to Ledger");
            System.out.println("4- Enter X to Quit");

            char option= sc.nextLine().toUpperCase().charAt(0);
            switch (option) {
                case 'D':
                    AddDeposit();
                    break;
                case 'P':
                    MakePayment();
                    break;
                case 'L':
                    Ledger();
                    break;
                case 'X':
                    running=false;
                    break;
                default:
                    System.out.println("Invalid Option Please Try Again");
            }

        }
}

public static void AddDeposit() {
        System.out.println("Enter Amount to Deposit");
        double amount=sc.nextDouble();
}
public static void MakePayment() {
        System.out.println("Enter Amount to Deposit");
        double amount=sc.nextDouble();
}
public static void Ledger() {

}


}
