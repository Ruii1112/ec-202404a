package com.example.domain;

import java.util.List;

/**
 * 注文商品のドメイン.
 *
 * @author rui.inoue
 */
public class OrderItem {
    /** id */
    private Integer id;
    /** 商品id */
    private Integer itemId;
    /** 注文id */
    private Integer orderId;
    /** 数 */
    private Integer quantity;
    /** サイズ */
    private Character size;
    /** 商品 */
    private Item item;
    /** 注文トッピングリスト */
    private List<OrderTopping> orderToppingList;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                ", size=" + size +
                ", item=" + item +
                ", orderToppingList=" + orderToppingList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Character getSize() {
        return size;
    }

    public void setSize(Character size) {
        this.size = size;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<OrderTopping> getOrderToppingList() {
        return orderToppingList;
    }

    public void setOrderToppingList(List<OrderTopping> orderToppingList) {
        this.orderToppingList = orderToppingList;
    }

    /**
     * トッピングや数量を考慮した注文商品ごとの小計を返します.
     *
     * @return 注文商品の小計(税抜き)
     */
    public int getCalcSubTotalPrice(){
        int itemSubtotal = 0;
        if (this.size.equals('S')){
            itemSubtotal = this.item.getPriceS();
        } else if (this.size.equals('M')) {
            itemSubtotal = this.item.getPriceM();
        } else if (this.size.equals('L')){
            itemSubtotal = this.item.getPriceL();
        }
        for (OrderTopping orderTopping : this.orderToppingList){
            itemSubtotal += orderTopping.getTopping().getPrice();
        }
        return itemSubtotal*this.quantity;
    }
}
