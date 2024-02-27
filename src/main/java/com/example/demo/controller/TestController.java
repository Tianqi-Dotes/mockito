package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;
import com.example.demo.vo.UserReq;
import com.example.demo.vo.UserVo;

@RestController
@Slf4j
@Validated
public class TestController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/test", method = GET)
    public Map<String, String> testGET() {
        Map m = new HashMap<String, String>();
        m.put("test", "http://http://122.51.58.127:7777/swagger-ui.html");
        m.put("time",String.valueOf(System.currentTimeMillis()));
        return m;
    }


    @RequestMapping(value = "/queryById", method = GET)
    public UserVo get(@NotNull Long userId) {
        return userService.selectById(userId);
    }

    @PostMapping("/add")
    public String add(@RequestBody @Validated UserReq userReq){
        userService.add(userReq.getUsername(),userReq.getPhone(), userReq.getFeatures());
        return "ok";
    }
}
