package com.example.form;

import jakarta.validation.constraints.NotBlank;

/**
 * レビューを入力するフォーム.
 *
 * @author rui.inoue
 */
public class ReviewForm {
    /** 商品のid */
    private Integer itemId;
    /** レビュー内容 */
    @NotBlank(message = "レビューが未入力です")
    private String content;

    @Override
    public String toString() {
        return "ReviewForm{" +
                "itemId=" + itemId +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
