package org.brapi_igpas.brapi.metadata;

public class Status {
    private String message;
    private String messageType;

    public Status() {
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
