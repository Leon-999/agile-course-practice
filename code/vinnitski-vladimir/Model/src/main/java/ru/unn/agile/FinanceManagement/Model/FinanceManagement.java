package ru.unn.agile.FinanceManagement.Model;

import java.util.*;

public class FinanceManagement {
    private final ArrayList<CategoryAndGoodsNames> memberCategory;
    private final ArrayList<Consumption> consumptions;
    private double balance;

    public FinanceManagement() {
        memberCategory = new ArrayList<CategoryAndGoodsNames>();
        consumptions = new ArrayList<Consumption>();
        balance = 0;
    }

    private void addNewCategory(final String category) {
        CategoryAndGoodsNames addedNewCategory =
                new CategoryAndGoodsNames(category);
        if (memberCategory.contains(addedNewCategory)) {
            return;
        }
        memberCategory.add(addedNewCategory);
    }

    private void addNewNameInCategory(final String category, final String name) {

        CategoryAndGoodsNames categoryFromAdded =
                new CategoryAndGoodsNames(category);
        Integer indexCategory = memberCategory.indexOf(categoryFromAdded);
        if (indexCategory >= 0) {
            memberCategory.get(indexCategory).tryAddGoodsName(name);
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
        CategoryAndGoodsNames categoryContainName =
                new CategoryAndGoodsNames(category);
        Integer indexCategory = memberCategory.indexOf(categoryContainName);
        if (indexCategory >= 0) {
            categoryContainName = memberCategory.get(indexCategory);
            arrayName = categoryContainName.getGoodsName();
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
