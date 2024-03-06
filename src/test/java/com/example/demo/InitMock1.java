package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.service.UserService;


@ExtendWith(MockitoExtension.class)
public class InitMock1 {

    @Mock
    private UserService mockUserService;

    @Spy
    private UserService spyUserService;

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
