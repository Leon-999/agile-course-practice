package ru.unn.agile.IntegrationMethods.view;

import ru.unn.agile.IntegrationMethods.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class Integrator {
    private JComboBox<ViewModel.Function> cmbFunction;
    private JTextField txtLowerLimit;
    private JTextField txtUpperLimit;
    private JComboBox<ViewModel.IntegrationMethod> cmbMethod;
    private JButton btnIntegrate;
    private JTextField txtResult;
    private JPanel mainPanel;
    private JLabel lbStatus;
    private ViewModel viewModel;

    private Integrator() { }

    private Integrator(final ViewModel viewModel) {
        this.viewModel = viewModel;
        bindFromViewModel();

        getFunctions();
        getMethods();

        btnIntegrate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                backBindToViewModel();
                viewModel.integrate();
                bindFromViewModel();
            }
        });

        cmbFunction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                backBindToViewModel();
                bindFromViewModel();
            }
        });

        cmbMethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                backBindToViewModel();
                bindFromViewModel();
            }
        });

        KeyAdapter keyListenerForLimits = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                backBindToViewModel();
                viewModel.processKeyPressing(e.getKeyCode());
                bindFromViewModel();
            }
        };
        txtLowerLimit.addKeyListener(keyListenerForLimits);
        txtUpperLimit.addKeyListener(keyListenerForLimits);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Integrator");
        frame.setContentPane(new Integrator(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void getFunctions() {
        ViewModel.Function[] functions = ViewModel.Function.values();
        cmbFunction.setModel(new JComboBox<>(functions).getModel());
    }

    private void getMethods() {
        ViewModel.IntegrationMethod[] methods = ViewModel.IntegrationMethod.values();
        cmbMethod.setModel(new JComboBox<>(methods).getModel());
    }

    private void backBindToViewModel() {
        viewModel.setLowerLimit(txtLowerLimit.getText());
        viewModel.setUpperLimit(txtUpperLimit.getText());

        viewModel.setFunction((ViewModel.Function) cmbFunction.getSelectedItem());
        viewModel.setIntegrationMethod((ViewModel.IntegrationMethod) cmbMethod.getSelectedItem());
    }

    private void bindFromViewModel() {
        btnIntegrate.setEnabled(viewModel.isIntegrateButtonEnabled());

        txtResult.setText(viewModel.getResult());
        lbStatus.setText(viewModel.getStatus());
    }
}
