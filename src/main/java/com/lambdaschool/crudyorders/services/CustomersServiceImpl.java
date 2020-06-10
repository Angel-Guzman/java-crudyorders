package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customersService")
public class CustomersServiceImpl implements CustomersService
{
    @Autowired
    private CustomersRepository custrepos;

    @Override
    public List<Customers> findAllCustomers()
    {
        List<Customers> rtnList = new ArrayList<>();

        custrepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Customers findCustomerById(long id)
    {
        return custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found "));
    }

    @Override
    public List<Customers> findByNameLike(String thename)
    {
        return custrepos.findByCustnameContainingIgnoringCase(thename);
    }
}
