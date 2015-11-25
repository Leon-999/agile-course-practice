package ru.unn.agile.LeftistHeap.viewmodel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LeftistHeapViewModelTest {
    private LeftistHeapViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new LeftistHeapViewModel();
    }

    @Test
    public void byDefaultApplyButtonIsDisabled() {
        assertFalse(viewModel.isApplyButtonEnabled());
    }

    @Test
    public void whenInputIntegerToKeyValueFieldInsertButtonIsEnabled() {
        viewModel.setKeyValue("1");

        assertTrue(viewModel.isApplyButtonEnabled());
    }

    @Test
    public void whenClearKeyValueFieldApplyButtonIsDisabled() {
        viewModel.setKeyValue("1");
        viewModel.setKeyValue("");

        assertFalse(viewModel.isApplyButtonEnabled());
    }

    @Test
    public void whenInsertOneIntegerToHeapContentDisplayed() {
        viewModel.setKeyValue("1");
        viewModel.setOperation(LeftistHeapViewModel.Operations.INSERT);
        viewModel.applyOperation();

        assertEquals("[1]", viewModel.getHeapContent());
    }

    @Test
    public void whenInsertTwoIntegersToHeapContentDisplayed() {
        viewModel.setOperation(LeftistHeapViewModel.Operations.INSERT);
        viewModel.setKeyValue("1");
        viewModel.applyOperation();
        viewModel.setKeyValue("2");
        viewModel.applyOperation();

        assertEquals("[1, 2]", viewModel.getHeapContent());
    }

    @Test
    public void whenInputWrongKeyValueErrorDisplayed() {
        viewModel.setKeyValue("afc");

        assertEquals(LeftistHeapViewModel.Errors.FIELD_BAD_FORMAT.getMessage(),
                     viewModel.getErrorText());
    }

    @Test
    public void whenInputWrongValueToKeyFieldApplyButtonIsDisabled() {
        viewModel.setKeyValue("afc");

        assertFalse(viewModel.isApplyButtonEnabled());
    }

    @Test
    public void byDefaultOperationIsInsert() {
        assertEquals(LeftistHeapViewModel.Operations.INSERT.toString(),
                     viewModel.getOperation().toString());
    }

    @Test
    public void canChangeOperation() {
        viewModel.setOperation(LeftistHeapViewModel.Operations.DELETE);

        assertEquals(LeftistHeapViewModel.Operations.DELETE, viewModel.getOperation());
    }

    @Test
    public void whenTryingToDeleteNotExistingElementErrorDisplayed() {
        viewModel.setKeyValue("1");
        viewModel.setOperation(LeftistHeapViewModel.Operations.DELETE);
        viewModel.applyOperation();

        assertEquals(LeftistHeapViewModel.Errors.VALUE_TO_DELETE_NOT_FOUND.getMessage(),
                     viewModel.getErrorText());
    }

    @Test
    public void canDeleteExistingElementFromHeap() {
        viewModel.setOperation(LeftistHeapViewModel.Operations.INSERT);
        viewModel.setKeyValue("1");
        viewModel.applyOperation();
        viewModel.setOperation(LeftistHeapViewModel.Operations.DELETE);
        viewModel.applyOperation();

        assertEquals("[]", viewModel.getHeapContent());
    }

    @Test
    public void whenFixKeyValueFormatErrorHide() {
        viewModel.setKeyValue("afc");
        viewModel.setKeyValue("1");

        assertEquals("", viewModel.getErrorText());
    }
}
