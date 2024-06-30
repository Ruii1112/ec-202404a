package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 注文確認情報の入力フォーム.
 *
 * @author rui.inoue
 */
public class OrderForm {
    /** 注文のid */
    private Integer orderId;
    /** 名前 */
    @NotBlank(message = "名前が未入力です")
    private String destinationName;
    /** メールアドレス */
    @NotBlank(message = "メールアドレスが未入力です")
    @Email(message = "メールアドレスの形式が不正です")
    private String destinationEmail;
    /** 郵便番号 */
    @Pattern(regexp="^[0-9]{3}-[0-9]{4}$", message = "郵便番号の形式が正しくありません")
    @NotBlank(message = "郵便番号が未入力です")
    private String destinationZipcode;
    /** 都道府県 */
    @NotBlank(message = "都道府県が未入力です")
    private String destinationPrefecture;
    /** 市区町村 */
    @NotBlank(message = "市区町村が未入力です")
    private String destinationMunicipalities;
    /** 住所 */
    @NotBlank(message = "住所が未入力です")
    private String destinationAddress;
    /** 電話番号 */
    @Pattern(regexp="^[0-9]{3,4}-[0-9]{2,4}-[0-9]{4}$", message = "電話番号の形式が正しくありません")
    @NotBlank(message = "電話番号が未入力です")
    private String destinationTel;
    /** 配達日 */
    @NotBlank(message = "配達日が未入力です")
    private String deliveryDate;
    /** 配達時間 */
    @NotBlank(message = "配達時間が未入力です")
    private String deliveryTime;
    /** 支払い方法 */
    private Integer paymentMethod;
    /** カード番号 */
    private String cardNumber;
    /** カード有効期限（年） */
    private String cardExpYear;
    /** カード有効期限（月） */
    private String cardExpMonth;
    /** カード名義人 */
    private String cardName;
    /** セキュリティコード */
    private String cardCvv;

    @Override
    public String toString() {
        return "OrderForm{" +
                "orderId=" + orderId +
                ", destinationName='" + destinationName + '\'' +
                ", destinationEmail='" + destinationEmail + '\'' +
                ", destinationZipcode='" + destinationZipcode + '\'' +
                ", destinationPrefecture='" + destinationPrefecture + '\'' +
                ", destinationMunicipalities='" + destinationMunicipalities + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", destinationTel='" + destinationTel + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", paymentMethod=" + paymentMethod +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardExpYear='" + cardExpYear + '\'' +
                ", cardExpMonth='" + cardExpMonth + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardCvv='" + cardCvv + '\'' +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpYear() {
        return cardExpYear;
    }

    public void setCardExpYear(String cardExpYear) {
        this.cardExpYear = cardExpYear;
    }

    public String getCardExpMonth() {
        return cardExpMonth;
    }

    public void setCardExpMonth(String cardExpMonth) {
        this.cardExpMonth = cardExpMonth;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }
}
