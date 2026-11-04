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
            if(!transactions.contains(transaction)){
                transactions.add(transaction);
            }
        }
        reader.close();
    }catch (IOException e){
            System.out.println("+ You have No Balance");
    }
    }

    public static void MainMenu() {
        boolean running = true;
        while(running) {
            System.out.println("_________________________________");
            MyBalance();
            System.out.println("__________________________________");
            System.out.println("** Welcome to FinancialTransactions Menu **");
            System.out.println("1- Enter D To Add Deposit");
            System.out.println("2- Enter P To Make Payment");
            System.out.println("3- Enter L To Ledger");
            System.out.println("4- Enter X To Exit the Program");

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

    public static double getBalance() {
        double balance = 0;

        for (Transaction t : transactions) {
            balance += t.getAmount();
        }

        return balance;
    }

    public static void MyBalance() {

        double balance = getBalance();

        if (balance >= 0) {
            System.out.println(Console.GREEN +
                    "Current Balance: $" + balance +
                    Console.RESET);
        } else {
            System.out.println(Console.RED +
                    "Current Balance: $" + balance +
                    Console.RESET);
        }
    }

    public static void AddDeposit() {

    double amount = Console.promptForDouble("Amount: ");
    if (amount < 0){
        Console.printRed("Negative Amount Detected \nYour Deposit Can't Be Processed");
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
        MyBalance();
        System.out.println("_______________________________");
        System.out.println("** Welcome to Ledger Menu **");
        System.out.println("1- Enter A To Display All Transactions");
        System.out.println("2- Enter D To Display All Deposits");
        System.out.println("3- Enter P To Display All Payments");
        System.out.println("4- Enter R For Reports");
        System.out.println("5- Enter H To Go Back To The Main Menu");

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
            FileWriter writer = new FileWriter("transactions.csv",true);

            writer.write(date + "|" + time.withNano(0) + "|" + description
                    + "|" + vendor + "|" + amount + "\n");
            writer.close();
            Console.printGreen("Transaction Saved Successfully.");
            Transaction transaction = new Transaction(date.toString(), time.withNano(0)
                    .toString(), description, vendor, amount);

            transactions.add(transaction);

        } catch (IOException e) {
            Console.printRed("Error Saving");
        }
    }

    public static void DisplayAllEntries(){
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }

    public static void DisplayDeposits(){
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if(  t.getAmount()>0 && ( !t.getDescription().equals("Negative Deposit Attempted") )  ){
                System.out.println(t.toString());
            }
        }
    }

    public static void DisplayPayments(){
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if(  t.getAmount()<0 && ( !t.getDescription().equals("Negative Deposit Attempted") )  ){
                System.out.println(t.toString());
            }
        }
    }

    public static void Reports(){
        boolean running = true;
        while(running) {
            System.out.println("_______________________________");
            MyBalance();
            System.out.println("_______________________________");
            System.out.println("** Welcome to Reports Menu **");
            System.out.println(" 1) Month To Date ");
            System.out.println(" 2) Previous Month ");
            System.out.println(" 3) Year To Date");
            System.out.println(" 4) Previous Year ");
            System.out.println(" 5) Search by Vendor");
            System.out.println(" 6) Custom Search");
            System.out.println(" 0) Go Back to the Ledger page");

            int option= Console.promptForInt("Enter Your Action Number: ");
            switch (option) {
                case 1:
                    MonthToDate();
                    break;
                case 2:
                    PreviousMonth();
                    break;
                case 3:
                    YearToDate();
                    break;
                case 4:
                    PreviousYear();
                    break;
                case 5:
                    SearchByVendor();
                    break;
                case 6:
                    CustomSearch();
                    break;
                case 0:
                    running=false;
                    break;
                default:
                    System.out.println("Invalid Choice Please Try Again");
            }
    }

}

    public static void MonthToDate(){
        LocalDate MonthStart=LocalDate.now().withDayOfMonth(1);

        for(int i=transactions.size() - 1; i >= 0; i--){
            Transaction t = transactions.get(i);
            LocalDate transactionDate=LocalDate.parse(t.getDate());

            if(!transactionDate.isBefore(MonthStart)){
                System.out.println(t.toString());
            }
        }
    }

    public static void PreviousMonth(){
        LocalDate FirstDayPeriviousMonth=LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate LasttDayPeriviousMonth=LocalDate.now().withDayOfMonth(1).minusDays(1);

        for(int i=transactions.size() - 1; i >= 0; i--){
            Transaction t = transactions.get(i);
            LocalDate transactionDate=LocalDate.parse(t.getDate());

            if(!transactionDate.isBefore(FirstDayPeriviousMonth)
               &&
               !transactionDate.isAfter(LasttDayPeriviousMonth)){
                System.out.println(t.toString());
            }
        }
    }

    public static void YearToDate(){
        LocalDate StartDayYear=LocalDate.now().withDayOfYear(1);

        for(int i=transactions.size() - 1; i >= 0; i--){
            Transaction t = transactions.get(i);
            LocalDate transactionDate=LocalDate.parse(t.getDate());

            if(!transactionDate.isBefore(StartDayYear)){
                System.out.println(t.toString());
            }
        }
    }

    public static void PreviousYear(){
        int year=LocalDate.now().getYear()-1;

        for(int i=transactions.size() - 1; i >= 0; i--){
            Transaction t = transactions.get(i);
            LocalDate transactionDate=LocalDate.parse(t.getDate());

            if (transactionDate.getYear()==year){
                System.out.println(t.toString());
            }
        }
    }

    public static void SearchByVendor(){
        YearToDate();
        System.out.println("_________________________________________________________");
        String vendor=Console.promptForString("Enter Vendor Name: ");
        System.out.println("_________________________________________________________");

        for(int i=transactions.size() - 1; i >= 0; i--){
            Transaction t = transactions.get(i);

            if (t.getVendor().equalsIgnoreCase(vendor)){
                System.out.println(t.toString());
            }
        }


    }

    public static void CustomSearch(){
        System.out.println("___________________________________________________________");
        System.out.println("Leave The Field Blank To Skip This Filter ");
        String startDateString = Console.promptForString("Start Date In This Format (yyyy-MM-dd): ");
        String endDateString = Console.promptForString("End Date In This Format (yyyy-MM-dd): ");
        String description = Console.promptForString("Description: ");
        String vendor = Console.promptForString("Vendor: ");
        String amountString = Console.promptForString("Amount: ");

        for (int i = transactions.size() - 1; i >= 0; i--) {

            Transaction t = transactions.get(i);

            LocalDate transactionDate = LocalDate.parse(t.getDate());

            if (!startDateString.isBlank()) {
                LocalDate startDate = LocalDate.parse(startDateString);

                if (transactionDate.isBefore(startDate)) {
                    continue;
                }
            }

            if (!endDateString.isBlank()) {
                LocalDate endDate = LocalDate.parse(endDateString);

                if (transactionDate.isAfter(endDate)) {
                    continue;
                }
            }

            if (!description.isBlank() && !t.getDescription().toLowerCase().contains(description.toLowerCase())) {
                continue;
            }

            if (!vendor.isBlank() && !t.getVendor().toLowerCase().contains(vendor.toLowerCase())) {
                continue;
            }

            if (!amountString.isBlank()) {
                double amount = Double.parseDouble(amountString);

                if (t.getAmount() != amount) {
                    continue;
                }
            }

            System.out.println(t);
        }

    }

}
