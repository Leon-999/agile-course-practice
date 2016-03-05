package ru.unn.agile.FinanceManagement.viewModel;

import ru.unn.agile.FinanceManagement.Model.Consumption;
import ru.unn.agile.FinanceManagement.Model.FinanceManagement;
import java.util.ArrayList;
import java.util.Calendar;


public class FinanceManagerViewModel {
    private final FinanceManagement model;
    private ArrayList<String> arrayCategory;
    private ArrayList<String> arrayName;
    private String category;
    private String[][] arrayConsumptions;
    private String name;
    private String count;
    private String price;
    private String year;
    private String month;
    private String day;
    private static final Integer COUNT_COLUMNS = 7;
    private String countErrorMessage;
    private String priceErrorMessage;
    private boolean enabledAddConsumptionButton;
    private boolean enabledNameTextbox;
    private boolean enabledCountAndPriceTextbox;
    private final Integer[] years;
    private final Integer[] months;
    private static final Integer COUNT_MONTH = 12;
    private Integer selectedDay = 0;
    private Integer selectedMonth = 0;
    private Integer selectedYear = 0;
    private Double balance = 0.;
    private static final String EMPTY_STRING = "";
    private final ILoggerOfFinanceManagement logger;

    public FinanceManagerViewModel(final ILoggerOfFinanceManagement logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
        model = new FinanceManagement();
        arrayCategory = new ArrayList<String>();
        arrayName = new ArrayList<String>();
        arrayConsumptions = new String[0][COUNT_COLUMNS];
        enabledAddConsumptionButton = false;
        priceErrorMessage = "";
        countErrorMessage = "";
        enabledNameTextbox = false;
        final Integer countYears = 5;
        final Integer startYear = 2013;
        years = new Integer[countYears];
        for (int i = 0; i < countYears; i++) {
            years[i] = startYear + i;
        }
        day = "1";
        months = new Integer[COUNT_MONTH];
        for (int i = 0; i < COUNT_MONTH; i++) {
            months[i] = i + 1;
        }
        year = years[0].toString();
        month = months[0].toString();
        loadAll();
        refreshCategory();
        refreshConsumptions();
    }

    private void loadAll() {
        final ArrayList<Consumption> oldConsumptions = logger.getConsumptions();
        for (int i = 0; i < oldConsumptions.size(); i++) {
            model.addConsumption(oldConsumptions.get(i));
        }
    }

    private void refreshCategory() {
        arrayCategory = model.getAllCategory();
    }

    public String[] getArrayCategory() {
        return arrayCategory.toArray(new String[arrayCategory.size()]);
    }

    private void refreshName() {
        arrayName = model.getAllNameInCategory(category);
    }

    public String[] getArrayName() {
        return arrayName.toArray(new String[arrayName.size()]);
    }

    public void setCategory(final String category) {
        enabledNameTextbox = true;
        if (category == null) {
            enabledNameTextbox = false;
            return;
        }
        if (category.equals(EMPTY_STRING)) {
            enabledNameTextbox = false;
        }
        this.category = category;
        refreshName();
    }

    public String[][] getArrayConsumptions() {
        return arrayConsumptions;
    }

    public void setName(final String name) {
        enabledCountAndPriceTextbox = true;
        if (name == null) {
            enabledCountAndPriceTextbox = false;
            return;
        }
        if (name.equals(EMPTY_STRING)) {
            enabledCountAndPriceTextbox = false;
        }
        updateEnabledAddConsumptionButton();
        this.name = name;
    }

    public void setCount(final String count) {
        countErrorMessage = "";
        updateEnabledAddConsumptionButton();
        if (count == null || count.equals(EMPTY_STRING)) {
            this.count = "0";
            return;
        }
        if (!count.matches("\\d+[.]?\\d*")) {
            countErrorMessage = "Please enter only numbers in the 'Count'";
            enabledAddConsumptionButton = false;
            return;
        }
        this.count = count;
    }

