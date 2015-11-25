package ru.unn.agile.LeftistHeap.view;

import ru.unn.agile.LeftistHeap.viewmodel.LeftistHeapViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class HeapForm {
    private final LeftistHeapViewModel viewModel;
    private JPanel rootPanel;
    private JTextField keyValueTextField;
    private JButton applyOperationButton;
    private JTextArea errorMessageTextField;
    private JTextArea heapContentTextField;
    private JComboBox<LeftistHeapViewModel.Operations> operationComboBox;

    private HeapForm() {
        viewModel = new LeftistHeapViewModel();

        operationComboBox.setModel(new JComboBox<>(
                LeftistHeapViewModel.Operations.values()).getModel());

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.applyOperation();
                bind();
            }
        };
        applyOperationButton.addActionListener(buttonListener);

        DocumentListener fieldsListener = new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                backBind();
                bind();
            }
        };

        keyValueTextField.getDocument().addDocumentListener(fieldsListener);
        errorMessageTextField.setForeground(Color.RED);

        bind();
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("HeapForm");
        frame.setContentPane(new HeapForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void backBind() {
        viewModel.setKeyValue(keyValueTextField.getText());
        viewModel.setOperation((LeftistHeapViewModel.Operations)
                                operationComboBox.getSelectedItem());
    }

    private void bind() {
        applyOperationButton.setEnabled(viewModel.isApplyButtonEnabled());
        heapContentTextField.setText(viewModel.getHeapContent());
        errorMessageTextField.setText(viewModel.getErrorText());
    }
}
