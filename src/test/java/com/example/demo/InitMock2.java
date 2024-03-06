package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.service.UserService;


/**
 * 通过静态方法处理
 */
public class InitMock2 {

    private UserService mockUserService;
    private UserService spyUserService;

    @BeforeEach
    public void init(){
        mockUserService=Mockito.mock(UserService.class);
        spyUserService=Mockito.spy(UserService.class);
    }

    @Test
    public void test1(){
        //判断对象是不是mock对象
        System.out.println(Mockito.mockingDetails(mockUserService).isMock());
        //判断对象是不是spy对象
        System.out.println(Mockito.mockingDetails(mockUserService).isSpy());

        //判断对象是不是mock对象 spy是mock对象的一种
        System.out.println(Mockito.mockingDetails(spyUserService).isMock());
        //判断对象是不是spy对象
        System.out.println(Mockito.mockingDetails(spyUserService).isSpy());
    }
}
