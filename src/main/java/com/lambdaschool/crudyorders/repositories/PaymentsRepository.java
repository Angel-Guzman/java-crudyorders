package com.lambdaschool.crudyorders.repositories;

import com.lambdaschool.crudyorders.models.Payments;
import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepository extends CrudRepository<Payments, Long>
{
}
