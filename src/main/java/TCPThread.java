import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
//TCP线程类
public class TCPThread implements Runnable{
    private Socket socket;
    ObjectInputStream inputStream ;
    ObjectOutputStream outputStream;

    public TCPThread(Socket socket) {
        this.socket = socket;
    }

    public ObjectInputStream getInputStream() {
        if(inputStream==null) {
            try {
                inputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        if (outputStream==null) {
            try {
                outputStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outputStream;
    }




    @Override
    public void run() {
        try {
            System.out.println("连接++");
            new DataHandle(this);
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
