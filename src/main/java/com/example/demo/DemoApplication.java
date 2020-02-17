package com.example.demo;

import com.example.demo.service.Lock;
import com.example.demo.service.OrderService;
import com.example.demo.service.impl.OrderServiceImpl;
import com.example.demo.service.impl.ZookeeperLockImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
         SpringApplication.run(DemoApplication.class, args);
//        for (int i = 0; i < 100; i++) {
//            new Thread(() -> {
//                OrderService orderService = new OrderServiceImpl();
//                String orderNo = orderService.order();
//                System.out.println(orderNo);
//            }).start();
//        }

    }

}
