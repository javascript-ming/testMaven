package com.testJava.modules;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Boy {
    @Value("21")
    private int age;
    @Value("小明")
    private String name;

    public int getAge() {
        return age;
    }

//    public void setAge(int age) {
//        this.age = age;
//    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public Boy(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Boy() {
    }
}
