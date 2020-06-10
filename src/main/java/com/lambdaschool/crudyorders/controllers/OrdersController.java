package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Orders;
import com.lambdaschool.crudyorders.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController
{
    @Autowired
    private OrdersService ordersService;

    // http://localhost:2020/orders/order/7
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> listOrdersById(@PathVariable long id)
    {
        Orders o = ordersService.findOrdersById(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
