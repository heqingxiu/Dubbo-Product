package com.qx.util;

import java.util.HashMap;

/**
 * Author: QX_He
 * DATA: 2020/8/29-12:42
 * Description:
 **/

public class AjaxResult extends HashMap<String, Object> {

    public static AjaxResult error(String msg) {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", 500);
        return json;
    }


    public static AjaxResult success(String msg) {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", 200);
        return json;
    }
}
