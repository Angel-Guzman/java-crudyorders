package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customers;

import java.util.List;

public interface CustomersService
{
    List<Customers> findAllCustomers();

    Customers findCustomerById(long id);

    List<Customers> findByNameLike(String thename);
}
