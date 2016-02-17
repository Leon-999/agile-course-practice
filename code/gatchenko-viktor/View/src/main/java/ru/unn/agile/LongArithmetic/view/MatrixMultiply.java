package ru.unn.agile.LongArithmetic.view;

import ru.unn.agile.LongArithmetic.model.Matrix;
import ru.unn.agile.LongArithmetic.viewmodel.ViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class MatrixMultiply {
    private static final int FORM_WIDTH  = 800;
    private static final int FORM_HEIGHT  = 300;
    private static final int WIDTH_GRID_TOP_PANEL = 2;
    private static final int HEIGHT_GRID_TOP_PANEL = 4;

    private final ViewModel viewModel;

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    private JTextField heightFirstMatrixText;
    private JTextField widthFirstMatrixText;
    private JTextField heightSecondMatrixText;
    private JTextField widthSecondMatrixText;
    private JButton okButton;
    private JButton calculateButton;

    private JTable firstMultiplier;
    private JTable secondMultiplier;
    private JTable resultMatrix;

    private JLabel labelStatus;

    public static void main(final String[] args) {
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
        JLabel firstMatrixLabel = new JLabel("Height and width first matrix: ");
        heightFirstMatrixText = new JTextField("");
        widthFirstMatrixText = new JTextField("2");
        JLabel secondMatrixLabel = new JLabel("Height and width second matrix: ");
        heightSecondMatrixText = new JTextField("2");
        widthSecondMatrixText = new JTextField("1");
        okButton = new JButton("Ok");
        okButton.setEnabled(false);

        topPanel.add(firstMatrixLabel);
        topPanel.add(heightFirstMatrixText);
        topPanel.add(widthFirstMatrixText);
        topPanel.add(okButton);
        topPanel.add(secondMatrixLabel);
        topPanel.add(heightSecondMatrixText);
        topPanel.add(widthSecondMatrixText);
    }

    private void initializeMiddlePanel() {
        firstMultiplier = new JTable(new DefaultTableModel(1, 1));
        secondMultiplier = new JTable(new DefaultTableModel(1, 1));

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

        KeyAdapter keyListenerInputMatrixSizes = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                keyProcessInputMatrixSizes();
            }
        };

        heightFirstMatrixText.addKeyListener(keyListenerInputMatrixSizes);
        widthFirstMatrixText.addKeyListener(keyListenerInputMatrixSizes);
        heightSecondMatrixText.addKeyListener(keyListenerInputMatrixSizes);
        widthSecondMatrixText.addKeyListener(keyListenerInputMatrixSizes);


    }

    private void clickOkButton() {
        bindMatrixSizes();
        viewModel.processingInputMatrixSizes();
        backBindMatrixSizes();
        calculateButton.setEnabled(viewModel.isMultiplyButtonEnabled());
    }

    private void  bindMatrixSizes() {
        viewModel.setHeightFirstMatrixStr(heightFirstMatrixText.getText());
        viewModel.setWidthFirstMatrixStr(widthFirstMatrixText.getText());
        viewModel.setHeightSecondMatrixStr(heightSecondMatrixText.getText());
        viewModel.setWidthSecondMatrixStr(widthSecondMatrixText.getText());
    }

    private void backBindMatrixSizes() {
        okButton.setEnabled(viewModel.isOkButtonEnabled());
        reinitializeMiddlePanel();
        statusUpdate();
    }

    private void statusUpdate() {
        labelStatus.setText(viewModel.getStatus());
    }

    private void reinitializeMiddlePanel() {
        DefaultTableModel tableModel1 = (DefaultTableModel) firstMultiplier.getModel();
        tableModel1.setRowCount(viewModel.getHeightFirstMatrix());
        tableModel1.setColumnCount(viewModel.getWidthFirstMatrix());

        DefaultTableModel tableModel2 = (DefaultTableModel) secondMultiplier.getModel();
        tableModel2.setRowCount(viewModel.getHeightSecondMatrix());
        tableModel2.setColumnCount(viewModel.getWidthSecondMatrix());

        middlePanel.updateUI();
    }

    private void clickMultiplyButton() {
        bindMatrixes();
        viewModel.processingInputMatrices();
        backBindMatrixes();
    }

    private void bindMatrixes() {
        stopEditingMatrixes();
        initializeMultiplier(firstMultiplier);
        initializeMultiplier(secondMultiplier);
    }

    private void stopEditingMatrixes() {
        if (firstMultiplier.isEditing()) {
            firstMultiplier.getCellEditor().stopCellEditing();
        }
        if (secondMultiplier.isEditing()) {
            secondMultiplier.getCellEditor().stopCellEditing();
        }
    }

    private void initializeMultiplier(final JTable multiplier) {
        int columnCount = multiplier.getColumnCount();
        int rowCount = multiplier.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                String stringValue = (String) multiplier.getValueAt(i, j);
                if (multiplier.equals(firstMultiplier)) {
                    viewModel.setValueToFirstMatrix(i, j, stringValue);
                } else {
                    viewModel.setValueToSecondMatrix(i, j, stringValue);
                }
            }
        }
    }

    private void backBindMatrixes() {
        Matrix result = viewModel.getResultMatrix();
        reinitializeBottomPanel(result);
        statusUpdate();
    }

    private  void reinitializeBottomPanel(final Matrix result) {
        DefaultTableModel tableModel = (DefaultTableModel) resultMatrix.getModel();

        if (result == null) {
            tableModel.setRowCount(1);
            tableModel.setColumnCount(1);
        } else {
            tableModel.setRowCount(result.getHeight());
            tableModel.setColumnCount(result.getWidth());

            initializeResultMatrix(result);
        }
        bottomPanel.updateUI();
    }

    private void initializeResultMatrix(final Matrix result) {
        int columnCount = result.getWidth();
        int rowCount = result.getHeight();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                String value = result.getElement(i, j).convertToString();
                resultMatrix.setValueAt(value, i, j);
            }
        }
    }

    private void keyProcessInputMatrixSizes() {
        bindMatrixSizes();
        viewModel.parseInputMatrixSizes();
        statusUpdate();
        okButton.setEnabled(viewModel.isOkButtonEnabled());
        calculateButton.setEnabled(viewModel.isMultiplyButtonEnabled());
    }
}
