package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StubTest {

    @Mock
    private List<String> mockList;
    @Mock
    private UserServiceImpl mockUserService;
    @Spy
    private UserServiceImpl spyUserService;

    @Test
    //插桩2种写法
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

    @Test
    //2种方式的区别
    public void test3(){
        Mockito.doReturn(99).when(mockUserService).getNumber();
        System.out.println("mock: "+mockUserService.getNumber());

        // spy在没有插桩时 调用默认方法 when导致先执行一次原方法,spy推荐用doReturn不会去执行方法
        //when会执行spy方法 打印
        Mockito.doReturn(99).when(spyUserService).getNumber();
        //Mockito.when(spyUserService.getNumber()).thenReturn(1000);
        System.out.println("spy: "+spyUserService.getNumber());

    }

    @Test
    //抛出异常
    public void test4(){
        //方法1
        Mockito.doThrow(RuntimeException.class).when(mockList).clear();
        try {
            mockList.clear();
            Assertions.fail();
        }catch (RuntimeException e){
            Assertions.assertTrue(e instanceof RuntimeException);
        }

        // 方法2
        Mockito.when(mockList.get(ArgumentMatchers.anyInt()))
            .thenThrow(RuntimeException.class);
        try {
            mockList.get(0);
            Assertions.fail();
        }catch (RuntimeException e){
            Assertions.assertTrue(e instanceof RuntimeException);
        }
    }

    @Test
    public void test5(){
        //Mockito.when(mockList.size()).thenReturn(1).thenReturn(2).thenReturn(3);
        //改写
        Mockito.when(mockList.size()).thenReturn(1,2,3);
        Assertions.assertEquals(1,mockList.size());
        Assertions.assertEquals(2,mockList.size());
        Assertions.assertEquals(3,mockList.size());
        Assertions.assertEquals(3,mockList.size());
    }

    @Test
    public void test6(){
        Mockito.when(mockList.get(ArgumentMatchers.anyInt())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                //获取传入参数
                Integer argument = invocationOnMock.getArgument(0, Integer.class);
                //指定返回结果
                return String.valueOf(argument*100);
            }
        });
        System.out.println("answer: "+mockList.get(1));
    }

    @Test
    //执行原始方法
    public void test7(){
        //方法1 mock
        Mockito.when(mockUserService.getNumber()).thenCallRealMethod();
        Assertions.assertEquals(0,mockUserService.getNumber());

        //方法2 spy
        Assertions.assertEquals(0,spyUserService.getNumber());
        //不让spy调用原方法 只需要插桩即可
    }


    @Test
    //verify 的多种方法
    public void test8(){
        mockList.add("one");
        Assertions.assertEquals(0,mockList.size());
        mockList.clear();

        Mockito.verify(mockList,Mockito.times(1))
            .add("one");
        Mockito.verify(mockList).clear();//默认是1次

        //检验没有调用
        Mockito.verify(mockList,Mockito.times(0))
            .get(1);
        Mockito.verify(mockList,Mockito.never()).get(1);

        //校验最少几次
        Mockito.verify(mockList,Mockito.atLeast(1))
            .clear();
    }

}
