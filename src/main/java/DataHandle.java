import java.io.*;
import java.net.Socket;
//线程数据的控制类
public class DataHandle {

    public DataHandle(TCPThread tcpThread) throws Exception {
        //对象数据的输入与输出，需要用ObjectInputStream和ObjectOutputStream进行
        ObjectInputStream inputStream=tcpThread.getInputStream();
        ObjectOutputStream outputStream=tcpThread.getOutputStream();

        TCPVoteMsg clientMsg=(TCPVoteMsg)inputStream.readObject();
        while(clientMsg.getStatusCode()!=-1){

            System.out.println("get client Msg:"+clientMsg.getStatusCode());
            System.out.println("get client Vote:"+clientMsg.getVoteId());
            outputStream.writeObject(new TCPVoteMsg(999));
            clientMsg=(TCPVoteMsg)inputStream.readObject();
        }



//        Reader reader = new InputStreamReader(socket.getInputStream());
//        char chars[] = new char[64];
//        int len;
//        StringBuilder client = new StringBuilder();
//        String temp;
//        int index;
//        while ((len = reader.read(chars)) != -1) {
//            temp = new String(chars, 0, len);
//            if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收
//                client.append(temp.substring(0, index));
//                break;
//            }
//            client.append(temp);
//        }
//        System.out.println("from client: " + client);
//        //读完后写一句
//        Writer writer = new OutputStreamWriter(socket.getOutputStream());
//        writer.write("Client Connect");
//        writer.flush();
//        writer.close();
//        reader.close();
//        socket.close();
    }
}
