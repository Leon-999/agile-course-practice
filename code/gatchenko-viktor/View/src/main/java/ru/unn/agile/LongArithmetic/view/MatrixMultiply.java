package ru.unn.agile.LongArithmetic.view;

import ru.unn.agile.LongArithmetic.model.Matrix;
import ru.unn.agile.LongArithmetic.viewmodel.KeyboardKeys;
import ru.unn.agile.LongArithmetic.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MatrixMultiply {

    //private Object[] headers = { "Name", "Surname", "Telephone" };

    /*private Object[][] data = {
            { "John", "Smith", "1112221" },
            { "Ivan", "Black", "2221111" },
            { "George", "White", "3334444" },
            { "Bolvan", "Black", "2235111" },
            { "Serg", "Black", "2221511" },
            { "Pussy", "Black", "2221111" },
            { "Tonya", "Red", "2121111" },
            { "Elise", "Green", "2321111" },
    };*/

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

    private KeyAdapter keyListener;

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

        keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                keyProcess(e);
            }
        };

        mFirstMatrixText.addKeyListener(keyListener);
        nFirstMatrixText.addKeyListener(keyListener);
        mSecondMatrixText.addKeyListener(keyListener);
        nSecondMatrixText.addKeyListener(keyListener);
    }

    private void initializePanels() {
        mainPanel = new JPanel(new FlowLayout());
        topPanel = new JPanel(new GridLayout(2,4));
        middlePanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new FlowLayout());
    }

    private void initializeTopPanel() {
        firstMatrixLabel = new JLabel("M and N first matrix:");
        mFirstMatrixText = new JTextField("1");//("3");
        nFirstMatrixText = new JTextField("2");
        secondMatrixLabel = new JLabel("M and N second matrix:");
        mSecondMatrixText = new JTextField("2");
        nSecondMatrixText = new JTextField("1");
        okButton = new JButton("Ok");

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
        resultMatrix.setEnabled(false);

        calculateButton = new JButton("Multiply");
        bottomPanel.add(calculateButton);
        bottomPanel.add(resultMatrix);

        labelStatus = new JLabel();
        bottomPanel.add(labelStatus);
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
                new  MatrixMultiply(new ViewModel());
            }
        });
    }

    private void clickOkButton() {
        bindMN();
        MatrixMultiply.this.viewModel.createMatrices();
        backBindMN();
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
        labelStatus.setText(viewModel.getStatus());
    }

    private void reinitializeMiddlePanel() {
        middlePanel.remove(firstMultiplier);
        middlePanel.remove(secondMultiplier);

        firstMultiplier = new JTable(viewModel.getM1(), viewModel.getN1());
        //firstMultiplier.setValueAt("1", 0, 0);
        secondMultiplier = new JTable(viewModel.getM2(), viewModel.getN2());

        middlePanel.add(firstMultiplier);
        middlePanel.add(secondMultiplier);
        middlePanel.updateUI();
    }

    private void clickMultiplyButton() {
        bindMatrixes();
        MatrixMultiply.this.viewModel.multiplyMatrices();
        backBindMatrixes();
    }

    private void bindMatrixes() {
        //firstMultiplier.
        //secondMultiplier.updateUI();
        //firstMultiplier.removeEditor();
        //secondMultiplier.removeEditor();
        boolean t = firstMultiplier.isEditing();
        int p = firstMultiplier.getEditingRow();
        int k = firstMultiplier.getEditingColumn();
        firstMultiplier.getCellEditor().stopCellEditing();
        secondMultiplier.getCellEditor().stopCellEditing();
        //firstMultiplier.editingStopped(null);
        //secondMultiplier.editingStopped(null);
        int columnCount = firstMultiplier.getColumnCount();
        int rowCount = firstMultiplier.getRowCount();
        for(int i = 0; i < rowCount; i++) {
            for(int j = 0; j < columnCount; j++) {
                //firstMultiplier.removeEditor();
                //firstMultiplier.editingStopped(null);
                //Object o = firstMultiplier.getValueAt(i, j);
                //TableCellEditor editor = firstMultiplier.getCellEditor(0, 0);
                //Object o2 = editor.getCellEditorValue();
                String stringValue = (String)firstMultiplier.getValueAt(i, j);
                viewModel.setValueToFirstMatrix(i, j, stringValue);
            }
        }

        columnCount = secondMultiplier.getColumnCount();
        rowCount = secondMultiplier.getRowCount();
        for(int i = 0; i < rowCount; i++) {
            for(int j = 0; j < columnCount; j++) {
                String value = (String)secondMultiplier.getValueAt(i, j);
                viewModel.setValueToSecondMatrix(i, j, value);
            }
        }
    }

    private void backBindMatrixes() {
        Matrix result = viewModel.getResultMatrix();
        int columnCount = result.getWidth();
        int rowCount = result.getHeight();
        bottomPanel.remove(resultMatrix);
        resultMatrix = new JTable(rowCount, columnCount);
        for(int i = 0; i < rowCount; i++) {
            for(int j = 0; j < columnCount; j++) {
                String value = result.getElement(i, j).convertToString();
                resultMatrix.setValueAt((Object)value, i, j);
                //resultMatrix.removeEditor();
                //resultMatrix.editingStopped(null);
            }
        }
        bottomPanel.add(resultMatrix);
        bottomPanel.updateUI();

    }

    private void keyProcess(final KeyEvent e) {
        if(e.getKeyCode() == KeyboardKeys.ENTER) {
            clickOkButton();
        } else if(e.getKeyCode() == KeyboardKeys.M) {

        }
                /*bind();
                Calculator.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();*/
    }
}
