package org.sang.redis.model;


import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -3946734305303957850L;

    public User() {
    }

    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
