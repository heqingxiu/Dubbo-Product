package com.qingxiu.utills.webresult;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Author: QX_He
 * DATA: 2020/8/1-22:09
 * Description: Use to making a multi upload file return result whose format is defined by WangEditor architecture.
 **/
public class MultiUploadFileResult implements Serializable {
    // It is represent no error when it is 0
    private String errno;

    private String[] data;


    public MultiUploadFileResult() {

    }

    /**
     * parameter constructor
     *
     * @param errno
     * @param data
     */
    public MultiUploadFileResult(String errno, String[] data) {
        this.errno = errno;
        this.data = data;
    }


    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MultiUploadFileResult{" +
                "errno='" + errno + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
