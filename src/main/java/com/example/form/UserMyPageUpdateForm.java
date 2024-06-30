package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * ユーザ情報更新時のフォーム.
 *
 * @author YusakuTerashima
 */
public class UserMyPageUpdateForm {
    /** 名前 */
    @NotBlank(message = "名前は必須です")
    private String name;
    /** メールアドレス */
    @Email(message = "形式が正しくありません")
    @NotBlank(message = "メールアドレスは必須です")
    private String email;
    /** 郵便番号 */
    @Pattern(regexp="^[0-9]{3}-[0-9]{4}$", message = "形式が正しくありません")
    @NotBlank(message = "郵便番号は必須です")
    private String zipcode;
    /** 都道府県 */
    @NotBlank(message = "都道府県は必須です")
    private String prefecture;
    /** 市区町村 */
    @NotBlank(message = "市区町村は必須です")
    private String municipalities;
    /** 住所 */
    @NotBlank(message = "住所は必須です")
    private String address;
    /** 電話番号 */
    @Pattern(regexp="^[0-9]{3,4}-[0-9]{2,4}-[0-9]{4}$", message = "形式が正しくありません")
    @NotBlank(message = "電話番号は必須です")
    private String telephone;

    @Override
    public String toString() {
        return "UserMyPageForm{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", municipalities='" + municipalities + '\'' +
                ", prefecture='" + prefecture + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMunicipalities() {
        return municipalities;
    }

    public void setMunicipalities(String municipalities) {
        this.municipalities = municipalities;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
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
}
