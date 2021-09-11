package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user", schema = "public")
public class UserModel {
    @Id
    private String username;

    @Column
    private String password;


    public UserModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setLogin(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "userModel{" +
                "login='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
