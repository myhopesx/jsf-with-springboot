package com.example.jsfspringbootjwt.controller;
import com.example.jsfspringbootjwt.model.User;
import com.example.jsfspringbootjwt.service.UserListService;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("userListController")
@SessionScoped
public class UserListController implements Serializable {

    private List<User> userList;

    private final UserListService userListService;

    public UserListController(UserListService userListService) {
        this.userListService = userListService;
    }

    public void initUserList() {
        userList = userListService.findAllUserList();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
