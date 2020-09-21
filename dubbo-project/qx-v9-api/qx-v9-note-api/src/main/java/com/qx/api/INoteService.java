package com.qx.api;

import com.qx.pojo.NoteResponseResult;

/**
 * Author: QX_He
 * DATA: 2020/8/7-9:42
 * Description:
 **/
public interface INoteService {

    NoteResponseResult sendMessages(String to, String data);

}
