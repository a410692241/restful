package com.wei.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {
    private String placeOrder;
    private String completeOreder;
    private Logger logger = LoggerFactory.getLogger(getClass());;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(() -> {
            logger.info("接到下单请求" + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOreder = placeOrder;
            logger.info("下单处理完毕");
        }).start();
    }

    public String getCompleteOreder() {
        return completeOreder;
    }

    public void setCompleteOreder(String completeOreder) {
        this.completeOreder = completeOreder;
    }
}
