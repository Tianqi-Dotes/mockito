package com.example.demo.entity;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userName;

    private String phone;
}
