package com.qx.pojo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Author: QX_He
 * DATA: 2020/8/7-10:27
 * Description:
 **/
public class NoteResponseResult implements Serializable {

    private String respCode;
    private String rspDesc;
    private String smsId;
    private String[] failList;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRspDesc() {
        return rspDesc;
    }

    public void setRspDesc(String rspDesc) {
        this.rspDesc = rspDesc;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String[] getFailList() {
        return failList;
    }

    public void setFailList(String[] failList) {
        this.failList = failList;
    }


    @Override
    public String toString() {
        return "NoteResponseResult{" +
                "respCode='" + respCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", smsId='" + smsId + '\'' +
                ", failList=" + Arrays.toString(failList) +
                '}';
    }
}
