package com.nuanyou.rekognition.model.vo;

import java.util.List;

/**
 * Created by mylon.sun on 2017/12/11.
 */
public class TransVo {

    private String from;

    private String to;

    private List<TransResultVo> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<TransResultVo> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<TransResultVo> trans_result) {
        this.trans_result = trans_result;
    }
}
