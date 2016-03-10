package ru.unn.agile.FinanceManagement.Model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class CategoryAndGoodsNamesTest {
    private CategoryAndGoodsNames consumption;

    @Before
    public void initializeTest() {
        String nameCategory = "new category";
        consumption = new CategoryAndGoodsNames(nameCategory);
    }

    @Test
    public void canGetCategory() {
        String nameCategory = "new category";

        assertEquals(nameCategory, consumption.getCategory());
    }

    @Test
    public void canGetHashCode() {
        int hashCode = consumption.hashCode();

        initializeTest();

        assertEquals(hashCode, consumption.hashCode());
    }

    @Test
    public void canAddNewName() {
        String newName = "name 1";

        assertTrue(consumption.tryAddGoodsName(newName));
    }

    @Test
    public void canAddOldName() {
        String newName = "name 1";

        consumption.tryAddGoodsName(newName);

        assertFalse(consumption.tryAddGoodsName(newName));
    }

    @Test
    public void cangetGoodsName() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("name 1");
        names.add("name 2");

        consumption.tryAddGoodsName(names.get(0));
        consumption.tryAddGoodsName(names.get(1));

        assertEquals(names, consumption.getGoodsName());
    }

    @Test
    public void canEqualsDifferentClass() {
        String diffClass = "name 1";

        assertFalse(consumption.equals(diffClass));
    }
}
