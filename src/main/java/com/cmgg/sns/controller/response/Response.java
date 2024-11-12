package com.cmgg.sns.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private String resultCode;
    private T result;

    public static <T> Response<Void> error(String errorCode) {
        return new Response<>(errorCode, null);
    }

    public static <T> Response<Void> success<T result> {
        return new Response<>("SUCCESS", null);
    }
}
