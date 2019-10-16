package com.Study.bean;

/**
 * @description: 用户登陆表单信息
 * @Author: Mutong
 * @Date: 2019.10.13 11:03
 */
public class LoginForm {

    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(String verifiCode) {
        this.verifiCode = verifiCode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
