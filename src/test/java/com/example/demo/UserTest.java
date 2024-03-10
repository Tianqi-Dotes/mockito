package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.User;
import com.example.demo.entity.UserFeature;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserFeatureService;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.vo.UserVo;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @InjectMocks
    @Spy
    private UserServiceImpl userService;

    @Mock
    //嵌入上面的inject对象
    private UserMapper userMapper;

    @Mock
    private UserFeatureService userFeatureService;

    @Test
    public void test1(){
        Mockito.doReturn(userMapper).when(userService).getBaseMapper();
        UserVo userVo = userService.selectById(1L);
        System.out.println(userVo);
    }

    @Test
    // 完全体 mock
    public void test2(){
        User res =new User();
        res.setId(1l);
        res.setUserName("tq");
        res.setPhone("11");

        List<UserFeature> list=new ArrayList<>();
        UserFeature userFeature=new UserFeature();
        userFeature.setUserId(1L);
        userFeature.setFeatureValue("gg");
        userFeature.setId(1L);
        list.add(userFeature);

        Mockito.doReturn(res).when(userMapper).selectById(1L);
        Mockito.doReturn(list).when(userFeatureService).selectByUserId(1L);

        UserVo userVo = userService.selectById(1L);

        Assertions.assertEquals("tq",userVo.getUserName());
        Assertions.assertEquals(1,userVo.getFeatures().size());
    }
}
