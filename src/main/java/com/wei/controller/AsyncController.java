package com.wei.controller;

import com.wei.service.DeferredResultHolder;
import com.wei.service.MockQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;

@RestController
@RequestMapping("async")
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**runnable处理异步请求
     * @return 检验多线程的返回顺序,主->副->主->副
     */
    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        logger.info("主线程开始");

        //使用deferredResult模拟创建订单
        UUID uuid = UUID.randomUUID();
        String OrderId = uuid.toString();
        mockQueue.setPlaceOrder(OrderId);
        DeferredResult<String> objectDeferredResult = new DeferredResult<>();
        deferredResultHolder.getMap().put(OrderId, objectDeferredResult);

        //使用callable方式实验进程的启动顺序
//        Callable<String> callable = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                logger.info("副线程启动");
//                Thread.sleep(1000);
//                logger.info("副线程返回");
//                return "success";
//            }
//        };

        //使用logger.info可以看到进程号-
        logger.info("主线程返回");
        return deferredResultHolder.getMap().get(OrderId);
    }




}
