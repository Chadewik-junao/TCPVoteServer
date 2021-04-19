import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class VoteServer {
    private boolean isStart=false;
    public static void main(String[] args)throws IOException {
        new VoteServer().start();
        new ServerUI();
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

    void finish(){
        setStart(false);

    }

    private void setStart(boolean setStart){
        this.isStart=setStart;
    }
}

