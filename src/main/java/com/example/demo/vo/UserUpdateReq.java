package com.example.demo.vo;

import lombok.Data;

@Data
public class UserUpdateReq {
    private Long id;
    private String userName;
    private String phone;
}
