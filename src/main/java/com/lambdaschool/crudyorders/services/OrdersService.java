package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.models.Orders;
import org.hibernate.criterion.Order;

public interface OrdersService
{
    Orders findOrdersById(long id);

    // DELETE
    void delete(long id);

    // POST/PUT
    Orders save(Orders orders);
}
