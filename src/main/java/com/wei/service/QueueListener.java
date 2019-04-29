package com.wei.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;
    private Logger logger = LoggerFactory.getLogger(getClass());
    ;

    /**
     * @param event 监听器
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            while (true) {
                if (StringUtils.isNotBlank(mockQueue.getCompleteOreder())) {
                    String orderId = mockQueue.getCompleteOreder();
                    logger.info("订单id是" + orderId);
                    deferredResultHolder.getMap().get(orderId).setResult("DeferredResultHolder used!");
                    mockQueue.setCompleteOreder(null);
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


        }).start();

    }

}
