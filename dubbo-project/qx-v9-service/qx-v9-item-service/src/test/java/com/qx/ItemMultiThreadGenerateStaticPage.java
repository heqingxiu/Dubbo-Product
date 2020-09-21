package com.qx;

import com.qx.api.item.IItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: QX_He
 * DATA: 2020/8/6-12:52
 * Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemMultiThreadGenerateStaticPage {

    @Autowired
    private IItemService IItemService;

    @Test
    public void MultiThreadGenerateStaticPageTest() throws InterruptedException {
        List<Long> ids = new ArrayList<>();
        for (long i = 0; i < 8; i++) {
            ids.add(i);
        }
        IItemService.generateStaticPageByMultiThread(ids);
        Thread.sleep(2000); // 程序不能结束太快,然后其他线程都还没有执行完,你就结束了,就导致不成功.
//        itemService.generateHtmlById(2L);
    }
}
