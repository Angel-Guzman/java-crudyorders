package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Orders;

public interface OrdersService
{
    Orders findOrdersById(long id);
}
