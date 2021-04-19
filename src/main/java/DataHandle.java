import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class DataHandle {
    public DataHandle(Socket socket) throws Exception {
        Reader reader = new InputStreamReader(socket.getInputStream());
        char chars[] = new char[64];
        int len;
        StringBuilder client = new StringBuilder();
        String temp;
        int index;
        while ((len = reader.read(chars)) != -1) {
            temp = new String(chars, 0, len);
            if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收
                client.append(temp.substring(0, index));
                break;
            }
            client.append(temp);
        }
        System.out.println("from client: " + client);
        //读完后写一句
        Writer writer = new OutputStreamWriter(socket.getOutputStream());
        writer.write("Client Connect");
        writer.flush();
        writer.close();
        reader.close();
        socket.close();
    }
}
