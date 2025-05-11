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

    static public ResponsePayload error(String message,int code) {
        return new ResponsePayload<>(message, code);
    }
}
