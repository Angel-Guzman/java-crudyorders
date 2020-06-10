package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.models.Orders;
import com.lambdaschool.crudyorders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
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

    @Transactional
    @Override
    public void delete(long id)
    {
        if (custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Customer " + id + " Not Found ");
        }
    }

    @Transactional
    @Override
    public Customers save(Customers customers)
    {
        Customers newCustomer = new Customers();

        newCustomer.setCustcity(customers.getCustcity());
        newCustomer.setCustname(customers.getCustname());
        newCustomer.setCustcountry(customers.getCustcountry());
        newCustomer.setGrade(customers.getGrade());
        newCustomer.setOpeningamt(customers.getOpeningamt());
        newCustomer.setPaymentamt(customers.getPaymentamt());
        newCustomer.setPhone(customers.getPhone());
        newCustomer.setReceiveamt(customers.getReceiveamt());
        newCustomer.setWorkingarea(customers.getWorkingarea());

        newCustomer.getOrders().clear();
        for (Orders o : customers.getOrders())
        {
            Orders newOrder = new Orders(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrderdescription());
            newCustomer.getOrders().add(newOrder);
        }

        return custrepos.save(newCustomer);
    }

    @Override
    public Customers update(Customers customers, long id)
    {
        return null;
    }
}
