package org.planth_pheno_analytics.brapi.api.response.metadata;

public class Status {
    public static final String MESSAGETYPE_LOG_INFO = "INFO";
    public static final String MESSAGETYPE_LOG_WARNING = "WARNING";
    public static final String MESSAGETYPE_LOG_ERROR = "ERROR";

    private String message;
    private String messageType;

    public Status() {
    }

    public Status(String message, String messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
