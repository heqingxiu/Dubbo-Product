package com.qx.controller;

import com.qx.typeexception.LoginException;
import com.qx.util.AjaxResult;
import javafx.fxml.LoadException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: QX_He
 * DATA: 2020/8/29-12:53
 * Description:
 **/
@RestController
public class ExceptionController {

    @RequestMapping("/login")
    public String toLogin() {
        boolean judge = true;
        if (judge) {
            throw new LoginException("登录失败，请重新登录");
        }
        return "登录成功";
    }

}
