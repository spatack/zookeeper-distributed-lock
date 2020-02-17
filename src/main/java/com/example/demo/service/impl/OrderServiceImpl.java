package com.example.demo.service.impl;

import com.example.demo.domain.Order;
import com.example.demo.service.Lock;
import com.example.demo.service.OrderService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Lock lock;

    private int count = 0;

    @Override
    public Order order() {
        lock.getLock();
        String orderNo = DateFormatUtils.format(new Date(), "yyyyMMddhhmmssSSS") + count;
        lock.unLock();
        System.out.println(orderNo);
        Order order = new Order();
        order.setOrderNo(orderNo);
        count++;
        return order;
    }
}
