package com.mlt.api.common.handler.error.enums;

public enum NotFoundCodeEnum {

    PRODUCT(1001, "PRO"),
    CATEGORY(1002, "CAT");

    private int codeNumber;
    private String codeType;

    NotFoundCodeEnum(int codeNumber, String codeType) {
        this.codeNumber = codeNumber;
        this.codeType = codeType;
    }

    public static String getErrorCode(NotFoundCodeEnum type) {
        return type.codeType +
                "-" +
                type.codeNumber;
    }
}
