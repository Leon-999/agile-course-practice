package ru.unn.agile.LongArithmetic.view;

import ru.unn.agile.LongArithmetic.model.Matrix;
import ru.unn.agile.LongArithmetic.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class MatrixMultiply {
    private static final int FORM_WIDTH  = 600;
    private static final int FORM_HEIGHT  = 500;
    private static final int WIDTH_GRID_TOP_PANEL = 2;
    private static final int HEIGHT_GRID_TOP_PANEL = 4;

    private final ViewModel viewModel;

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    private JTextField rowsFirstMatrixText;
    private JTextField colsFirstMatrixText;
    private JTextField rowsSecondMatrixText;
    private JTextField colsSecondMatrixText;
    private JButton okButton;
    private JButton calculateButton;

    private JTable firstMultiplier;
    private JTable secondMultiplier;
    private JTable resultMatrix;

    private JLabel labelStatus;

    public static void main(final String[] args) {
        //Создаем фрейм в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new  MatrixMultiply(new ViewModel());
            }
        });
    }

    private MatrixMultiply(final ViewModel viewModel) {
        JFrame frame = new JFrame("JTableExample");
        frame.setSize(FORM_WIDTH, FORM_HEIGHT);
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
        topPanel = new JPanel(new GridLayout(WIDTH_GRID_TOP_PANEL, HEIGHT_GRID_TOP_PANEL));
        middlePanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new FlowLayout());
    }

    private void initializeTopPanel() {
        JLabel firstMatrixLabel = new JLabel("M and N first matrix:");
        rowsFirstMatrixText = new JTextField("");
        colsFirstMatrixText = new JTextField("2");
        JLabel secondMatrixLabel = new JLabel("M and N second matrix:");
        rowsSecondMatrixText = new JTextField("2");
        colsSecondMatrixText = new JTextField("1");
        okButton = new JButton("Ok");
        okButton.setEnabled(false);

        topPanel.add(firstMatrixLabel);
        topPanel.add(rowsFirstMatrixText);
        topPanel.add(colsFirstMatrixText);
        topPanel.add(okButton);
        topPanel.add(secondMatrixLabel);
        topPanel.add(rowsSecondMatrixText);
        topPanel.add(colsSecondMatrixText);
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

        KeyAdapter keyListenerInputNM = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                keyProcessInputColsRows();
            }
        };

        rowsFirstMatrixText.addKeyListener(keyListenerInputNM);
        colsFirstMatrixText.addKeyListener(keyListenerInputNM);
        rowsSecondMatrixText.addKeyListener(keyListenerInputNM);
        colsSecondMatrixText.addKeyListener(keyListenerInputNM);


    }

    private void clickOkButton() {
        bindColsRows();
        viewModel.processingInputColsRows();
        backBindColsRows();
        calculateButton.setEnabled(viewModel.isMultiplyButtonEnabled());
    }

    private void  bindColsRows() {
        viewModel.setRowsFirstMatrix(rowsFirstMatrixText.getText());
        viewModel.setColsFirstMatrix(colsFirstMatrixText.getText());
        viewModel.setRowsSecondMatrix(rowsSecondMatrixText.getText());
        viewModel.setColsSecondMatrix(colsSecondMatrixText.getText());
    }

    private void backBindColsRows() {
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

        firstMultiplier = new JTable(viewModel.getRows1(), viewModel.getCols1());
        secondMultiplier = new JTable(viewModel.getRows2(), viewModel.getCols2());

        middlePanel.add(firstMultiplier);
        middlePanel.add(secondMultiplier);
        middlePanel.updateUI();
    }

    private void clickMultiplyButton() {
        bindMatrixes();
        MatrixMultiply.this.viewModel.processingInputMatrices();
        backBindMatrixes();
    }

    private void bindMatrixes() {
        stopEditingMatrixes();
        initializeFirstMultiplier();
        initializeSecondMultiplier();
    }

    private void stopEditingMatrixes() {
        if (firstMultiplier.isEditing()) {
            firstMultiplier.getCellEditor().stopCellEditing();
        }
        if (secondMultiplier.isEditing()) {
            secondMultiplier.getCellEditor().stopCellEditing();
        }
    }

    private void initializeFirstMultiplier() {
        int columnCount = firstMultiplier.getColumnCount();
        int rowCount = firstMultiplier.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                String stringValue = (String) firstMultiplier.getValueAt(i, j);
                viewModel.setValueToFirstMatrix(i, j, stringValue);
            }
        }
    }

    private void  initializeSecondMultiplier() {
        int columnCount = secondMultiplier.getColumnCount();
        int rowCount = secondMultiplier.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                String value = (String) secondMultiplier.getValueAt(i, j);
                viewModel.setValueToSecondMatrix(i, j, value);
            }
        }
    }

    private void backBindMatrixes() {
        Matrix result = viewModel.getResultMatrix();
        reinitializeBottomPanel(result);
        statusUpdate();
    }

    private  void reinitializeBottomPanel(final Matrix result) {
        bottomPanel.remove(resultMatrix);

        initializeResultMatrix(result);
        bottomPanel.add(resultMatrix);
        bottomPanel.updateUI();
    }

    private void initializeResultMatrix(final Matrix result) {
        if (result == null) {
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

    private void keyProcessInputColsRows() {
        bindColsRows();
        viewModel.parseInputColsRows();
        statusUpdate();
        okButton.setEnabled(viewModel.isOkButtonEnabled());
        calculateButton.setEnabled(viewModel.isMultiplyButtonEnabled());
    }
}
