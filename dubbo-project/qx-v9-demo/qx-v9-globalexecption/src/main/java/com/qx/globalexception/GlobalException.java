package com.qx.globalexception;

import com.qx.typeexception.LoginException;
import com.qx.util.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: QX_He
 * DATA: 2020/8/29-12:42
 * Description:
 **/
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(LoginException.class)
    public AjaxResult loginException(LoginException e, HttpServletRequest request) {
        return AjaxResult.error(e.getMessage());
    }

}
