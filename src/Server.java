import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8000);
        Socket socket = serverSocket.accept();

        FileInputStream fileStream = new FileInputStream("C://Users/tomas/Desktop/lunar-repo-PROZE/proze-lunar-lander-szewczyk-chruscinski/src/config/level1.cfg");
        byte b[] = new byte[2002];

        fileStream.read(b, 0, b.length);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(b, 0, b.length);

    }
}
