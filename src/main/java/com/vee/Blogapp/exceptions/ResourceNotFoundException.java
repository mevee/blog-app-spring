package com.vee.Blogapp.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResourceNotFoundException extends  RuntimeException {

    String message;
    String exception;
    Object type;

    public ResourceNotFoundException(String message, String exception, Object type) {
        this.message = message;
        this.exception = exception;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ResourceNotFoundException{" +
                "message='" + message + '\'' +
                ", exception='" + exception + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
