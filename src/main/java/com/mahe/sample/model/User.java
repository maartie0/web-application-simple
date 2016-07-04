package com.mahe.sample.model;

/**
 * Created by maha on 04/07/16.
 */
import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {}

    public User(String name,String password) {
        this.name = name;
        this.password = password;
    }


    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name  + ", password=" + password + "]";
    }

    public boolean equals(String username,String password) {
        return (this.name.equals(username) && this.password.equals(password));
    }

}