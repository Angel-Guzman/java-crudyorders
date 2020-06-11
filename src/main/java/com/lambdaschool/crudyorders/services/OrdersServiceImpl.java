package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Orders;
import com.lambdaschool.crudyorders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
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

//    public void delete(long id)
//    {
//        if (custrepos.findById(id).isPresent())
//        {
//            custrepos.deleteById(id);
//        } else
//        {
//            throw new EntityNotFoundException("Customer " + id + " Not Found ");
//        }
//    }
    @Transactional
    @Override
    public void delete(long id)
    {
        if (ordersrepos.findById(id).isPresent())
        {
            ordersrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Order " + id + " Not Found ");
        }
    }
}
