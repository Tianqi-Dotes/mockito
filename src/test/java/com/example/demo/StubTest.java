package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
public class StubTest {

    @Mock
    private List<String> mockList;

    @Test
    public void test1(){
        //方法一断言
        Mockito.doReturn("zero").when(mockList).get(0);
        Assertions.assertEquals("zero", mockList.get(0));

        //方法二 指定方法返回值
        Mockito.when(mockList.get(1)).thenReturn("one");
        Assertions.assertEquals("one", mockList.get(1));

    }

    @Test
    //测试void 方法
    public void test2(){
        //指定方法执行
        Mockito.doNothing().when(mockList).clear();
        mockList.clear();
        //目标类
        Mockito.verify(mockList, Mockito.times(1))
            //执行什么方法
            .clear();
    }

}
