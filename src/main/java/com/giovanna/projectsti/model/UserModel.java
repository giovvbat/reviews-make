package com.giovanna.projectsti.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_user")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

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
}
