package com.mahe.sample.DataHandler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Department {

    @Id
    @GeneratedValue
    private Long id;


    private String name;

    @OneToMany(mappedBy="department",cascade=CascadeType.PERSIST)
    private List<User> users = new ArrayList<User>();

    public Department() {
        super();
    }
    public Department(String name) {
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
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}