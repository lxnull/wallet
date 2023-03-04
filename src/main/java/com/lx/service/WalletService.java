package com.lx.service;

import com.lx.dao.WalletDao;
import com.lx.pojo.Order;
import com.lx.pojo.Wallet;
import com.lx.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WalletService {

    private SqlSession sqlSession = new MybatisUtils().getSqlSession();

    public List<Wallet> getWalletList() {
        return sqlSession.getMapper(WalletDao.class).getWalletList();
    }

    public void pay(Wallet wallet) {
        sqlSession.getMapper(WalletDao.class).pay(wallet);
    }

    public Wallet getWalletById(int id) {
        return sqlSession.getMapper(WalletDao.class).getWalletById(id);
    }

    public List<Order> getDetails(int uid) {
        Wallet wallet = sqlSession.getMapper(WalletDao.class).getWalletByUserId(uid);
        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getOrderListByWid(wallet.getId());
        return orderList;
    }
}
