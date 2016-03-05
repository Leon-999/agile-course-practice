package ru.unn.agile.FinanceManagement.Infrastructure;

import ru.unn.agile.FinanceManagement.Model.Consumption;
import ru.unn.agile.FinanceManagement.viewModel.ILoggerOfFinanceManagement;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;


public class TxtLoggerOfFinanceManagement implements ILoggerOfFinanceManagement {
    private final String logPath;
    private BufferedWriter logger;

    public TxtLoggerOfFinanceManagement(final String logPath) {
        this.logPath = logPath;
        try {
            logger = new BufferedWriter(new FileWriter(logPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addConsumption(final Consumption consumption) {
         try {
            String message = consumption.toString();
            logger.write(message);
            logger.newLine();
            logger.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Consumption stringToConsumption(final String message) {
        final String[] parcedMessage = message.split(",");
        final int categoryIndex = 0;
        final int nameIndex = 1;
        final int countIndex = 2;
        final int priceIndex = 3;
        final int yearIndex = 4;
        final int monthIndex = 5;
        final int dayIndex = 6;
        Calendar date;
        date = Calendar.getInstance();
        date.clear();
        date.set(Calendar.YEAR, Integer.parseInt(parcedMessage[yearIndex]));
        date.set(Calendar.MONTH, Integer.parseInt(parcedMessage[monthIndex]) - 1);
        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(parcedMessage[dayIndex]));
        Consumption consumption = new Consumption(parcedMessage[categoryIndex], parcedMessage[nameIndex],
                Double.parseDouble(parcedMessage[countIndex]),
                Double.parseDouble(parcedMessage[priceIndex]), date);
        return consumption;
    }

    @Override
    public ArrayList<Consumption> getConsumptions() {
        ArrayList<Consumption> log = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(logPath));

            String currentLine = reader.readLine();
            while (currentLine != null) {
                log.add(stringToConsumption(currentLine));
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return log;
    }
}
