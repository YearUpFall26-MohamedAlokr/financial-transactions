package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FinancialTransactions {

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

            char option= Console.promptForChar("Enter Your Action Character");
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
                    System.out.println("Invalid Choice Please Try Again");
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

    double amount = Console.promptForDouble("Amount: ");
    String vendor = Console.promptForString("Vendor");
    String description = Console.promptForString("Description");
    saveTransaction( amount, vendor,description);
}

public static void MakePayment(){
    double amount = -Math.abs(Console.promptForDouble("Amount: "));
    String vendor = Console.promptForString("Vendor: ");
    String description = Console.promptForString("Description: ");
    saveTransaction( amount, vendor,description);
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

