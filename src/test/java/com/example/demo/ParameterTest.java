package com.example.demo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserReq;

@ExtendWith(MockitoExtension.class)
public class ParameterTest {

    @Mock
    private UserService mockUserService;


    @Test
    public void chazhuang1(){
        User req1=new User();
        //指定 方法返回值
        //入参必须为 req1
        Mockito.doReturn(99).when(mockUserService)
            .modifyById(req1);
        int i = mockUserService.modifyById(req1);
        System.out.println(i);
    }

    @Test
    //任意参数指定
    public void chazhuang2(){
        User req1=new User();
        //指定 方法返回值
        //入参为任意对象
        Mockito.doReturn(99).when(mockUserService)
            .modifyById(ArgumentMatchers.any());
        int i = mockUserService.modifyById(req1);
        System.out.println(i);
    }

    @Test
    // verify校验方法入参 执行次数
    public void chazhuang3(){
        mockUserService.add("tq","110",new ArrayList<>());
        Mockito.verify(mockUserService, Mockito.times(1))
            //匹配条件
            .add("tq","110",new ArrayList<>());

        //通用匹配 验证
        Mockito.verify(mockUserService, Mockito.times(1))
            //调用什么方法 匹配条件
            .add(ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),
                ArgumentMatchers.anyList());
    }
}
