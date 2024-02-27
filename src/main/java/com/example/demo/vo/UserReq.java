package com.example.demo.vo;

import lombok.Data;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class UserReq {
    @NotBlank
    private String username;
    @NotBlank
    private String phone;
    @NotEmpty
    private List<String> features;
}
