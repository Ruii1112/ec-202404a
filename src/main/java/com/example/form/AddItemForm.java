package com.example.form;

import java.util.List;

/**
 * 商品をカートに追加するフォーム.
 *
 * @author rui.inoue
 */
public class AddItemForm {
    private String size;
    private List<Integer> toppingList;
    private Integer quantity;
    private Integer itemId;

    @Override
    public String toString() {
        return "AddItemForm{" +
                "size='" + size + '\'' +
                ", toppingList=" + toppingList +
                ", quantity=" + quantity +
                ", itemId=" + itemId +
                '}';
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<Integer> getToppingList() {
        return toppingList;
    }

    public void setToppingList(List<Integer> toppingList) {
        this.toppingList = toppingList;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}
