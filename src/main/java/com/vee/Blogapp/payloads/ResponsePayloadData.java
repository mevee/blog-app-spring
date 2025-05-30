package com.vee.Blogapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResponsePayloadData<T> extends ResponsePayload{
    private T data;

    public ResponsePayloadData() {
    }

    public ResponsePayloadData(String message, int code, T data) {
        super(message, code);
        this.data = data;
    }

    static public ResponsePayloadData success() {
        return new ResponsePayloadData<>("Success", 200,null);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
