package com.team.two.mitrais_carrot.dto;

public class MessageDto{
    private String message;
    private Boolean status;

    public MessageDto(String message) {
        this.message = message;
    }

    public MessageDto(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
