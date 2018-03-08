package com.nuanyou.rekognition.common;

public class APIException extends RuntimeException {

    private int code;

    public APIException(ResultCodes code) {
        super(code.message);
        this.code = code.code;
    }

    public APIException(ResultCodes code, Object message) {
        super(code.message + String.valueOf(message));
        this.code = code.code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
