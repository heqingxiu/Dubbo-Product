package com.qx.api.item;

import com.qingxiu.utills.webresult.Result;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Author: QX_He
 * DATA: 2020/8/5-19:02
 * Description:
 **/
public interface ItemService {

    /**
     * Generate a static page via freemarker
     *
     * @param id
     * @return
     */
    Result<String> generateHtmlById(Long id);

    /**
     * Multi thread for generate static page
     *
     * @param lists
     * @return
     */
    Result<List<Future>> generateStaticPageByMultiThread(List<Long> lists);
}
