package com.wgy.blog.service.Impl;

import com.wgy.blog.dao.UserRepository;
import com.wgy.blog.entity.User;
import com.wgy.blog.service.UserService;
import com.wgy.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        System.out.println(MD5Utils.code("111111"));
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
