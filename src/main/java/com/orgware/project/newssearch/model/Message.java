package com.orgware.project.newssearch.model;

/**
 * Model to return status and description of the status
 * of the server
 * standard options available - Started | Stopped
 */
public class Message {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
