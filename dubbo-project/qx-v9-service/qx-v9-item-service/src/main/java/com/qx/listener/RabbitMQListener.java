package com.qx.listener;
import com.qingxiu.constants.RabbitMQConstants;
import com.qx.api.item.IItemService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: QX_He
 * DATA: 2020/8/6-20:25
 * Description:
 **/

@Component
public class RabbitMQListener {

    @Autowired
    private IItemService IItemService;

    @RabbitListener(queues = RabbitMQConstants.ITEMQUEUE)
    public void processItemQueue(Long productId) { // 这里事什么类型由发送者决定,如果事对象，必须要序列化
        IItemService.generateHtmlById(productId);
    }
}
