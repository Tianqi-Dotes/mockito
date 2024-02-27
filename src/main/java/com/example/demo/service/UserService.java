package com.example.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.UserVo;

public interface UserService extends IService<User> {
    UserVo selectById(Long userId);

    void add(String username, String phone, List<String> features);
}