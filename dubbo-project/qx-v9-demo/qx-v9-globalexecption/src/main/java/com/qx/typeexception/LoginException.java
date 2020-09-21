package com.qx.typeexception;

/**
 * Author: QX_He
 * DATA: 2020/8/29-12:43
 * Description:
 **/
public class LoginException extends RuntimeException {

    protected final String message;

    public LoginException(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
