package com.example.domain;

/**
 * ブックマークのドメイン.
 *
 * @author rui.inoue
 */
public class Bookmark {
    /** id */
    private Integer id;
    /** ユーザid */
    private Integer userId;
    /** 商品id */
    private Integer itemId;

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemId=" + itemId +
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}
