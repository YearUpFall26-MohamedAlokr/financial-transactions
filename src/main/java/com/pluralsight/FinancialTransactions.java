package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class FinancialTransactions {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        LoadAccount();
        MainMenu();
    }
    public static void LoadAccount() {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
        String line;

        while((line = reader.readLine()) != null){

            String[] parts = line.split("\\|");

            Transaction transaction = new Transaction(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));

            transactions.add(transaction);
        }
    }catch (IOException e){
            System.out.println("+ You have No Balance");
    }
    }

    public static void MainMenu() {
        boolean running = true;
        while(running) {
            System.out.println("_______________________________");
            System.out.println("Your Current Balance is $"+MyBalance());
            System.out.println("_______________________________");
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
public static double MyBalance() {
        double balance = 0;
        for (Transaction t: transactions){
            balance+=t.getAmount();

        }return balance;
}
public static void AddDeposit() {
    System.out.print("Amount: ");
    double amount = Double.parseDouble(sc.nextLine());

    System.out.print("Vendor: ");
    String vendor = sc.nextLine();

    System.out.print("Description: ");
    String description = sc.nextLine();

    saveTransaction( amount, vendor,description);
}

public static void MakePayment(){
        System.out.println("Enter Amount to Deposit");
        double amount=sc.nextDouble();
}

public static void Ledger(){

}

public static void saveTransaction( double amount, String vendor,String description){

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        try {
            FileWriter writer =
                    new FileWriter("transactions.csv", true);

            writer.write(date + "|" +
                    time.withNano(0) + "|" + description + "|" + vendor + "|" + amount + "\n");
            writer.close();
            System.out.println("Transaction Saved Successfully.");

        } catch (IOException e) {
            System.out.println("Error Saving");
        }
    }
}

