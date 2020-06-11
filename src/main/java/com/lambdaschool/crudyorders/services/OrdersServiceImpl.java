package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.models.Orders;
import com.lambdaschool.crudyorders.models.Payments;
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

    // POST/PUT
    @Transactional
    @Override
    public Orders save(Orders orders)
    {
        Orders newOrders = new Orders();

        if (orders.getOrdnum() != 0)
        {
            ordersrepos.findById(orders.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + orders.getOrdnum() + " Not Found "));
            newOrders.setOrdnum(orders.getOrdnum());
        }

        newOrders.setOrdamount(orders.getOrdamount());
        newOrders.setAdvanceamount(orders.getAdvanceamount());
        newOrders.setOrderdescription(orders.getOrderdescription());
        newOrders.setCustomers(orders.getCustomers());

        newOrders.getPayments().clear();
        for (Payments p : orders.getPayments())
        {
            newOrders.addPayments(p);
        }

        return ordersrepos.save(newOrders);

    }

    // DELETE
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
