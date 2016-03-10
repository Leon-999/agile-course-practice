package ru.unn.agile.FinanceManagement.Model;

import java.util.ArrayList;

class CategoryAndGoodsNames {

    private final String category;
    private final ArrayList<String> goodsName;

    public CategoryAndGoodsNames(final String nameCategory) {
        category = nameCategory;
        goodsName = new ArrayList<String>();
    }

    @Override
    public int hashCode() {
        final int shift = 5;
        int result = category.hashCode();
        result = result << shift + goodsName.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        CategoryAndGoodsNames newCategory = (CategoryAndGoodsNames) object;
        return category.equals(newCategory.category);

    }

    public boolean tryAddGoodsName(final String goodsName) {
        if (!this.goodsName.contains(goodsName)) {
            this.addGoodsName(goodsName);
            return true;
        }
        return false;
    }

    private void addGoodsName(final String goodsName) {
        this.goodsName.add(goodsName);
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<String> getGoodsName() {
        return goodsName;
    }
}
