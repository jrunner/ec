package com.t2t.top.dubbo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/10/17.
 */
public class Account implements Serializable {
    private String account;
    private String password;
    private String nick;
    private Date createTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
