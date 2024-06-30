package com.example.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 支払い方法のenum.
 *
 * @author rui.inoue
 */
public enum PaymentMethod {
    MONEY(1, "代金引換"),
    CREDIT(2, "クレジットカード"),
    ;

    private final int key;
    private final String value;

    PaymentMethod(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static PaymentMethod of(Integer key){
        for (PaymentMethod paymentMethod: PaymentMethod.values()){
            if(paymentMethod.key == key){
                return paymentMethod;
            }
        }
        throw new IndexOutOfBoundsException("The value of PaymentMethod does not exist");
    }

    public static Map<Integer, String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (PaymentMethod paymentMethod: PaymentMethod.values()){
            map.put(paymentMethod.key, paymentMethod.value);
        }
        return map;
    }
}
