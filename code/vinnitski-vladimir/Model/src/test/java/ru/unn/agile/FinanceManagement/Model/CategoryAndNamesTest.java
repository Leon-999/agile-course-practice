package ru.unn.agile.FinanceManagement.Model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class CategoryAndNamesTest {

    private CategoryAndNames consumption;

    private String initializeTest() {
        String nameCategory = "new category";
        consumption = new CategoryAndNames(nameCategory);
        return nameCategory;
    }

    @Test
    public void canGetCategory() {
        String nameCategory = initializeTest();

        assertEquals(nameCategory, consumption.getCategory());
    }

    @Test
    public void canGetHashCode() {
        initializeTest();
        int hashCode = consumption.hashCode();
        initializeTest();
        assertEquals(hashCode, consumption.hashCode());
    }

    @Test
    public void canAddNewName() {
        initializeTest();
        String newName = "name 1";
        assertTrue(consumption.tryAddNewName(newName));
    }

    @Test
    public void canAddOldName() {
        initializeTest();
        String newName = "name 1";
        consumption.tryAddNewName(newName);
        assertFalse(consumption.tryAddNewName(newName));
    }

    @Test
    public void canGetName() {
        initializeTest();
        ArrayList<String> names = new ArrayList<String>();
        names.add("name 1");
        names.add("name 2");
        consumption.tryAddNewName(names.get(0));
        consumption.tryAddNewName(names.get(1));
        assertEquals(names, consumption.getName());
    }

    @Test
    public void canEqualsDifferentClass() {
        initializeTest();
        String diffClass = "name 1";
        assertFalse(consumption.equals(diffClass));
    }
}
