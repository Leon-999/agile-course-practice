package ru.unn.agile.FinanceManagement.Model;

import java.util.ArrayList;

class CategoryAndNames {

    private final String category;
    private final ArrayList<String> names;

    public CategoryAndNames(final String nameCategory) {
        this.category = nameCategory;
        this.names = new ArrayList<String>();
    }

    @Override
    public int hashCode() {
        final int shift = 5;
        int result = category.hashCode();
        result = result << shift + names.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        CategoryAndNames newCategory = (CategoryAndNames) object;

        return category.equals(newCategory.category);

    }

    public boolean tryAddNewName(final String name) {
        if (!this.names.contains(name)) {
            this.addNewName(name);

            return true;
        }

        return false;
    }

    private void addNewName(final String name) {
        this.names.add(name);
    }

    public String getCategory() {
        return this.category;
    }

    public ArrayList<String> getName() {
        return this.names;
    }
}
