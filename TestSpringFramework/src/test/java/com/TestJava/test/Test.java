package com.TestJava.test;

import com.testJava.pojo.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
//        创建spring
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Book bk= (Book)ac.getBean("bk");
        System.out.println("name:" + bk.getName() + "id:" + bk.getId());
    }
}