    public void setPrice(final String price) {
        priceErrorMessage = "";
        updateEnabledAddConsumptionButton();
        if (price == null || price.equals(EMPTY_STRING)) {
            this.price = "0";
            return;
        }
        if (!price.matches("\\d+.?\\d*")) {
            priceErrorMessage = "Please enter only numbers in the 'Price'";
            enabledAddConsumptionButton = false;
            return;
        }
        this.price = price;
    }

    private void updateEnabledAddConsumptionButton() {
        if (!countErrorMessage.equals(EMPTY_STRING) || !priceErrorMessage.equals(EMPTY_STRING)
                || !enabledCountAndPriceTextbox) {
            enabledAddConsumptionButton = false;
            return;
        }
        enabledAddConsumptionButton = true;
    }

    public void setYear(final String year) {
        this.year = year;
    }

    public void setMonth(final String month) {
        this.month = month;
    }

    public void setDay(final String day) {
        this.day = day;
    }

    public void addConsumption() {
        Consumption consumption = new Consumption();
        Calendar date = consumption.convertStringsToDate(year, month, day);
        Consumption consumption = new Consumption(category, name, Double.parseDouble(count),
                Double.parseDouble(price), date);
        model.addConsumption(consumption);
        logger.addConsumption(consumption);
        refreshConsumptions();
        refreshCategory();
    }

    private void refreshConsumptions() {
        ArrayList<Consumption> consumptions = model.getAllConsumptions();
        arrayConsumptions = new String[consumptions.size()][COUNT_COLUMNS];
        final int indexOfCategory = 0;
        final int indexOfName = 1;
        final int indexOfCount = 2;
        final int indexOfPrice = 3;
        final int indexOfYear = 4;
        final int indexOfMonth = 5;
        final int indexOfDay = 6;
        for (int i = 0; i < consumptions.size(); i++) {
            arrayConsumptions[i][indexOfCategory] = consumptions.get(i).getCategory();
            arrayConsumptions[i][indexOfName] = consumptions.get(i).getName();
            arrayConsumptions[i][indexOfCount] = consumptions.get(i).getCount().toString();
            arrayConsumptions[i][indexOfPrice] = consumptions.get(i).getPrice().toString();
            Calendar date = consumptions.get(i).getDate();
            arrayConsumptions[i][indexOfYear] = Integer.toString(date.get(Calendar.YEAR));
            arrayConsumptions[i][indexOfMonth] = Integer.toString(date.get(Calendar.MONTH) + 1);
            arrayConsumptions[i][indexOfDay] = Integer.toString(date.get(Calendar.DAY_OF_MONTH));
        }
    }

    public String getCountErrorMessage() {
        return countErrorMessage;
    }

    public String getPriceErrorMessage() {
        return priceErrorMessage;
    }

    public boolean isEnabledAddConsumptionButton() {
        return enabledAddConsumptionButton;
    }

    public boolean isEnabledNameTextbox() {
        return enabledNameTextbox;
    }

    public boolean isEnabledCountAndPriceTextbox() {
        return enabledCountAndPriceTextbox;
    }

    public Integer[] getYears() {
        return years;
    }

    public Integer[] getMonths() {
        return months;
    }

    public Integer[] getDays() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        int countDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Integer[] days = new Integer[countDay];
        for (int i = 0; i < countDay; i++) {
            days[i] = i + 1;
        }
        return days;
    }

    public void setSelectedDay(final Integer selectedDay) {
        this.selectedDay = selectedDay;
    }

    public Integer getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedMonth(final Integer selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public Integer getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedYear(final Integer selectedYear) {
        this.selectedYear = selectedYear;
    }

    public Integer getSelectedYear() {
        return selectedYear;
    }

    public void addedBalance(final String addedBalance) {
        balance += Double.parseDouble(addedBalance);
    }

    public Double getBalance() {
        return balance;
    }
}
