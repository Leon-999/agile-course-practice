package ru.unn.agile.LongArithmetic.view;

import javax.swing.*;
import java.awt.*;

public class MatrixMultiply {

    private Object[] headers = { "Name", "Surname", "Telephone" };

    private Object[][] data = {
            { "John", "Smith", "1112221" },
            { "Ivan", "Black", "2221111" },
            { "George", "White", "3334444" },
            { "Bolvan", "Black", "2235111" },
            { "Serg", "Black", "2221511" },
            { "Pussy", "Black", "2221111" },
            { "Tonya", "Red", "2121111" },
            { "Elise", "Green", "2321111" },
    };

    private static final int formWidth  = 600;
    private static final int formHeight  = 500;

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;


    private JLabel firstMatrixLabel;
    private JTextField mFirstMatrixText;
    private JTextField nFirstMatrixText;
    private JLabel secondMatrixLabel;
    private JTextField mSecondMatrixText;
    private JTextField nSecondMatrixText;
    private JButton okButton;
    private JButton calculateButton;

    private JTable firstMultiplier;
    private JTable secondMultiplier;
    private JTable resultMatrix;

    private MatrixMultiply() {
        JFrame frame = new JFrame("JTableExample");
        frame.setSize(formWidth, formHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializePanels();
        initializeTopPanel();
        initializeMiddlePanel();
        initializeBottomPanel();
        initializeMainPanel();

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void initializePanels() {
        mainPanel = new JPanel(new FlowLayout());
        topPanel = new JPanel(new GridLayout(2,4));
        middlePanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new FlowLayout());
    }

    private void initializeTopPanel() {
        firstMatrixLabel = new JLabel("M and N first matrix:");
        mFirstMatrixText = new JTextField("3");
        nFirstMatrixText = new JTextField("4");
        secondMatrixLabel = new JLabel("M and N second matrix:");
        mSecondMatrixText = new JTextField("4");
        nSecondMatrixText = new JTextField("3");
        okButton = new JButton("ok");

        topPanel.add(firstMatrixLabel);
        topPanel.add(mFirstMatrixText);
        topPanel.add(nFirstMatrixText);
        topPanel.add(okButton);
        topPanel.add(secondMatrixLabel);
        topPanel.add(mSecondMatrixText);
        topPanel.add(nSecondMatrixText);
    }

    private void initializeMiddlePanel() {
        firstMultiplier = new JTable(data, headers);
        secondMultiplier = new JTable(data, headers);

        middlePanel.add(firstMultiplier);
        middlePanel.add(secondMultiplier);
    }

    private void initializeBottomPanel() {
        resultMatrix = new JTable(data, headers);

        calculateButton = new JButton("Multiply");
        bottomPanel.add(calculateButton);
        bottomPanel.add(resultMatrix);
    }

    private void initializeMainPanel() {
        mainPanel.add(topPanel);
        mainPanel.add(middlePanel);
        mainPanel.add(bottomPanel);
    }

    public static void main(String[] args) {
        //Создаем фрейм в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new  MatrixMultiply();
            }
        });
    }
}
