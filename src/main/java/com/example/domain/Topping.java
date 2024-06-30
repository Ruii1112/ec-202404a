package com.example.domain;

/**
 * トッピングのドメイン.
 *
 * @author rui.inoue
 */
public class Topping {
    /** id */
    private Integer id;
    /** 名前 */
    private String name;
    /** 価格 */
    private Integer price;

    @Override
    public String toString() {
        return "Topping{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
