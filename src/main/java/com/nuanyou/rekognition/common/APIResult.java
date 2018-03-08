package com.nuanyou.rekognition.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by Alan.ye on 2016/9/7.
 */
public class APIResult<T> {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("data")
    private T data;

    public APIResult() {
        this.code = ResultCodes.Success.code;
        this.msg = ResultCodes.Success.message;
    }

    public APIResult(Exception e) {
        this.code = ResultCodes.UnknownError.code;
        this.msg = ResultCodes.UnknownError.message + ExceptionUtils.getStackTrace(e);
    }

    public APIResult(Exception e,String msg) {
        this.code = ResultCodes.UnknownError.code;
        this.msg =msg;
    }

    public APIResult(APIException e) {
        this.code = e.getCode();
        this.msg = e.getMessage();
    }

    public APIResult(ResultCodes code) {
        this.code = code.code;
        this.msg = code.getMessage();
    }

    public APIResult(ResultCodes code, String message) {
        this.code = code.code;
        this.msg = code.message + message;
    }

    public APIResult(T t) {
        this(ResultCodes.Success, t);
    }

    public APIResult(ResultCodes code, T data) {
        this(code);
        this.data = data;
    }

    public boolean isSuccess() {
        return ResultCodes.Success.code.equals(code);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}