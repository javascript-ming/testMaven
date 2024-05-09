import java.sql.*;
import java.util.List;

public class Test {
    public static <e> void main(String[] args) throws SQLException {
        // JDBC连接数据库
        // 加载驱动
        // 获取数据库连接
        String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "rootroot";
        Connection connection = DriverManager.getConnection(url, username, password);
        // 创建语句对象
        Statement statement = connection.createStatement();
        // 通过语句对象执行sql命令，并有返回结果：对数据库的数据条数影响值
        ResultSet resultSet = statement.executeQuery("select * from t_book");
//        statement.executeQuery returns List
//                statement.executeUpdate() returns int
        try {
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                double price = resultSet.getDouble("price");
                System.out.println("书籍id："+ id +"书籍名称："+ name + "书籍作者："+ author + "书籍价格：" + price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

    }
}
