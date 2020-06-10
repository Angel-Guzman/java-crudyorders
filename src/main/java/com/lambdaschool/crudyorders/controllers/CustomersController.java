package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController
{
    @Autowired
    private CustomersService customersService;

    // http://localhost:2020/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers()
    {
        List<Customers> myCustomers = customersService.findAllCustomers();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }


    // http://localhost:2020/customers/customer/7
    // http://localhost:2020/customers/customer/77
    @GetMapping(value = "/customer/{id}", produces = {"application/json"})
    public ResponseEntity<?> listCustomersById(@PathVariable long id)
    {
        Customers c = customersService.findCustomerById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // http://localhost:2020/customers/namelike/mes
    // http://localhost:2020/customers/namelike/cin
    @GetMapping(value = "/namelike/{thename}", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomersLikeName(@PathVariable String thename)
    {
        List<Customers> myCustomers = customersService.findByNameLike(thename);
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

}
