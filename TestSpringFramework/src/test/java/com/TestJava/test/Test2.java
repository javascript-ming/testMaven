package com.TestJava.test;

import com.testJava.pojo.Girl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    public static void main(String[] args) {
//        create spring container
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        get Object by container
        Girl g1 = (Girl)applicationContext.getBean("girl"); // 通过id指定 或者 @Component会自动注册小写字母开头的类名单词的id
        System.out.println(g1.getName() + g1.getBf().getName());
    }
}
