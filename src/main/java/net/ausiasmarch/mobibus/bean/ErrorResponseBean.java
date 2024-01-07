package net.ausiasmarch.mobibus.bean;

public class ErrorResponseBean {
    private String status;
    private String message;
    private String details;

    public ErrorResponseBean(String status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
