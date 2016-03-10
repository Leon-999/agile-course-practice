package ru.unn.agile.VolumesComputer.View;

import ru.unn.agile.VolumesComputer.Infrastructure.LoggerFile;
import ru.unn.agile.VolumesComputer.ViewModel.ComputerViewModel;
import ru.unn.agile.VolumesComputer.ViewModel.FigureName;

import javax.swing.*;
import java.awt.event.*;

public final class Computer {
    private JComboBox<FigureName> comboBoxFigures;
    private JTextField textFieldParameter1;
    private JTextField textFieldParameter2;
    private JTextField textFieldParameter3;
    private JTextField textFieldVolume;
    private JPanel panelMain;
    private JLabel labelParameter1;
    private JLabel labelParameter2;
    private JLabel labelParameter3;
    private JLabel labelVolume;
    private JButton buttonSolve;

    private final ComputerViewModel viewModel;

    public static void main(final String[] agrs) {
        JFrame frame = new JFrame("VolumesComputer");
        LoggerFile logger = new LoggerFile("./Computer.log");
        ComputerViewModel viewModel = new ComputerViewModel(logger);
        Computer computer = new Computer(viewModel);
        frame.setContentPane(computer.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Computer(final ComputerViewModel viewModel) {
        this.viewModel = viewModel;
        textFieldParameter1initialize();
        textFieldParameter2initialize();
        textFieldParameter3initialize();
        textFieldVolumeInitialize();
        comboBoxFiguresInitialize();
        buttonSolveInitialize();
    }
    private void comboBoxFiguresInitialize() {
        comboBoxFigures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                viewModel.setFigure(
                        (FigureName) comboBoxFigures.getSelectedItem());
                labelParameter1.setText(viewModel.getParameter1name() + ":");
                labelParameter2.setText(viewModel.getParameter2name() + ":");
                labelParameter3.setText(viewModel.getParameter3name() + ":");
                labelParameter1.setVisible(viewModel.isParameter1enabled());
                labelParameter2.setVisible(viewModel.isParameter2enabled());
                labelParameter3.setVisible(viewModel.isParameter3enabled());
                textFieldParameter1.setVisible(viewModel.isParameter1enabled());
                textFieldParameter2.setVisible(viewModel.isParameter2enabled());
                textFieldParameter3.setVisible(viewModel.isParameter3enabled());
                buttonSolve.setEnabled(viewModel.isInputCorrect());
                textFieldVolume.setText(viewModel.getVolume());
            }
        });
        final FigureName[] figures = FigureName.values();
        comboBoxFigures.setModel(new JComboBox<>(figures).getModel());
        comboBoxFigures.setSelectedIndex(0);
    }
    private void textFieldParameter1initialize() {
        textFieldParameter1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                viewModel.setParameter1(textFieldParameter1.getText());
                buttonSolve.setEnabled(viewModel.isInputCorrect());
            }
        });
        textFieldParameter1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent e) {
                viewModel.update1();
            }
        });
    }
    private void textFieldParameter2initialize() {
        textFieldParameter2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                viewModel.setParameter2(textFieldParameter2.getText());
                buttonSolve.setEnabled(viewModel.isInputCorrect());
            }
        });
        textFieldParameter2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent e) {
                viewModel.update2();
            }
        });
    }
    private void textFieldParameter3initialize() {
        textFieldParameter3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                viewModel.setParameter3(textFieldParameter3.getText());
                buttonSolve.setEnabled(viewModel.isInputCorrect());
            }
        });
        textFieldParameter3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent e) {
                viewModel.update3();
            }
        });
    }
    private void textFieldVolumeInitialize() {
        textFieldVolume.setEditable(false);
        textFieldVolume.setText(viewModel.getVolume());
    }
    private void buttonSolveInitialize() {
        buttonSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                viewModel.setParameter1(textFieldParameter1.getText());
                viewModel.setParameter2(textFieldParameter2.getText());
                viewModel.setParameter3(textFieldParameter3.getText());
                viewModel.solve();
                textFieldVolume.setText(viewModel.getVolume());
            }
        });
    }
}
