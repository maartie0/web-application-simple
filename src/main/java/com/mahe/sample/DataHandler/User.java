package com.mahe.sample.DataHandler;

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
    @ManyToOne
    private Department department;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {}

    public User(String name,String password, Department department) {
        this.name = name;
        this.password = password;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", department="
                + department.getName() + "]";
    }

}