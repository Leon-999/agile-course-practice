package ru.unn.agile.FinanceManagement.view;

import ru.unn.agile.FinanceManagement.Infrastructure.TxtLoggerOfFinanceManagement;
import ru.unn.agile.FinanceManagement.viewModel.FinanceManagerViewModel;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public final class Manager {
    private JPanel mainPanel;
    private JTable consumptions;
    private JButton addConsumptionButton;
    private JTextField balance0TextField;
    private JTextField addedBalanceTF;
    private JButton addedBalanceButton;
    private JTextField categoryTextField;
    private JTextField nameTextField;
    private JTextField countTF;
    private JTextField priceTF;
    private JTextField countTextField;
    private JTextField priceTextField;
    private JTextField dayTextField;
    private JComboBox<String> categoryCB;
    private JComboBox<String> nameCB;
    private JTextField yearTextField1;
    private JComboBox<Integer> yearCB;
    private JComboBox<Integer> monthCB;
    private JComboBox<Integer> dayCB;
    private JTextField monthTextField;
    private JTextField priceErrorMessage;
    private JTextField countErrorMessage;
    private final FinanceManagerViewModel viewModel;
    private final String[] columnNames = {
            "Category",
            "Name",
            "Count",
            "Price",
            "Year",
            "Month",
            "Day"
    };

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Calculator");
        TxtLoggerOfFinanceManagement logger =
            new TxtLoggerOfFinanceManagement("./FinanceManagement.log");
        frame.setContentPane(new Manager(new FinanceManagerViewModel(logger)).mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Manager(final FinanceManagerViewModel viewModel) {
        this.viewModel = viewModel;
        backBinding();
        addConsumptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                binding();
                viewModel.addConsumption();
                categoryCB.setModel(new JComboBox<>(viewModel.getArrayCategory()).getModel());
                backBinding();
            }
        });

        addedBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                binding();
                viewModel.addedBalance(addedBalanceTF.getText());
                backBinding();
            }
        });

        nameCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                binding();
                backBinding();
            }
        });

        yearCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                binding();
                backBinding();
            }
        });

        dayCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                binding();
                backBinding();
            }
        });

        monthCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                binding();
                backBinding();
            }
        });

        categoryCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                binding();
                nameCB.setModel(new JComboBox<>(viewModel.getArrayName()).getModel());
                backBinding();
            }
        });

        countTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(final DocumentEvent env) {
                binding();
                backBinding();
            }

            @Override
            public void removeUpdate(final DocumentEvent env) {
                binding();
                backBinding();
            }

            @Override
            public void insertUpdate(final DocumentEvent env) {
                binding();
                backBinding();
            }
        });

        priceTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(final DocumentEvent env) {
                binding();
                backBinding();
            }

            @Override
            public void changedUpdate(final DocumentEvent env) {
                binding();
                backBinding();
            }

            @Override
            public void insertUpdate(final DocumentEvent env) {
                binding();
                backBinding();
            }
        });
    }

    private void backBinding() {
        consumptions.setModel(new JTable(viewModel.getArrayConsumptions(), columnNames).getModel());
        nameCB.setEnabled(viewModel.isEnabledNameTextbox());
        addConsumptionButton.setEnabled(viewModel.isEnabledAddConsumptionButton());
        countTF.setEnabled(viewModel.isEnabledCountAndPriceTextbox());
        priceTF.setEnabled(viewModel.isEnabledCountAndPriceTextbox());
        yearCB.setModel(new JComboBox<>(viewModel.getYears()).getModel());
        yearCB.setSelectedIndex(viewModel.getSelectedYear());
        monthCB.setModel(new JComboBox<>(viewModel.getMonths()).getModel());
        monthCB.setSelectedIndex(viewModel.getSelectedMonth());
        dayCB.setModel(new JComboBox<>(viewModel.getDays()).getModel());
        dayCB.setSelectedIndex(viewModel.getSelectedDay());
        countErrorMessage.setText(viewModel.getCountErrorMessage());
        priceErrorMessage.setText(viewModel.getPriceErrorMessage());
        balance0TextField.setText("Balance = " + viewModel.getBalance());
    }

    private void binding() {
        viewModel.setCategory((String) categoryCB.getSelectedItem());
        viewModel.setName((String) nameCB.getSelectedItem());
        viewModel.setCount(countTF.getText());
        viewModel.setPrice(priceTF.getText());
        viewModel.setYear(yearCB.getSelectedItem().toString());
        viewModel.setMonth(monthCB.getSelectedItem().toString());
        viewModel.setDay(dayCB.getSelectedItem().toString());
        viewModel.setSelectedDay(dayCB.getSelectedIndex());
        viewModel.setSelectedMonth(monthCB.getSelectedIndex());
        viewModel.setSelectedYear(yearCB.getSelectedIndex());
    }
}
