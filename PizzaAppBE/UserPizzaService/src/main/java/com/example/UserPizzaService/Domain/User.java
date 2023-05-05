package com.example.UserPizzaService.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {

    @Id
    private String userEmail;
    private String userName;
    private String userPassword;
    private long userPhoneNo;
    private List<Order> userOrderList;
    private List<Pizza> userPizzaCartList;

    public User() {
    }

    public User(String userEmail, String userName, String userPassword, long userPhoneNo, List<Order> userOrderList, List<Pizza> userPizzaCartList) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNo = userPhoneNo;
        this.userOrderList = userOrderList;
        this.userPizzaCartList = userPizzaCartList;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public long getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(long userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public List<Order> getUserOrderList() {
        return userOrderList;
    }

    public void setUserOrderList(List<Order> userOrderList) {
        this.userOrderList = userOrderList;
    }

    public List<Pizza> getUserPizzaCartList() {
        return userPizzaCartList;
    }

    public void setUserPizzaCartList(List<Pizza> userPizzaCartList) {
        this.userPizzaCartList = userPizzaCartList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhoneNo=" + userPhoneNo +
                ", userOrderList=" + userOrderList +
                ", userPizzaCartList=" + userPizzaCartList +
                '}';
    }
}
