package com.example.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ユーザのドメイン.
 *
 * @author rui.inoue
 */
public class User {
    /** id */
    private Integer id;
    /** 名前 */
    private String name;
    /** メールアドレス */
    private String email;
    /** パスワード */
    private String password;
    /** 郵便番号 */
    private String zipcode;
    /** 都道府県 */
    private String prefecture;
    /** 市区町村 */
    private String municipalities;
    /** 住所 */
    private String address;
    /** 電話番号 */
    private String telephone;
    /** 管理者フラグ */
    private boolean adminFlag;
    /** 削除フラグ */
    private LocalDateTime deletedAt;
    /** ブックマークした商品リスト */
    private List<Item> bookmarkList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", prefecture='" + prefecture + '\'' +
                ", municipalities='" + municipalities + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adminFlag=" + adminFlag +
                ", deletedAt=" + deletedAt +
                ", bookmarkList=" + bookmarkList +
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getMunicipalities() {
        return municipalities;
    }

    public void setMunicipalities(String municipalities) {
        this.municipalities = municipalities;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(boolean adminFlag) {
        this.adminFlag = adminFlag;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<Item> getBookmarkList() {
        return bookmarkList;
    }

    public void setBookmarkList(List<Item> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }
}
