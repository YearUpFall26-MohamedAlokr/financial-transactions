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
        transactions.clear();
    try {
        BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
        String line;

        while((line = reader.readLine()) != null){

            String[] parts = line.split("\\|");

            Transaction transaction = new Transaction(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));
            if(!transactions.contains(transaction)){
                transactions.add(transaction);
            }
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
            System.out.println("1- Enter D To Add Deposit");
            System.out.println("2- Enter P To Make Payment");
            System.out.println("3- Enter L To Ledger");
            System.out.println("4- Enter X To Quit");

            char option= Console.promptForChar("Enter Your Action Character: ");
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
    if (amount < 0){
        amount = 0;
        String vendor = "------";
        String description = "Negative Deposit Attempted";
        saveTransaction(amount, vendor, description);
        System.out.println("Negative Amount Detected \nYour Deposit Can't Be Processed");
    }else {
        String vendor = Console.promptForString("Vendor: ");
        String description = Console.promptForString("Description: ");
        saveTransaction(amount, vendor, description);
    }

}

    public static void MakePayment(){
    double amount = -Math.abs(Console.promptForDouble("Amount: "));
    String vendor = Console.promptForString("Vendor: ");
    String description = Console.promptForString("Description: ");
    saveTransaction( amount, vendor,description);
}

    public static void Ledger(){
    boolean running = true;
    while(running) {
        System.out.println("_______________________________");
        System.out.println("Your Current Balance is $"+MyBalance());
        System.out.println("_______________________________");
        System.out.println("** Welcome to FinancialTransactions Menu **");
        System.out.println("1- Enter A To Display All Transactions");
        System.out.println("2- Enter D To Display All Deposits");
        System.out.println("3- Enter P To Display All Payments");
        System.out.println("4- Enter R For Reports");
        System.out.println("5- Enter H To Quit");

        char option= Console.promptForChar("Enter Your Action Character: ");
        switch (option) {
            case 'A':
                DisplayAllEntries();
                break;
            case 'D':
                DisplayDeposits();
                break;
            case 'P':
                DisplayPayments();
                break;
            case 'R':
                Reports();
                break;
            case 'H':
                running=false;
                break;
            default:
                System.out.println("Invalid Choice Please Try Again");
        }

    }
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
            Transaction transaction = new Transaction(date.toString(), time.withNano(0)
                    .toString(), description, vendor, amount);

            transactions.add(transaction);

        } catch (IOException e) {
            System.out.println("Error Saving");
        }
    }

    public static void DisplayAllEntries(){
        LoadAccount();
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }

    public static void DisplayDeposits(){
        LoadAccount();
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if(  t.getAmount()>0 && ( !t.getDescription().equals("Negative Deposit Attempted") )  ){
                System.out.println(t.toString());
            }
        }
    }

    public static void DisplayPayments(){
        LoadAccount();
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if(  t.getAmount()<0 && ( !t.getDescription().equals("Negative Deposit Attempted") )  ){
                System.out.println(t.toString());
            }
        }
    }

    public static void Reports(){

    }



}
