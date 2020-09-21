package com.qx.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: QX_He
 * DATA: 2020/8/5-19:05
 * Description:
 **/
@Controller
@RequestMapping("/item")
public class ItemController {

    /**
     * temporary in here for test
     * @return
     */
    @RequestMapping("/list")
    public String browseWebPage() {
        return "commodity";
    }


}
