package com.nuanyou.rekognition.common;

public enum ResultCodes {

    Success(0, "成功"),

    NotFound(404, "找不到页面"),

    UnknownError(500, "未知错误");

    public final Integer code;

    public final String message;

    ResultCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultCodes toEnum(Integer value) {
        if (value == null) {
            return null;
        }
        ResultCodes[] values = ResultCodes.values();
        for (ResultCodes type : values)
            if (type.code.equals(value)) {
                return type;
            }
        throw new IllegalArgumentException("Cannot create evalue from value: " + value + "!");
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}