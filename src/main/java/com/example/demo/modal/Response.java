package com.example.demo.modal;

public class Response {
    private int statusCode;
    private String message;

    public Response(String message,int statusCode){
        this.message = message;
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
