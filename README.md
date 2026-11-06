# Financial Transactions Application

## Overview

The Financial Transactions Application is a Java-based personal finance management system that allows users to record deposits and payments, maintain a transaction ledger, search transaction history, and generate financial reports.

This project was developed as a capstone project to demonstrate object-oriented programming, file handling, collections, reporting, and graphical user interface development using Java.

---

## Features

### Transaction Management
- Add Deposits
- Add Payments
- Store transaction information in a CSV file
- Automatically load previous transactions when the application starts

### Ledger
- View all transactions
- View deposits only
- View payments only
- Display transactions in reverse chronological order (newest first)

### Reporting
- Month-To-Date Report
- Previous Month Report
- Year-To-Date Report
- Previous Year Report
- Search by Vendor
- Custom Search

### Custom Search Filters
Users can search using any combination of:

- Start Date
- End Date
- Description
- Vendor
- Amount

### Graphical User Interface
- User-friendly Java Swing interface
- Easy transaction entry
- Transaction viewing and reporting
- Color-coded transaction display
- Positive balances displayed in green
- Negative balances displayed in red

---

## Technologies Used

- Java
- Java Swing
- File I/O
- ArrayList Collections
- LocalDate / LocalDateTime
- CSV Data Storage
- Object-Oriented Programming Principles

---

## Project Structure

```
financial-transactions/
│
├── Main.java
├── FinancialTransactionsGUI.java
├── Transaction.java
├── TransactionManager.java
├── Console.java
├── transactions.csv
└── README.md
```

---

## How It Works

### Transaction Class
Represents a single financial transaction and stores:

- Date
- Time
- Description
- Vendor
- Amount

### Transaction Manager
Handles:

- Loading transactions
- Saving transactions
- Report generation
- Searching transactions

### GUI
Provides a user interface for:

- Entering transactions
- Viewing the ledger
- Running reports
- Managing transaction data

---

## Running the Application

### Using IntelliJ

1. Clone the repository:

```bash
git clone https://github.com/YearUpFall26-MohamedAlokr/financial-transactions.git
```

2. Open the project in IntelliJ IDEA.

3. Run:

```java
Main.java
```

---

## Sample Transaction Format

Transactions are stored in CSV format:

```csv
2026-11-01|08:30:00|Deposit|Paycheck|1500.00
2026-11-02|13:15:00|Payment|Amazon|-45.99
```

---

## Skills Demonstrated

- Object-Oriented Programming
- Encapsulation
- Class Design
- Collections Framework
- File Handling
- Exception Handling
- Date and Time Processing
- User Interface Development
- Git Version Control
- GitHub Repository Management

---

## Future Improvements

- Database integration
- User authentication
- Transaction categories
- Charts and financial analytics
- Export reports to PDF
- Multi-user support

---

## Author

Mohamed Alokr

Year Up United Capstone Project

Fall 2026