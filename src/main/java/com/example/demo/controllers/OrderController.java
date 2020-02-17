package com.example.demo.controllers;

import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;
    /**
     * 下单
     * @return 订单号
     */
    @GetMapping("/order")
    public Order doOrder() {
        return orderService.order();
    };
}
