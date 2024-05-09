package com.testMybatis.test;

import com.testJar.pojo.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        // 导入mybatis配置
        String resource = "mybatis.xml";
        // 输入流读取配置
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 工厂构建器创建工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 创建会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行查询
        List<Book> list = sqlSession.selectList("com.testJar.pojo.BookMapper.selectAllBooks");
        for(int i = 0; i < list.size(); i++) {
            Book book = (Book)(list.get(i));
            System.out.println(book.getName() + "---" + book.getAuthor());
        }
        // close
        sqlSession.close();

    }

}
