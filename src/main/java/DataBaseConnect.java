import java.sql.Connection;
import java.sql.DriverManager;
//数据库连接
public class DataBaseConnect {

    public static void connext(String[] args) {
        String driverName = "com.mysql.cj.jdbc.Driver";

        String dbURL = "jdbc:mysql://localhost:3306/votesystem?&serverTimezone=Asia/Shanghai";

        String userName = "root";

        String userPwd = "123456";

        try {
            Class.forName(driverName);
            Connection con = DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("连接数据库成功");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("连接失败");

        }

    }

}