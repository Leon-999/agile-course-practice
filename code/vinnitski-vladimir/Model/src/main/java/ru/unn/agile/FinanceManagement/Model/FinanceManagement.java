package ru.unn.agile.FinanceManagement.Model;

import java.util.*;

public class FinanceManagement {

    private final ArrayList<CategoryAndNames> memberCategory;
    private final ArrayList<Consumption> consumptions;
    private double balance;

    public FinanceManagement() {
        memberCategory = new ArrayList<CategoryAndNames>();
        consumptions = new ArrayList<Consumption>();
        balance = 0;
    }

    private void addNewCategory(final String category) {

        CategoryAndNames addedNewCategory =
                new CategoryAndNames(category);

        if (memberCategory.contains(addedNewCategory)) {
            return;
        }

        memberCategory.add(addedNewCategory);
    }

    private void addNewNameInCategory(final String category, final String name) {

        CategoryAndNames categoryFromAdded =
                new CategoryAndNames(category);

        Integer indexCategory = memberCategory.indexOf(categoryFromAdded);

        if (indexCategory >= 0) {
            memberCategory.get(indexCategory).tryAddNewName(name);
        }
    }

    public ArrayList<String> getAllCategory() {
        ArrayList<String> categories = new ArrayList<String>();
        for (int i = 0; i < memberCategory.size(); i++) {
            categories.add(memberCategory.get(i).getCategory());
        }
        return categories;
    }

    public ArrayList<String> getAllNameInCategory(final String category) {

        ArrayList<String> arrayName = new ArrayList<String>();
        CategoryAndNames categoryContainName =
                new CategoryAndNames(category);

        Integer indexCategory = memberCategory.indexOf(categoryContainName);

        if (indexCategory >= 0) {
            categoryContainName = memberCategory.get(indexCategory);
            arrayName = categoryContainName.getName();
        }
        return arrayName;
    }

    public void addConsumption(final Consumption newConsumption) {
        balance -= newConsumption.getPrice();
        addNewCategory(newConsumption.getCategory());
        addNewNameInCategory(newConsumption.getCategory(), newConsumption.getName());
        consumptions.add(newConsumption);
    }

    public ArrayList<Consumption> getAllConsumptions() {
        return consumptions;
    }

    public void addNewSum(final double sum) {
        balance += sum;
    }

    public double getBalance() {
        return balance;
    }
}
