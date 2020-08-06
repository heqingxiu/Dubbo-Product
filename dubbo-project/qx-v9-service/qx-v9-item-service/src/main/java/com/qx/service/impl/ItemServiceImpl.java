package com.qx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingxiu.utills.webresult.Result;
import com.qingxiu.utills.webresult.StatusCode;
import com.qx.api.item.ItemService;
import com.qx.v9.entity.TProduct;
import com.qx.v9.mapper.TProductMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Author: QX_He
 * DATA: 2020/8/5-19:03
 * Description:
 **/
@Component
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TProductMapper tProductMapper;

    @Autowired
    private Configuration configuration;

    //    @Value("location.static.html")
    @Value("${location.static.html}")
    private String StaticHtmlLocation;

    /**
     * Create self defined thread pool
     */
    //Gets the number of computer core processor
    private int corePoolSize = Runtime.getRuntime().availableProcessors();
    // Self defined thread pool
    private ExecutorService selfDefinedPool = new ThreadPoolExecutor(
            corePoolSize, corePoolSize * 2, 0L,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));

    /**
     * Multi thread for generate static page
     *
     * @param lists
     * @return
     */
    public Result<List<Future>> generateStaticPageByMultiThread(List<Long> lists) {
        List<Future> FutureList = new ArrayList<>(lists.size());
        for (Long id : lists) {
            FutureList.add(selfDefinedPool.submit(new CreateHtmlTask(id)));
        }
        return new Result<List<Future>>(true, StatusCode.OK, "MultiThreadGenerateStaticPageSuccessfully", FutureList);
    }

    /**
     * Callable implement class for multi thread task
     */
    private class CreateHtmlTask implements Callable<Boolean> {  //一般不用Runnable方法,因为没有返回值
        private Long id;

        public CreateHtmlTask(Long id) {
            this.id = id;
        }

        @Override
        public Boolean call() throws Exception {
            //Generate a static page
            TProduct product = tProductMapper.selectByPrimaryKey(id); // 这个id 怎么给我呢? 用构造器
            try {
                //2.equation or formula: Output = template + data
                Template template = configuration.getTemplate("item.ftl");
                Map<String, Object> data = new HashMap<>();
                data.put("product", product);
                FileWriter fileWriter = new FileWriter(
                        StaticHtmlLocation + id + "commodity.html");
                template.process(data, fileWriter);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }


    /**
     * Generate a static page via freemarker
     *
     * @param id
     * @return
     */
    @Override
    public Result<String> generateHtmlById(Long id) {
//        1. Get Product messages from mysql database by id
        TProduct product = tProductMapper.selectByPrimaryKey(id);
        try {
            //2.equation or formula: Output = template + data
            Template template = configuration.getTemplate("item.ftl");
            Map<String, Object> data = new HashMap<>();
            data.put("product", product);
            FileWriter fileWriter = new FileWriter(
                    StaticHtmlLocation + id + "WhatCommodity.html");
            template.process(data, fileWriter);
        } catch (Exception e) {
            return new Result<String>(true, StatusCode.OK, "Failed to generate a static page", "fail");
        }
        return new Result<String>(true, StatusCode.OK, "Generate static page successfully", "success");
    }
}
