package com.example.demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVo {
    private Long id;

    private String userName;

    private String phone;
    private List<String> features;
}
