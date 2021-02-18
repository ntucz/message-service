package com.smartarch.message.test;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Response {
    private int code;
    private Object obj;
    private String message;
    private String error;

    public Response(int code) {
        this.code = code;
    }
}