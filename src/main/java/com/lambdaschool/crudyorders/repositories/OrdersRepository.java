package com.lambdaschool.crudyorders.repositories;

import com.lambdaschool.crudyorders.models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long>
{
}
