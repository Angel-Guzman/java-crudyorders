package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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


    // http://localhost:2020/customers/customer/23
    // http://localhost:2020/customers/customer/77
    @GetMapping(value = "/customer/{id}", produces = {"application/json"})
    public ResponseEntity<?> listCustomersById(@PathVariable long id)
    {
        Customers c = customersService.findCustomerById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // http://localhost:2020/customers/namelike/mes
    // http://localhost:2020/customers/namelike/zin
    @GetMapping(value = "/namelike/{thename}", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomersLikeName(@PathVariable String thename)
    {
        List<Customers> myCustomers = customersService.findByNameLike(thename);
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    // POST
    @PostMapping(value = "/customer", consumes = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customers newCustomer)
    {
        newCustomer.setCustcode(0);
        newCustomer = customersService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custcode}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    // PUT

    // PATCH

    // DELETE http://localhost:2020/customers/customer/54
    // deleted 19
    @DeleteMapping("/customer/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode)
    {
        customersService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
