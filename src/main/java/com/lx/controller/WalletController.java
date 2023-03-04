package com.lx.controller;

import com.lx.pojo.Order;
import com.lx.pojo.Wallet;
import com.lx.service.OrderService;
import com.lx.service.WalletService;
import com.lx.utils.CommonUtils;
import com.lx.utils.Status;
import org.joda.time.DateTime;

import java.util.List;

public class WalletController {

    public static void main(String[] args) {
        printOrder();
    }

    // 查询用户钱包金额变动明细的接口
    private static void printOrder() {
        int uid = 7102;
        List<Order> orderList = new WalletService().getDetails(uid);
        orderList.stream().forEach(order -> {
            String createTime = new DateTime(order.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss");
            String patTime = new DateTime(order.getPayTime()).toString("yyyy-MM-dd HH:mm:ss");
            if (order.getRefund() == 0) {
                System.out.println("用户:" + uid + "\t"
                        + "订单类型:消费" + "\t"
                        + "消费金额:" + order.getPrice() + "元" + "\t"
                        + "创建订单时间:" + createTime + "\t"
                        + "完成时间:" + patTime);
            } else {
                System.out.println("用户:" + uid + "\t"
                        + "订单类型:退款" + "\t"
                        + "退款金额:" + order.getPrice() + "元" + "\t"
                        + "创建订单时间:" + createTime + "\t"
                        + "完成时间:" + patTime);
            }
        });
    }

    // 查询账户中的余额
    private static void getById() {
        Wallet walletById = new WalletService().getWalletById(1);
        System.out.println("钱包中的余额为 " + walletById.getBalance() + " 元");
    }

    // 用户消费100元的接口
    private static void pay100() {
        // 1.用户提交订单，得到用户的订单
        Order order = submitOrder(Status.FAILED.getCode(), 100);
        // 2.通过订单在钱包中扣款
        pay(order);
        // 3.将用户的订单修改到已支付状态，并填入支付时间
        updateFlag(order);
    }

    // 用户退款20元接口
    private static void refund20() {
        Order order = submitOrder(Status.FAILED.getCode(), 20);
        refund(order);
        updateFlag(order);
    }

    private static void refund(Order order) {
        if (order.getFlag() == 0) {
            throw new RuntimeException("该订单已退款成功，无需重复处理");
        }
        WalletService walletService = new WalletService();
        Wallet wallet = walletService.getWalletById(order.getWid());
        int newBalance = wallet.getBalance() + order.getPrice();
        wallet.setBalance(newBalance);
        walletService.pay(wallet);
        System.out.println("执行退款操作，退款金额 " + order.getPrice() + " 元，钱包余额 " + wallet.getBalance() + "元。");
    }

    private static void pay(Order order) {
        if (order.getFlag() == 0) {
            throw new RuntimeException("该订单为已支付状态，无需重复支付");
        }
        WalletService walletService = new WalletService();
        Wallet wallet = walletService.getWalletById(order.getWid());
        int newBalance = wallet.getBalance() - order.getPrice();
        if (newBalance < 0) {
            throw new RuntimeException("钱包中的余额不足，无法支付");
        }
        wallet.setBalance(newBalance);
        walletService.pay(wallet);
        System.out.println("执行支付操作，支付金额 " + order.getPrice() + " 元，钱包余额 " + wallet.getBalance() + "元。");
    }

    private static void updateFlag(Order order) {
        order.setFlag(Status.SUCCESS.getCode());
        order.setPayTime(System.currentTimeMillis());
        new OrderService().updateFlag(order);
    }

    private static Order submitOrder(int isRefund, int price) {
        Order order = new Order();
        String orderId = String.valueOf(CommonUtils.random5Num()) + 1234;
        order.setId(orderId);
        order.setWid(1);
        order.setPrice(price);
        order.setCreateTime(System.currentTimeMillis());
        // 订单状态创建时默认为failed状态
        order.setFlag(Status.FAILED.getCode());
        order.setPayTime(0L);
        order.setRefund(isRefund);
        new OrderService().submitOrder(order);
        return order;
    }

    private static void getOrderList() {
        List<Order> orderList = new OrderService().getOrderList();
        orderList.stream().forEach(order -> System.out.println(order.toString()));
    }

    private static void getWalletList() {
        List<Wallet> walletList = new WalletService().getWalletList();
        walletList.stream().forEach(wallet -> System.out.println(wallet.toString()));
    }
}
