package com.mlt.api.common.handler.error.exception;

import com.mlt.api.common.handler.error.enums.NotFoundCodeEnum;
import lombok.Getter;

@Getter
public class MltNotFoundException extends RuntimeException {

    private final String message;
    private final String code;
    private final NotFoundCodeEnum type;

    public MltNotFoundException(String message, String code, NotFoundCodeEnum type) {
        this.message = message;
        this.code = code;
        this.type = type;
    }

}
