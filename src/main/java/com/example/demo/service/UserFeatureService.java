package com.example.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.UserFeature;

public interface UserFeatureService extends IService<UserFeature> {
    List<UserFeature> selectByUserId(Long userId);
}
