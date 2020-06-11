package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.models.Orders;
import com.lambdaschool.crudyorders.services.OrdersService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrdersController
{
    @Autowired
    private OrdersService ordersService;

    // http://localhost:2020/orders/order/52
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> listOrdersById(@PathVariable long id)
    {
        Orders o = ordersService.findOrdersById(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    // POST
    @PostMapping(value = "/order", consumes = {"application/json"})
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody Orders newOrders)
    {
        newOrders.setOrdnum(0);
        newOrders = ordersService.save(newOrders);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{orderid}")
                .buildAndExpand(newOrders.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(newOrders, responseHeaders, HttpStatus.CREATED);
    }

    // PUT
    @PutMapping(value = "/order/{orderid}", consumes = {"application/json"})
    public ResponseEntity<?> updateOrder(@Valid @RequestBody Orders updateOrders, @PathVariable long orderid)
    {
        updateOrders.setOrdnum(orderid);
        ordersService.save(updateOrders);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/order/{ordernum}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordernum)
    {
        ordersService.delete(ordernum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
