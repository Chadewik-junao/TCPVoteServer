import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//主类，程序入口
public class VoteServer {
    private boolean isStart = false;

    //private static DataBaseConnect dbConnect;
    public static void main(String[] args) throws IOException {
        //dbConnect=new DataBaseConnect();
        try {
//            List result=dbConnect.selectVoteTable();//输出投票表
//
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//            String time = df.format(new Date());
//            System.out.println(time);
//            InetAddress ip4 = Inet4Address.getLocalHost();
//            System.out.println(ip4.getHostAddress());
//            long start = System.currentTimeMillis();
//            Process process = Runtime.getRuntime().exec(
//                    new String[] { "wmic", "cpu", "get", "ProcessorId" });
//            process.getOutputStream().close();
//            Scanner sc = new Scanner(process.getInputStream());
//            sc.next();
//            String cpuid = sc.next();
//            System.out.println(cpuid);
//
//            List result=dbConnect.selectCandidateTable(new TCPVoteMsg(101,"001"));
//            System.out.println(result);
//            TCPVoteMsg clientMsg=new TCPVoteMsg(101,"001");
//            clientMsg.setVoter(new Voter("001","001001","cdk"));
//            System.out.println(dbConnect.insertVote(clientMsg));
            new ServerUI();//界面
            new VoteServer().start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    void start() throws IOException {
        int port = 10086;
        //定义一个ServerSocket监听在端口上
        setStart(true);
        ServerSocket server = new ServerSocket(port);
        while (isStart) {
            //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
            Socket socket = server.accept();
            //建立一个新的自定义线程来处理它
            new Thread(new TCPThread(socket)).start();
        }
    }

    void finish() {
        setStart(false);
    }

    private void setStart(boolean setStart) {
        this.isStart = setStart;
    }
}

