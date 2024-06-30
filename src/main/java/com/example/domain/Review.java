package com.example.domain;

/**
 * レビューのドメイン.
 *
 * @author rui.inoue
 */
public class Review {
    /** id */
    private Integer id;
    /** 文章 */
    private String content;
    /** ユーザid */
    private Integer userId;
    /** 商品id */
    private Integer itemId;
    /** ポジティブな度合い */
    private double positiveValue;
    /** ネガティブな度合い */
    private double negativeValue;
    /** ニュートラルな度合い */
    private double neutralValue;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", positiveValue=" + positiveValue +
                ", negativeValue=" + negativeValue +
                ", neutralValue=" + neutralValue +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public double getPositiveValue() {
        return positiveValue;
    }

    public void setPositiveValue(double positiveValue) {
        this.positiveValue = positiveValue;
    }

    public double getNegativeValue() {
        return negativeValue;
    }

    public void setNegativeValue(double negativeValue) {
        this.negativeValue = negativeValue;
    }

    public double getNeutralValue() {
        return neutralValue;
    }

    public void setNeutralValue(double neutralValue) {
        this.neutralValue = neutralValue;
    }
}
