package com.lx.service;

import com.lx.dao.OrderDao;
import com.lx.dao.WalletDao;
import com.lx.pojo.Order;
import com.lx.pojo.Wallet;
import com.lx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OrderService {
    private SqlSession sqlSession = new MybatisUtils().getSqlSession();

    public List<Order> getOrderList() {
        return sqlSession.getMapper(OrderDao.class).getOrderList();
    }

    public void submitOrder(Order order) {
        sqlSession.getMapper(OrderDao.class).submitOrder(order);
    }

    public void updateFlag(Order order) {
        sqlSession.getMapper(OrderDao.class).updateFlag(order);
    }

    public List<Order> getOrderListByWid(int wid) {
        return sqlSession.getMapper(OrderDao.class).getOrderListByWid(wid);
    }
}
