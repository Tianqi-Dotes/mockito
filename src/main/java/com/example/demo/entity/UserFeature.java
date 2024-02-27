package com.example.demo.entity;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_feature")
@Data
public class UserFeature {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String featureValue;
}
