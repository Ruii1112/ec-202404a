package com.example.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 注文のドメイン.
 *
 * @author rui.inoue
 */
public class Order {
    /** 消費税率 */
    private static final double TAX = 0.1;

    /** id */
    private Integer id;
    /** ユーザid */
    private Integer userId;
    /** 状態 */
    private Integer status;
    /** 合計金額 */
    private Integer totalPrice;
    /** 宛先氏名 */
    private String destinationName;
    /** 宛先メールアドレス */
    private String destinationEmail;
    /** 宛先郵便番号 */
    private String destinationZipcode;
    /** 宛先都道府県 */
    private String destinationPrefecture;
    /** 宛先市区町村 */
    private String destinationMunicipalities;
    /** 宛先住所 */
    private String destinationAddress;
    /** 宛先電話番号 */
    private String destinationTel;
    /** 注文日時 */
    private LocalDateTime orderTime;
    /** 配達日時 */
    private LocalDateTime deliveryTime;
    /** 支払い方法 */
    private Integer paymentMethod;
    /** 注文商品リスト */
    private List<OrderItem> orderItemList;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", destinationName='" + destinationName + '\'' +
                ", destinationEmail='" + destinationEmail + '\'' +
                ", destinationZipcode='" + destinationZipcode + '\'' +
                ", destinationPrefecture='" + destinationPrefecture + '\'' +
                ", destinationMunicipalities='" + destinationMunicipalities + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", destinationTel='" + destinationTel + '\'' +
                ", orderTime=" + orderTime +
                ", deliveryTime=" + deliveryTime +
                ", paymentMethod=" + paymentMethod +
                ", orderItemList=" + orderItemList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationEmail() {
        return destinationEmail;
    }

    public void setDestinationEmail(String destinationEmail) {
        this.destinationEmail = destinationEmail;
    }

    public String getDestinationZipcode() {
        return destinationZipcode;
    }

    public void setDestinationZipcode(String destinationZipcode) {
        this.destinationZipcode = destinationZipcode;
    }

    public String getDestinationPrefecture() {
        return destinationPrefecture;
    }

    public void setDestinationPrefecture(String destinationPrefecture) {
        this.destinationPrefecture = destinationPrefecture;
    }

    public String getDestinationMunicipalities() {
        return destinationMunicipalities;
    }

    public void setDestinationMunicipalities(String destinationMunicipalities) {
        this.destinationMunicipalities = destinationMunicipalities;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDestinationTel() {
        return destinationTel;
    }

    public void setDestinationTel(String destinationTel) {
        this.destinationTel = destinationTel;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    /**
     * 消費税額を計算して返します.
     *
     * @return 消費税額
     */
    public int getTax() {
        return (int) (getCalcTotalPrice() * TAX);
    }

    /**
     * カート内の商品を参照して合計金額を返します.
     *
     * @return 合計金額(税抜き)
     */
    public int getCalcTotalPrice(){
        int total = 0;
        for(OrderItem orderItem : this.orderItemList){
            total += orderItem.getCalcSubTotalPrice();
        }
        return total;
    }
}
