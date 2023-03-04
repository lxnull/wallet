package com.lx.dao;

import com.lx.pojo.Order;

import java.util.List;

public interface OrderDao {
    public List<Order> getOrderList();

    public void submitOrder(Order order);

    public void updateFlag(Order order);

    public List<Order> getOrderListByWid(int wid);
}
