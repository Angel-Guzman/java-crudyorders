package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.models.Orders;
import com.lambdaschool.crudyorders.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    // PUT
//    @PutMapping(value = "/customer/{customerid}", consumes = {"application/json"})
//    public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customers updateCustomers, @PathVariable long customerid)
//    {
//        updateCustomers.setCustcode(customerid);
//        customersService.save(updateCustomers);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    
    // DELETE
    @DeleteMapping("/order/{ordernum}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordernum)
    {
        ordersService.delete(ordernum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
