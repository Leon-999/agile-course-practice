package ru.unn.agile.LongArithmetic.view;

import javax.swing.*;
import java.awt.*;

public class MatrixMultiply {
    /*private JTable table1;
    private JPanel panel1;
    private JButton[] testButtons;

    //Массив содержащий заголоки таблицы
    private Object[] headers = { "Name", "Surname", "Telephone" };

    //Массив содержащий информацию для таблицы
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

    private MatrixMultiply() {}

    private static void createUIComponents() {
        // TODO: place custom component creation code here

    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Calculator");

        MatrixMultiply form = new MatrixMultiply();
        frame.setContentPane(form.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        //createUIComponents();
        frame.setSize(500,500);
        form.panel1.setLayout(new GridLayout(2,2));
        int count = 3;
        form.testButtons = new JButton[count];
        for(int i = 0; i < count; i++) {
            form.testButtons[i] = new JButton("test");
            form.testButtons[i].setVisible(true);
            form.testButtons[i].setSize(30, 70);
            //form.testButtons[i].setLocation(i * 30, 0);
        }

        for(int i = 0; i < count; i++) {
            form.panel1.add(form.testButtons[i]);
        }

        form.table1 = new JTable(form.data, form.headers);
        JScrollPane jscrlp = new JScrollPane(form.table1);
        frame.getContentPane().add(jscrlp);

        frame.setVisible(true);
    }*/

    //Массив содержащий заголоки таблицы
    private Object[] headers = { "Name", "Surname", "Telephone" };

    //Массив содержащий информацию для таблицы
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
        //Устанавливаем диспетчер компоновки
        //frame.getContentPane().setLayout(new FlowLayout());
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new FlowLayout());
        topPanel = new JPanel(new GridLayout(2,4));
        middlePanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new FlowLayout());

        firstMultiplier = new JTable(data, headers);
        secondMultiplier = new JTable(data, headers);
        resultMatrix = new JTable(data, headers);
        //Создаем панель прокрутки и включаем в ее состав нашу таблицу
        //JScrollPane jscrlp = new JScrollPane(firstMultiplier);
        //Устаналиваем размеры прокручиваемой области
        //firstMultiplier.setPreferredScrollableViewportSize(new Dimension(250, 100));
        //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней

        firstMatrixLabel = new JLabel("M and N first matrix:");
        mFirstMatrixText = new JTextField();
        nFirstMatrixText = new JTextField();
        //nFirstMatrixText;
        secondMatrixLabel = new JLabel("M and N second matrix:");
        mSecondMatrixText = new JTextField();
        nSecondMatrixText = new JTextField();
        okButton = new JButton("ok");

        topPanel.add(firstMatrixLabel);
        topPanel.add(mFirstMatrixText);
        topPanel.add(nFirstMatrixText);
        topPanel.add(okButton);
        topPanel.add(secondMatrixLabel);
        topPanel.add(mSecondMatrixText);
        topPanel.add(nSecondMatrixText);

        middlePanel.add(firstMultiplier);
        middlePanel.add(secondMultiplier);

        calculateButton = new JButton("Multiply");
        bottomPanel.add(calculateButton);
        bottomPanel.add(resultMatrix);

        //Отображаем контейнер
        mainPanel.add(topPanel);
        mainPanel.add(middlePanel);
        mainPanel.add(bottomPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
        //mainPanel.setVisible(true);
    }

    //Функция main, запускающаяся при старте приложения
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
