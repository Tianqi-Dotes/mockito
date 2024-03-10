package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.service.UserFeatureService;
import com.example.demo.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class InjectMocks {

    //inject mock 不能是接口,因为会创建对应的实例
    //默认创建普通对象，没有经过处理
    //因此配合spy使用，使其变为调用真实方法的mock对象
    @org.mockito.InjectMocks
    @Spy
    //加上 inject mocks的区别是,注入的属性都是null
    //自己进行注入标记 inject mocks的位置
    //使用构造器，setter，反射进行注入
    private UserServiceImpl userService;

    @Mock
    private UserFeatureService userFeatureService;

    @Test
    public void test1(){

    }
}
