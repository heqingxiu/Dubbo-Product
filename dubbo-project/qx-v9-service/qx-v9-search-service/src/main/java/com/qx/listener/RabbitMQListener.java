package com.qx.listener;

import com.qingxiu.constants.RabbitMQConstants;
import com.qx.api.search.IProductSearchService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: QX_He
 * DATA: 2020/8/6-21:40
 * Description:
 **/
@Component
public class RabbitMQListener {

    @Autowired
    private IProductSearchService iProductSearchService;

    @RabbitListener(queues = RabbitMQConstants.SEARCHQUEUENAME)
    public void processSearchQueue(Long productId) {
        iProductSearchService.synchronizedSolrDataIncrement(productId);
    }
}
