package com.t2t.top.dubbo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangpengfei on 2015/10/17.
 * 注意:类需要序列化
 */
public class UserDto implements Serializable {
    private int id;
    private String name;
    private int age;
    private List<String> addresses;
    private List<Account> accounts;

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
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
