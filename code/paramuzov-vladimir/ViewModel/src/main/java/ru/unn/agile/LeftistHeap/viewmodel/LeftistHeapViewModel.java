package ru.unn.agile.LeftistHeap.viewmodel;

import ru.unn.agile.LeftistHeap.model.LeftistHeap;
import ru.unn.agile.LeftistHeap.model.LeftistHeapNode;

import java.util.Arrays;

public class LeftistHeapViewModel {
    public enum Errors {
        FIELD_BAD_FORMAT("Wrong value in text field. Only integers, please.\n"),
        VALUE_TO_DELETE_NOT_FOUND("Element to delete not found.\n");

        private String errorMessage;

        Errors(final String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getMessage() {
            return errorMessage;
        }
    }

    public enum Operations {
        INSERT("Insert number"),
        DELETE("Delete number");

        private String operation;

        Operations(final String operation) {
            this.operation = operation;
        }

        @Override
        public String toString() {
            return operation;
        }
    }

    private final LeftistHeap<Integer> heap;
    private boolean applyButtonEnabled = false;
    private String keyValue;
    private String heapContent = "[]";
    private String errorText = "";
    private Operations operation = Operations.INSERT;

    public LeftistHeapViewModel() {
        heap = new LeftistHeap<>();
    }

    public boolean isApplyButtonEnabled() {
        return applyButtonEnabled;
    }

    public void setKeyValue(final String keyValue) {
        if ("".equals(keyValue)) {
            errorText = "";
            applyButtonEnabled = false;
            return;
        }

        if (!keyValue.matches("\\d+|-\\d+")) {
            errorText = Errors.FIELD_BAD_FORMAT.getMessage();
            applyButtonEnabled = false;
            return;
        }

        errorText = "";
        applyButtonEnabled = true;
        this.keyValue = keyValue;
    }

    public void setOperation(final Operations operation) {
        this.operation = operation;
    }

    public Operations getOperation() {
        return operation;
    }

    public void applyOperation() {
        if (operation == Operations.INSERT) {
            insertElement();
        } else {
            deleteElement();
        }
    }

    public String getHeapContent() {
        return heapContent;
    }

    public String getErrorText() {
        return errorText;
    }

    private void insertElement() {
            heap.insert(Integer.parseInt(keyValue));
            Object[] content = heap.toSortedArray();
            heapContent = Arrays.toString(content);
    }

    private void deleteElement() {
        LeftistHeapNode<Integer> nodeToDelete;
        try {
            nodeToDelete = heap.findNodeByKey(Integer.parseInt(keyValue));
        } catch (NullPointerException e) {
            errorText =  Errors.VALUE_TO_DELETE_NOT_FOUND.getMessage();
            return;
        }
        errorText = "";
        heap.delete(nodeToDelete);
        Object[] content = heap.toSortedArray();
        heapContent = Arrays.toString(content);
    }
}
