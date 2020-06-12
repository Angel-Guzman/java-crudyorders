package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.models.Orders;
import com.lambdaschool.crudyorders.models.Payments;
import com.lambdaschool.crudyorders.repositories.CustomersRepository;
import org.aspectj.weaver.ast.Or;
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

    // DELETE
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

    // POST/PUT
    @Transactional
    @Override
    public Customers save(Customers customers)
    {
        Customers newCustomer = new Customers();
        if (customers.getCustcode() != 0)
        {
            custrepos.findById(customers.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customers.getCustcode() + " Not Found "));
            newCustomer.setCustcode(customers.getCustcode());
        }

        newCustomer.setCustname(customers.getCustname());
        newCustomer.setCustcity(customers.getCustcity());
        newCustomer.setWorkingarea(customers.getWorkingarea());
        newCustomer.setCustcountry(customers.getCustcountry());
        newCustomer.setGrade(customers.getGrade());
        newCustomer.setOpeningamt(customers.getOpeningamt());
        newCustomer.setReceiveamt(customers.getReceiveamt());
        newCustomer.setPaymentamt(customers.getPaymentamt());
        newCustomer.setOutstandingamt(customers.getOutstandingamt());
        newCustomer.setPhone(customers.getPhone());
        newCustomer.setAgent(customers.getAgent());

        newCustomer.getOrders().clear();
        for (Orders o : customers.getOrders())
        {
            Orders newOrder = new Orders(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrderdescription());
            for (Payments p : o.getPayments())
            {
                newOrder.addPayments(p);
            }
            newCustomer.getOrders().add(newOrder);
        }

        return custrepos.save(newCustomer);
    }

    // PATCH
    @Transactional
    @Override
    public Customers update(Customers customers, long id)
    {
        Customers currentCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("Customer " + id + " Not Found")));

        if (customers.getCustname() != null)
        {
            currentCustomer.setCustname(customers.getCustname());
        }

        if (customers.getCustcity() != null)
        {
            currentCustomer.setCustcity(customers.getCustcity());
        }

        if (customers.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customers.getWorkingarea());
        }

        if (customers.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customers.getCustcountry());
        }

        if (customers.getOrders().size() > 0)
        {
            currentCustomer.getOrders().clear();
            for (Orders o : customers.getOrders())
            {
                Orders newOrder = new Orders(o.getOrdamount(), o.getAdvanceamount(), currentCustomer, o.getOrderdescription());
                for (Payments p : o.getPayments())
                {
                    newOrder.addPayments(p);
                }
                currentCustomer.getOrders().add(newOrder);
            }
        }
        return custrepos.save(currentCustomer);
    }
}
