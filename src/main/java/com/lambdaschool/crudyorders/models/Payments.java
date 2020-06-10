package com.lambdaschool.crudyorders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payments")
public class Payments
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long paymentid;

    @Column(nullable = false)
    private String type;

//    @ManyToMany(mappedBy = "payments")
//    @JsonIgnoreProperties(value = "payments")
//    private List<Orders> ordersList = new ArrayList<>();

    public Payments()
    {
    }

    public Payments(String type)
    {
        this.type = type;
    }

    public long getPaymentid()
    {
        return paymentid;
    }

    public void setPaymentid(long paymentid)
    {
        this.paymentid = paymentid;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

//    public List<Orders> getOrdersList()
//    {
//        return ordersList;
//    }

//    public void setOrdersList(List<Orders> ordersList)
//    {
//        this.ordersList = ordersList;
//    }

    @Override
    public String toString()
    {
        return "Payments{" +
                "paymentid=" + paymentid +
                ", type='" + type + '\'' +
//                ", ordersList=" + ordersList +
                '}';
    }
}
