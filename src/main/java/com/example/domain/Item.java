package com.example.domain;

import java.util.List;

/**
 * 商品のドメイン.
 *
 * @author rui.inoue
 */
public class Item {
    /** id */
    private Integer id;
    /** 商品名 */
    private String name;
    /** 説明 */
    private String description;
    /** サイズSの価格 */
    private Integer priceS;
    /** サイズMの価格 */
    private Integer priceM;
    /** サイズLの価格 */
    private Integer priceL;
    /** 画像のパス */
    private String imagePath;
    /** 削除フラグ */
    private Boolean deleted;
    /** トッピングのリスト */
    private List<Topping> toppingList;
    /** レビューのリスト */
    private List<Review> reviewList;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priceS=" + priceS +
                ", priceM=" + priceM +
                ", priceL=" + priceL +
                ", imagePath='" + imagePath + '\'' +
                ", deleted=" + deleted +
                ", toppingList=" + toppingList +
                ", reviewList=" + reviewList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriceS() {
        return priceS;
    }

    public void setPriceS(Integer priceS) {
        this.priceS = priceS;
    }

    public Integer getPriceM() {
        return priceM;
    }

    public void setPriceM(Integer priceM) {
        this.priceM = priceM;
    }

    public Integer getPriceL() {
        return priceL;
    }

    public void setPriceL(Integer priceL) {
        this.priceL = priceL;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<Topping> getToppingList() {
        return toppingList;
    }

    public void setToppingList(List<Topping> toppingList) {
        this.toppingList = toppingList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
