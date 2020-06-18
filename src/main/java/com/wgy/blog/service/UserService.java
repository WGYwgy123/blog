package com.wgy.blog.service;

import com.wgy.blog.entity.User;

public interface UserService {

    User checkUser(String username, String password);
}
