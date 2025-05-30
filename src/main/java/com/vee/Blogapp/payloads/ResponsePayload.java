package com.vee.Blogapp.payloads;


import lombok.Getter;

@Getter
public class ResponsePayload{
    private String message;
    private int code;


    public ResponsePayload() {
     }

    public ResponsePayload(String message, int code) {
        this.message = message;
        this.code = code;

    }

    static public ResponsePayload success() {
        return new ResponsePayload("Success", 200);
    }

    static public ResponsePayload error(String message, int code) {
        return new ResponsePayload(message, code);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
