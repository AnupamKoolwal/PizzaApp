package com.example.UserPizzaService.Domain;

import java.util.Date;
import java.util.List;

public class Order {

    private int orderId;
    private Date orderDate;
    private List<Pizza> orderPizzaList;
    private double orderTotalPrice;

    public Order() {
    }

    public Order(int orderId, Date orderDate, List<Pizza> orderPizzaList, double orderTotalPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderPizzaList = orderPizzaList;
        this.orderTotalPrice = orderTotalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Pizza> getOrderPizzaList() {
        return orderPizzaList;
    }

    public void setOrderPizzaList(List<Pizza> orderPizzaList) {
        this.orderPizzaList = orderPizzaList;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderPizzaList=" + orderPizzaList +
                ", orderTotalPrice=" + orderTotalPrice +
                '}';
    }
}
