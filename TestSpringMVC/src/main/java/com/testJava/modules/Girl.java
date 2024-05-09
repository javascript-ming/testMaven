package com.testJava.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Girl {
    @Value("20")
    private int age;
    @Value("小秋")
    private String name;
    @Autowired
    private Boy bf;

    public Boy getBf() {
        return bf;
    }

//    public void setBf(Boy bf) {
//        this.bf = bf;
//    }

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

    public Girl(int age, String name, Boy bf) {
        this.age = age;
        this.name = name;
        this.bf = bf;
    }

    public Girl() {
    }
}
