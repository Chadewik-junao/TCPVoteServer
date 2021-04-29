import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//数据库连接
public class DataBaseConnect {
    //连接数据库
    private Connection dbConnection;

    // 传递sql语句
    private Statement stt;

    // 结果集
    private ResultSet set;

    //时间转换
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DataBaseConnect() {
        final String driverName = "com.mysql.cj.jdbc.Driver";

        final String dbURL = "jdbc:mysql://localhost:3306/votesystem?serverTimezone=GMT%2B8&amp&useSSL=false";

        final String userName = "root";

        final String userPwd = "123456";

        try {
            Class.forName(driverName);
            dbConnection = DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("连接数据库成功");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("连接失败");

        }
    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    //输出投票表
    public List<Vote> selectVoteTable() throws Exception{
        String sql="select * from votetable";
        stt = dbConnection.createStatement();
        List<Vote> voteList=new ArrayList<>();
        // 返回结果集
        set = stt.executeQuery(sql);
        // 获取数据
        while (set.next()) {
            voteList.add(new Vote(set.getString(1),set.getString(2),new java.sql.Date(sdf.parse(set.getString(3)).getTime()),new java.sql.Date(sdf.parse(set.getString(4)).getTime()),set.getInt(5)));
            System.out.println(set.getString(1)+set.getString(2)+set.getString(3)+set.getString(4)+set.getString(5));
        }
        return voteList;
    }

    //查询对应投票的候选人
    public List<Candidate> selectCandidateTable(TCPVoteMsg clientMsg)throws Exception{

        return null;
    }

    public void disConnect(){

    }
}