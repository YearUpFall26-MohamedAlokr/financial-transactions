package com.pluralsight;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FinancialTransactionsGUI extends JFrame {

    public JLabel balanceLabel;
    public JTable transactionTable;
    public DefaultTableModel table;

    public static void main(String[] args) {

        FinancialTransactions.LoadAccount();
        SwingUtilities.invokeLater(FinancialTransactionsGUI::new);

    }

    public FinancialTransactionsGUI() {

        setTitle("Financial Transactions");
        setIconImage(new ImageIcon("C:\\Users\\mohamedalokr\\pluralsight\\capstones\\financial-transactions\\ProgramIcon.png").getImage());
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
            loadTransactions();
        });

        JButton paymentButton = new JButton("Make Payment");
        paymentButton.addActionListener(e -> {

            String amountText = JOptionPane.showInputDialog("Payment Amount");
            String vendor = JOptionPane.showInputDialog("Vendor");
            String description = JOptionPane.showInputDialog("Description");
            double amount = -Math.abs(Double.parseDouble(amountText));
            FinancialTransactions.saveTransaction(amount,vendor,description);

            refreshBalance();
            loadTransactions();
        });


        JPanel buttonPanel = new JPanel();
        add(balanceLabel, BorderLayout.NORTH);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        buttonPanel.add(depositButton);
        buttonPanel.add(paymentButton);
        add(buttonPanel, BorderLayout.SOUTH);

        String[] columns = {"Date","Time","Description","Vendor","Amount"};
        table = new DefaultTableModel(columns, 0);
        transactionTable = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        add(scrollPane, BorderLayout.CENTER);

        loadTransactions();
        setVisible(true);
    }

    public void refreshBalance() {

        double balance = FinancialTransactions.getBalance();
        balanceLabel.setText("Current Balance: $" + String.format("%.2f", balance));
        if(balance >= 0){
            balanceLabel.setForeground(Color.GREEN);
        }
        else{
            balanceLabel.setForeground(Color.RED);
        }
    }

    public void loadTransactions() {

        table.setRowCount(0);

        for (int i = FinancialTransactions.transactions.size() - 1; i >= 0; i--) {

            Transaction t = FinancialTransactions.transactions.get(i);

            table.addRow(new Object[]{t.getDate(), t.getTime(), t.getDescription(),t.getVendor(), t.getAmount()});
        }

        transactionTable.getColumnModel().getColumn(4).setCellRenderer(
                        new javax.swing.table.DefaultTableCellRenderer() {

                            @Override
                            public Component getTableCellRendererComponent(
                                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                                Component c = super.getTableCellRendererComponent(
                                                table, value, isSelected, hasFocus, row, column);

                                double amount = Double.parseDouble(value.toString());

                                if (amount >= 0) {
                                    c.setForeground(Color.GREEN);
                                } else {
                                    c.setForeground(Color.RED);
                                }

                                return c;
                            }
                        });


    }


}