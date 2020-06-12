package com.lambdaschool.crudyorders.repositories;

import com.lambdaschool.crudyorders.models.Customers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customers, Long>
{
    List<Customers> findByCustnameContainingIgnoringCase(String likename);
}
