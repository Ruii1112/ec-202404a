package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * ユーザ登録時のフォーム.
 *
 * @author YusakuTerashima
 */
public class UserRegisterForm {
    /** 苗字 */
    @NotBlank(message = "苗字は必須です")
    private String lastName;
    /** 名前 */
    @NotBlank(message = "名前は必須です")
    private String firstName;
    /** メールアドレス */
    @Email(message = "形式が正しくありません")
    @NotBlank(message = "メールアドレスは必須です")
    private String email;
    /** パスワード */
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=/*_!?-])(?=\\S+$).{8,}$",
            message = "パスワードは、8文字以上で、大文字、小文字、数字、および記号を含める必要があります。(使用可能な記号：@#$%^&+=/*_!?-)"
    )
    @NotBlank(message = "パスワードは必須です")
    private String password;
    /** 確認用パスワード */
    @NotBlank(message = "確認用パスワードは必須です")
    private String confirmPassword;
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
        return "UserRegisterForm{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", municipalities='" + municipalities + '\'' +
                ", prefecture='" + prefecture + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
