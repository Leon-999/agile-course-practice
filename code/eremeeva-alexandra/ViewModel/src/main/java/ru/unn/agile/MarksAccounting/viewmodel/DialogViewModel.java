package ru.unn.agile.MarksAccounting.viewmodel;

import ru.unn.agile.MarksAccounting.model.*;
import javax.swing.*;
import java.text.ParseException;

public abstract class DialogViewModel {
    private TableOfMarks tableOfMarks;
    private DialogType dialogType;
    private String dialogGroup;
    private String dialogStudent;
    private String dialogSubject;
    private String dialogDate;
    private String dialogInputTextBox;
    private boolean dialogGroupBoxVisible;
    private boolean dialogStudentBoxVisible;
    private boolean dialogSubjectBoxVisible;
    private boolean dialogDateTextBoxVisible;
    private boolean dialogInputTextBoxVisible;

    public DialogViewModel() {
        setAllInvisible();
        dialogType = DialogType.DEFAULT_DIALOG;
        tableOfMarks = new TableOfMarks();
    }

    private void setAllInvisible() {
        dialogDateTextBoxVisible = false;
        dialogGroupBoxVisible = false;
        dialogStudentBoxVisible = false;
        dialogSubjectBoxVisible = false;
        dialogInputTextBoxVisible = false;
    }

    public boolean isDialogInputTextBoxVisible() {
        return dialogInputTextBoxVisible;
    }

    public boolean isDialogGroupBoxVisible() {
        return dialogGroupBoxVisible;
    }

    public boolean isDialogStudentBoxVisible() {
        return dialogStudentBoxVisible;
    }

    public boolean isDialogSubjectBoxVisible() {
        return dialogSubjectBoxVisible;
    }

    public boolean isDialogDateTextBoxVisible() {
        return dialogDateTextBoxVisible;
    }

    public void setDialogGroup(final Object dialogGroup) {
        if (dialogGroup == null) {
            this.dialogGroup = "";
        } else {
            this.dialogGroup = dialogGroup.toString();
        }
    }

    public void setDialogStudent(final Object dialogStudent) {
        if (dialogStudent == null) {
            this.dialogStudent = "";
        } else {
            this.dialogStudent = dialogStudent.toString();
        }
    }

    public void setDialogSubject(final Object dialogSubject) {
        if (dialogSubject == null) {
            this.dialogSubject = "";
        } else {
            this.dialogSubject = dialogSubject.toString();
        }
    }

    public void setDialogDate(final String dialogDate) {
        this.dialogDate = dialogDate;
    }

    public void setDialogInputTextBoxVisible(final boolean dialogInputTextBoxVisible) {
        this.dialogInputTextBoxVisible = dialogInputTextBoxVisible;
    }

    public void setDialogDateTextBoxVisible(final boolean dialogDateTextBoxVisible) {
        this.dialogDateTextBoxVisible = dialogDateTextBoxVisible;
    }

    public void setDialogSubjectBoxVisible(final boolean dialogSubjectBoxVisible) {
        this.dialogSubjectBoxVisible = dialogSubjectBoxVisible;
    }

    public void setDialogStudentBoxVisible(final boolean dialogStudentBoxVisible) {
        this.dialogStudentBoxVisible = dialogStudentBoxVisible;
    }

    public void setDialogGroupBoxVisible(final boolean dialogGroupBoxVisible) {
        this.dialogGroupBoxVisible = dialogGroupBoxVisible;
    }

    public void setDialogType(final DialogType dialogType) {
        this.dialogType = dialogType;
    }

    public String getDialogGroup() {
        return dialogGroup;
    }

    public String getDialogStudent() {
        return dialogStudent;
    }

    public String getDialogSubject() {
        return dialogSubject;
    }

    public DialogType getDialogType() {
        return dialogType;
    }

    public String getDialogDate() {
        return dialogDate;
    }

    public TableOfMarks getTableOfMarks() {
        return tableOfMarks;
    }

    public String getDialogInputTextBox() {
        return dialogInputTextBox;
    }

    public ComboBoxModel<String> getDialogGroupsComboBoxModel() {
        return new JComboBox<String>(new String[0]).getModel();
    }

    public ComboBoxModel<String> getDialogStudentsComboBoxModel() {
        return new JComboBox<String>(new String[0]).getModel();
    }

    public ComboBoxModel<String> getDialogSubjectsComboBoxModel() {
        return new JComboBox<String>(new String[0]).getModel();
    }

    public void setTableOfMarks(final TableOfMarks tableOfMarks) {
        this.tableOfMarks = tableOfMarks;
    }

    public void setDialogInputTextBox(final String dialogInputTextBox) {
        this.dialogInputTextBox = dialogInputTextBox;
    }

    public abstract void changeTableOfMarks() throws ParseException;

    protected void setDialogFieldsByDefault() {
        dialogGroup = "";
        dialogStudent = "";
        dialogSubject = "";
        dialogDate = "";
        dialogInputTextBox = "";
    }

    protected ComboBoxModel<String> getComboBoxModelOfAllGroups() {
        String[] groupNumbers = getTableOfMarks().getGroupsAsArrayOfStrings();
        setDialogGroup(groupNumbers[0]);
        return new JComboBox<String>(groupNumbers).getModel();
    }

    protected ComboBoxModel<String> getComboBoxModelOfAllStudents() {
        String[] studentNames =
                getTableOfMarks().getStudentsAsArrayOfStrings(new Group(getDialogGroup()));
        setDialogStudent(studentNames[0]);
        return new JComboBox<String>(studentNames).getModel();
    }

    protected ComboBoxModel<String> getComboBoxModelOfAllSubjects() {
        String[] subjects = getTableOfMarks().getAcademicSubjectsAsArray(
                new Group(getDialogGroup()));
        setDialogSubject(subjects[0]);
        return new JComboBox<String>(subjects).getModel();
    }
}
