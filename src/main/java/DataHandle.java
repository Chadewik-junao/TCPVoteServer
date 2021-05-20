import java.io.*;
import java.net.Socket;
import java.util.List;

//线程数据控制
public class DataHandle {

    public DataHandle(TCPThread tcpThread) throws Exception {
        //对象数据的输入与输出，需要用ObjectInputStream和ObjectOutputStream进行
        ObjectInputStream inputStream = tcpThread.getInputStream();
        ObjectOutputStream outputStream = tcpThread.getOutputStream();
        DataBaseConnect dbConnect = new DataBaseConnect();
        while (tcpThread.flag) {
            TCPVoteMsg clientMsg = (TCPVoteMsg) inputStream.readObject();
            System.out.println("get client UID:"+clientMsg.getSerialVersionUID());
            System.out.println("get client Msg:" + clientMsg.getStatusCode());
            System.out.println("get client Vote:" + clientMsg.getVoteId());
            try {
                switch (clientMsg.getStatusCode()) {
                    case 101: {//查询投票
                        List<Vote> result = dbConnect.selectVoteTable();
                        TCPVoteMsg tcpVoteMsg = new TCPVoteMsg(901, result);
                        outputStream.writeObject(tcpVoteMsg);
                        System.out.println(result);
                    }
                    break;
                    case 201: {//查询候选人
                        List<Candidate> result = dbConnect.selectCandidateTable(clientMsg);
                        outputStream.writeObject(new TCPVoteMsg(902,clientMsg.getVoteId(),clientMsg.getCandidateId(),result));
                        System.out.println(result);
                    }
                    break;
                    case 301:{//投票
                        if(dbConnect.insertVote(clientMsg)){
                            outputStream.writeObject(new TCPVoteMsg(903));
                            System.out.println("true");
                        }
                    }
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //outputStream.writeObject(new TCPVoteMsg(999,result));
            //clientMsg=(TCPVoteMsg)inputStream.readObject();
        }
        tcpThread.close();
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
