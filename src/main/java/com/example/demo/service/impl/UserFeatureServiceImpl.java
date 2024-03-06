package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.UserFeature;
import com.example.demo.mapper.UserFeatureMapper;
import com.example.demo.service.UserFeatureService;

@Service
public class UserFeatureServiceImpl extends ServiceImpl<UserFeatureMapper, UserFeature> implements UserFeatureService {

    @Override
    public List<UserFeature> selectByUserId(Long userId) {
        if (userId==null){
            return null;
        }
        LambdaQueryWrapper<UserFeature> wrapper = Wrappers.<UserFeature>lambdaQuery().eq(UserFeature::getUserId, userId);
        return this.list(wrapper);
    }
}
