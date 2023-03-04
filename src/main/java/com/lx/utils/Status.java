package com.lx.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jarno
 * 表示删除的枚举
 */
public enum Status {
    /**
     * look name 未支付
     */
    SUCCESS(0,"成功/支付订单"),
    FAILED(1,"失败/退款订单");

    private static final Map<Integer, Status> CODE_MAP = new HashMap<>(3);

    static{
        for(Status status: values()){
            CODE_MAP.put(status.getCode(), status);
        }
    }

    /**
     * 根据code获取枚举值
     * @param code
     * @return
     */
    public static Status valueOfCode(int code){
        return CODE_MAP.get(code);
    }

    private int code;
    private String msg;

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}