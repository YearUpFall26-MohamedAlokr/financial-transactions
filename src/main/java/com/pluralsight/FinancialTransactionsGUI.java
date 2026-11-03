package com.pluralsight;

import javax.swing.*;
import java.awt.*;

public class FinancialTransactionsGUI extends JFrame {

    public JLabel balanceLabel;

    public FinancialTransactionsGUI() {

        setTitle("Financial Transactions");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



        balanceLabel = new JLabel();
        double balance = FinancialTransactions.getBalance();
        balanceLabel.setText("Current Balance: $" +String.format("%.2f", balance));
        if(balance >= 0){
            balanceLabel.setForeground(java.awt.Color.GREEN);
        }
        else{
            balanceLabel.setForeground(java.awt.Color.RED);
        }

        JButton depositButton = new JButton("Add Deposit");
        depositButton.addActionListener(e -> {

            String amountText = JOptionPane.showInputDialog("Deposit Amount");

            String vendor = JOptionPane.showInputDialog("Vendor");

            String description = JOptionPane.showInputDialog("Description");

            double amount = Double.parseDouble(amountText);

            FinancialTransactions.saveTransaction(amount,vendor,description);
            refreshBalance();
        });


        JButton paymentButton = new JButton("Make Payment");
        paymentButton.addActionListener(e -> {

            String amountText = JOptionPane.showInputDialog("Payment Amount");

            String vendor = JOptionPane.showInputDialog("Vendor");

            String description = JOptionPane.showInputDialog("Description");

            double amount = -Math.abs(Double.parseDouble(amountText));

            FinancialTransactions.saveTransaction(amount,vendor,description);
            refreshBalance();
        });



        JPanel buttonPanel = new JPanel();

        add(balanceLabel, BorderLayout.NORTH);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        buttonPanel.add(depositButton);
        buttonPanel.add(paymentButton);
        add(buttonPanel, BorderLayout.SOUTH);



        setVisible(true);
    }



    public static void main(String[] args) {

        FinancialTransactions.LoadAccount();

        SwingUtilities.invokeLater(() -> new FinancialTransactionsGUI()
        );
    }


    public void refreshBalance() {

        double balance =
                FinancialTransactions.getBalance();

        balanceLabel.setText(
                "Current Balance: $" +
                        String.format("%.2f", balance)
        );

        if(balance >= 0){
            balanceLabel.setForeground(Color.GREEN);
        }
        else{
            balanceLabel.setForeground(Color.RED);
        }
    }
}