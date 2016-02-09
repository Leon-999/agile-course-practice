package ru.unn.agile.FinanceManagement.Model;

import java.util.Calendar;

public class Consumption {
    private String category;
    private String name;
    private Double count;
    private Double price;
    private Calendar date;

    public void addConsumption(final String category, final String name,
                               final Double count, final Double price, final Calendar date) {
        this.category = category;
        this.name = name;
        this.count = count;
        this.price = price;
        this.date = date;
    }

    public Calendar convertStringsToDate(final String year, final String month, final String day) {
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        return calendar;
    }

    @Override
    public int hashCode() {
        final int shift = 32;
        int result = 0;
        if (category != null) {
            result = category.hashCode();
        }
        result = (shift - 1) * result + name.hashCode();
        result = (shift - 1) * result + count.hashCode();
        result = (shift - 1) * result + price.hashCode();
        result = (shift - 1) * result + date.hashCode();
        return result;
    }
    private boolean isNotEquals(final Object obj1, final Object odj2) {
        if (obj1 != null && obj1.equals(odj2)
                || obj1 == null && odj2 != null) {
            return false;
        }
        return true;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Consumption that = (Consumption) obj;

        if (isNotEquals(category, that.category)) {
            return false;
        }
        if (isNotEquals(name, that.name)) {
            return false;
        }
        if (isNotEquals(price, that.price)) {
            return false;
        }
        if (isNotEquals(count, that.count)) {
            return false;
        }
        if (date != null
                && date.get(Calendar.YEAR) == that.date.get(Calendar.YEAR)
                && date.get(Calendar.MONTH) == that.date.get(Calendar.MONTH)
                && date.get(Calendar.DAY_OF_MONTH) == that.date.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return that.date == null;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Double getCount() {
        return count;
    }

    public Double getPrice() {
        return price;
    }

    public Calendar getDate() {
        return date;
    }
}
