package com.lx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private int wid;
    private int price;
    private Long createTime;
    private Long payTime;
    // 0 支付订单， 1 退款订单
    private int refund;
    // 0 成功，1 失败
    private int flag;
}
