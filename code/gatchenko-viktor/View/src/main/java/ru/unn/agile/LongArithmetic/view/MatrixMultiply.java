package ru.unn.agile.LongArithmetic.view;

import ru.unn.agile.LongArithmetic.model.Matrix;
import ru.unn.agile.LongArithmetic.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MatrixMultiply {
    private static final int formWidth  = 600;
    private static final int formHeight  = 500;

    private ViewModel viewModel;

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

    private JLabel labelStatus;

    private KeyAdapter keyListenerInputNM;

    public static void main(String[] args) {
        //Создаем фрейм в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new  MatrixMultiply(new ViewModel());
            }
        });
    }

    private MatrixMultiply(ViewModel viewModel) {
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

        this.viewModel = viewModel;

        initializeListeners();
        statusUpdate();
    }

    private void initializePanels() {
        mainPanel = new JPanel(new FlowLayout());
        topPanel = new JPanel(new GridLayout(2,4));
        middlePanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new FlowLayout());
    }

    private void initializeTopPanel() {
        firstMatrixLabel = new JLabel("M and N first matrix:");
        mFirstMatrixText = new JTextField("");
        nFirstMatrixText = new JTextField("2");
        secondMatrixLabel = new JLabel("M and N second matrix:");
        mSecondMatrixText = new JTextField("2");
        nSecondMatrixText = new JTextField("1");
        okButton = new JButton("Ok");
        okButton.setEnabled(false);

        topPanel.add(firstMatrixLabel);
        topPanel.add(mFirstMatrixText);
        topPanel.add(nFirstMatrixText);
        topPanel.add(okButton);
        topPanel.add(secondMatrixLabel);
        topPanel.add(mSecondMatrixText);
        topPanel.add(nSecondMatrixText);
    }

    private void initializeMiddlePanel() {
        firstMultiplier = new JTable(1, 1);
        secondMultiplier = new JTable(1, 1);

        middlePanel.add(firstMultiplier);
        middlePanel.add(secondMultiplier);
    }

    private void initializeBottomPanel() {
        resultMatrix = new JTable(1, 1);
        bottomPanel.add(resultMatrix);
    }

    private void initializeMainPanel() {
        mainPanel.add(topPanel);
        mainPanel.add(middlePanel);

        calculateButton = new JButton("Multiply");
        calculateButton.setEnabled(false);
        mainPanel.add(calculateButton);

        mainPanel.add(bottomPanel);

        labelStatus = new JLabel();
        mainPanel.add(labelStatus);
    }

    private void initializeListeners() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                clickOkButton();
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                clickMultiplyButton();
            }
        });

        keyListenerInputNM = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                keyProcessInputMN();
            }
        };

        mFirstMatrixText.addKeyListener(keyListenerInputNM);
        nFirstMatrixText.addKeyListener(keyListenerInputNM);
        mSecondMatrixText.addKeyListener(keyListenerInputNM);
        nSecondMatrixText.addKeyListener(keyListenerInputNM);


    }

    private void clickOkButton() {
        bindMN();
        viewModel.processingInputMN();
        backBindMN();
        calculateButton.setEnabled(viewModel.isMultiplyButtonEnabled());
    }

    private void  bindMN() {
        viewModel.setMFirstMatrix(mFirstMatrixText.getText());
        viewModel.setNFirstMatrix(nFirstMatrixText.getText());
        viewModel.setMSecondMatrix(mSecondMatrixText.getText());
        viewModel.setNSecondMatrix(nSecondMatrixText.getText());
    }

    private void backBindMN() {
        okButton.setEnabled(viewModel.isOkButtonEnabled());
        reinitializeMiddlePanel();
        statusUpdate();
    }

    private void statusUpdate() {
        labelStatus.setText(viewModel.getStatus());
    }

    private void reinitializeMiddlePanel() {
        middlePanel.remove(firstMultiplier);
        middlePanel.remove(secondMultiplier);

        firstMultiplier = new JTable(viewModel.getM1(), viewModel.getN1());
        secondMultiplier = new JTable(viewModel.getM2(), viewModel.getN2());

        middlePanel.add(firstMultiplier);
        middlePanel.add(secondMultiplier);
        middlePanel.updateUI();
    }

    private void clickMultiplyButton() {
        bindMatrixes();
        MatrixMultiply.this.viewModel.processingInputMatrices();//multiplyMatrices();
        backBindMatrixes();
    }

    private void bindMatrixes() {
        stopEditingMatrixes();
        initializeFirstMultiplier();
        initializeSecondMultiplier();
    }

    private void stopEditingMatrixes() {
        if(firstMultiplier.isEditing()) {
            firstMultiplier.getCellEditor().stopCellEditing();
        }
        if (secondMultiplier.isEditing()) {
            secondMultiplier.getCellEditor().stopCellEditing();
        }
    }

    private void initializeFirstMultiplier() {
        int columnCount = firstMultiplier.getColumnCount();
        int rowCount = firstMultiplier.getRowCount();
        for(int i = 0; i < rowCount; i++) {
            for(int j = 0; j < columnCount; j++) {
                String stringValue = (String)firstMultiplier.getValueAt(i, j);
                viewModel.setValueToFirstMatrix(i, j, stringValue);
            }
        }
    }

    private void  initializeSecondMultiplier() {
        int columnCount = secondMultiplier.getColumnCount();
        int rowCount = secondMultiplier.getRowCount();
        for(int i = 0; i < rowCount; i++) {
            for(int j = 0; j < columnCount; j++) {
                String value = (String)secondMultiplier.getValueAt(i, j);
                viewModel.setValueToSecondMatrix(i, j, value);
            }
        }
    }

    private void backBindMatrixes() {
        Matrix result = viewModel.getResultMatrix();
        reinitializeBottomPanel(result);
        statusUpdate();
    }

    private  void reinitializeBottomPanel(Matrix result) {
        bottomPanel.remove(resultMatrix);

        initializeResultMatrix(result);
        bottomPanel.add(resultMatrix);
        bottomPanel.updateUI();
    }

    private void initializeResultMatrix(Matrix result) {
        if(result == null) {
            resultMatrix = new JTable(1, 1);
        } else {
            int columnCount = result.getWidth();
            int rowCount = result.getHeight();
            resultMatrix = new JTable(rowCount, columnCount);

            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    String value = result.getElement(i, j).convertToString();
                    resultMatrix.setValueAt(value, i, j);
                }
            }
        }
    }

    private void keyProcessInputMN() {
        bindMN();
        viewModel.parseInputMN();
        statusUpdate();
        okButton.setEnabled(viewModel.isOkButtonEnabled());
        calculateButton.setEnabled(viewModel.isMultiplyButtonEnabled());
    }
}
