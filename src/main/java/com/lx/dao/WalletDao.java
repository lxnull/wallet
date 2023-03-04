package com.lx.dao;

import com.lx.pojo.Order;
import com.lx.pojo.Wallet;

import java.util.List;

public interface WalletDao {
    public List<Wallet> getWalletList();

    public void pay(Wallet wallet);

    public Wallet getWalletById(int id);

    public Wallet getWalletByUserId(int uid);
}
