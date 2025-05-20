package com.vee.Blogapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResponsePayload<T> {
    private String message;
    private int code;
    private T data;
3
    public void setData(T data) {
        this.data = data;
    }

    public ResponsePayload(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ResponsePayload(String message, int code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    static public ResponsePayload success() {
        return new ResponsePayload<>("Success", 200);
    }

    static public ResponsePayload error(String message, int code) {
        return new ResponsePayload<>(message, code);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }
}
