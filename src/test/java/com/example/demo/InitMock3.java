package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.example.demo.service.UserService;


/**
 * openMocks去触发创建
 */
public class InitMock3 {

    @Mock
    private UserService mockUserService;
    @Spy
    private UserService spyUserService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
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
