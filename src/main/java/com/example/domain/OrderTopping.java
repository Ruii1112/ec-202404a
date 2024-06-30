package com.example.domain;

/**
 * 注文トッピングのドメイン.
 *
 * @author rui.inoue
 */
public class OrderTopping {
    /** id */
    private Integer id;
    /** トッピングid */
    private Integer toppingId;
    /** 注文商品id */
    private Integer orderItemId;
    /** トッピング */
    private Topping topping;

    @Override
    public String toString() {
        return "OrderTopping{" +
                "id=" + id +
                ", toppingId=" + toppingId +
                ", orderItemId=" + orderItemId +
                ", topping=" + topping +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToppingId() {
        return toppingId;
    }

    public void setToppingId(Integer toppingId) {
        this.toppingId = toppingId;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }
}
