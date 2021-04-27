import java.io.IOException;
import java.net.Socket;
//TCP线程类
public class TCPThread implements Runnable{
    private Socket socket;

    public TCPThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("连接++");
            new DataHandle(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
