package com.lambdaschool.crudyorders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;

    private double ordamount;
    private double advanceamount;

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    @JsonIgnoreProperties(value = "orders")
    private Customers customers;

    private String orderdescription;

    @ManyToMany()
    @JoinTable(name = "orderspayments",
            joinColumns = @JoinColumn(name = "ordnum"),
            inverseJoinColumns = @JoinColumn(name = "paymentid"))
    @JsonIgnoreProperties(value = "orders")
    private List<Payments> payments = new ArrayList<>();

    public Orders()
    {
    }

    public Orders(double ordamount, double advanceamount, Customers customers, String orderdescription)
    {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.customers = customers;
        this.orderdescription = orderdescription;
    }

    public long getOrdnum()
    {
        return ordnum;
    }

    public void setOrdnum(long ordnum)
    {
        this.ordnum = ordnum;
    }

    public double getOrdamount()
    {
        return ordamount;
    }

    public void setOrdamount(double ordamount)
    {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount()
    {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount)
    {
        this.advanceamount = advanceamount;
    }

    public Customers getCustomers()
    {
        return customers;
    }

    public void setCustomers(Customers customers)
    {
        this.customers = customers;
    }

    public String getOrderdescription()
    {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription)
    {
        this.orderdescription = orderdescription;
    }

    public List<Payments> getPayments()
    {
        return payments;
    }

    public void setPayments(List<Payments> payments)
    {
        this.payments = payments;
    }

    @Override
    public String toString()
    {
        return "Orders{" +
                "ordnum=" + ordnum +
                ", ordamount=" + ordamount +
                ", advanceamount=" + advanceamount +
                ", customers=" + customers +
                ", orderdescription='" + orderdescription + '\'' +
                ", payments=" + payments +
                '}';
    }
}
