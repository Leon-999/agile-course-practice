package ru.unn.agile.Vec3.View;

import ru.unn.agile.Vec3.ViewModel.Vector3ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Vector3Viewer {
    private JPanel mainPanel;
    private JTextField txtCoordZ0;
    private JTextField txtCoordY0;
    private JTextField txtCoordX0;
    private JLabel lblCoordX0;
    private JLabel lblCoordY0;
    private JLabel lblCoordZ0;
    private JTextField txtCoordZ1;
    private JTextField txtCoordY1;
    private JTextField txtCoordX1;
    private JLabel lblCoordX1;
    private JLabel lblCoordY1;
    private JLabel lblCoordZ1;
    private JTextField txtResult;
    private JLabel lblResult;
    private JComboBox<ActionList> cmbActionList;
    private JButton btnCalculate;

    private Vector3ViewModel viewModel;

    private Vector3Viewer() { }

    private Vector3Viewer(final Vector3ViewModel viewModel) {
        this.viewModel = viewModel;

        loadActionList();

        createUIActions();

        backBind();
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Vector3dViewer");

        frame.setContentPane(new Vector3Viewer(new Vector3ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIActions() {
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();

                switch ((ActionList) cmbActionList.getSelectedItem()) {
                    case GET_NORM_FIRST_VECTOR:     viewModel.getNormOfFirstVector();
                                                    break;

                    case GET_NORM_SECOND_VECTOR:    viewModel.getNormOfSecondVector();
                                                    break;

                    case NORMAlIZE_FIRST_VECTOR:    viewModel.normalizeFirstVector();
                                                    break;

                    case NORMALIZE_SECOND_VECTOR:   viewModel.normalizeSecondVector();
                                                    break;

                    case CALCULATE_DOT_PRODUCT:     viewModel.getDotProduct();
                                                    break;

                    case CALCULATE_CROSS_PRODUCT:   viewModel.getCrossProduct();
                                                    break;

                    default: break;
                }

                bind();
            }
        });
    }

    private void loadActionList() {
        ActionList[] actions = ActionList.values();
        cmbActionList.setModel(new JComboBox<>(actions).getModel());
    }

    private  void backBind() {
        viewModel.setCoordX0(txtCoordX0.getText());
        viewModel.setCoordY0(txtCoordY0.getText());
        viewModel.setCoordZ0(txtCoordZ0.getText());

        viewModel.setCoordX1(txtCoordX1.getText());
        viewModel.setCoordY1(txtCoordY1.getText());
        viewModel.setCoordZ1(txtCoordZ1.getText());
    }

    private void bind() {
        txtCoordX0.setText(viewModel.getCoordX0());
        txtCoordY0.setText(viewModel.getCoordY0());
        txtCoordZ0.setText(viewModel.getCoordZ0());

        txtCoordX1.setText(viewModel.getCoordX1());
        txtCoordY1.setText(viewModel.getCoordY1());
        txtCoordZ1.setText(viewModel.getCoordZ1());

        txtResult.setText(viewModel.getResultOfLastAction());
    }
}
