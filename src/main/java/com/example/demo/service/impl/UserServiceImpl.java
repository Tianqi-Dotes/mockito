package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.entity.UserFeature;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserFeatureService;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserReq;
import com.example.demo.vo.UserUpdateReq;
import com.example.demo.vo.UserVo;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserFeatureService userFeatureService;

    @Override
    public UserVo selectById(Long userId) {
        User user = userMapper.selectById(userId);
        if (user==null){
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user,userVo);
        List<UserFeature> userFeatures = userFeatureService.selectByUserId(userId);
        if(userFeatures==null||userFeatures.size()<=0){
           return userVo;
        }
        List<String> collect = userFeatures.stream().map(UserFeature::getFeatureValue).collect(Collectors.toList());
        userVo.setFeatures(collect);
        return userVo;
    }

    @Override
    public int modifyById(User userReq) {
        return userMapper.updateById(userReq);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(String username, String phone, List<String> features) {
        User user=new User();
        user.setUserName(username);
        user.setPhone(phone);
        save(user);

        List<UserFeature> collect = features.stream().map(el -> {
            UserFeature feature = new UserFeature();
            feature.setFeatureValue(el);
            feature.setUserId(user.getId());
            return feature;
        }).collect(Collectors.toList());
        userFeatureService.saveBatch(collect);
    }
}
