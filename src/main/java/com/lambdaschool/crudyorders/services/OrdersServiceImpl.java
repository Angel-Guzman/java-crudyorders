package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Orders;
import com.lambdaschool.crudyorders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "ordersService")
public class OrdersServiceImpl implements OrdersService
{
    @Autowired
    private OrdersRepository ordersrepos;

    @Override
    public Orders findOrdersById(long id)
    {
        return ordersrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found "));
    }
}
