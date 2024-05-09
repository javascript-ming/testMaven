package com.testJava.controller;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.testJava.modules.Boy;
import com.testJava.modules.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// jdbc的执行过程
//1. 加载驱动（老方法）
//1. 创建连接
//2. 创建语句执行对象
//3. 声明语句并执行
//4. 获取处理结果
//5. 关闭数据库资源
@Controller
public class TestController {
    @RequestMapping("/index1")
    public String index() {
        return "index.jsp";
    }
    @RequestMapping("/testGet")
    public String testGet(String name, int age) {
        System.out.println(name + '-' +age);
        return "index.jsp";
    };
    @RequestMapping("/testGet2")
    public String testGet2(Person p) {
        System.out.println(p.getName()+'-'+p.getAge());
        return "index.jsp";
    }
}
