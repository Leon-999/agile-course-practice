package ru.unn.agile.PomodoroTimer.infrastructure;

public class LogRecordXmlTag {
    private String name;
    private String timeAttributeName;
    private String messageAttributeName;
    private String timeAttributeValue;
    private String messageAttributeValue;

    public LogRecordXmlTag() {
        this.name = "event";
        this.timeAttributeName = "time";
        this.messageAttributeName = "message";
        this.timeAttributeValue = "";
        this.messageAttributeValue = "";
    }

    public LogRecordXmlTag(final String name, final String timeAttributeName,
                           final String messageAttributeName) {
        this.name = name;
        this.timeAttributeName = timeAttributeName;
        this.messageAttributeName = messageAttributeName;
        this.timeAttributeValue = "";
        this.messageAttributeValue = "";
    }

    public String getName() {
        return name;
    }

    public String getTimeAttributeName() {
        return timeAttributeName;
    }

    public String getMessageAttributeName() {
        return messageAttributeName;
    }

    public String getTimeAttributeValue() {
        return timeAttributeValue;
    }

    public String getMessageAttributeValue() {
        return messageAttributeValue;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setTimeAttributeName(final String timeAttributeName) {
        this.timeAttributeName = timeAttributeName;
    }

    public void setMessageAttributeName(final String messageAttributeName) {
        this.messageAttributeName = messageAttributeName;
    }

    public void setTimeAttributeValue(final String timeAttributeValue) {
        this.timeAttributeValue = timeAttributeValue;
    }

    public void setMessageAttributeValue(final String messageAttributeValue) {
        this.messageAttributeValue = messageAttributeValue;
    }

    public String toString() {
        return "  <" + this.name + " " + this.timeAttributeName + "=\""
                + "[" + this.timeAttributeValue + "]"
                + "\" " + this.messageAttributeName
                + "=\"" + this.messageAttributeValue + "\" />";

    }
}
