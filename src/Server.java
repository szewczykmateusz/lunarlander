import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server implements Runnable{

    ServerSocket serverSocket;
    Scanner scanner;
    Socket socket;

    public Server(Socket socket) {
            this.socket = socket;
    }

//    public static void main(String[] args) throws Exception {
//
//        ServerSocket serverSocket = new ServerSocket(8000);
//        Socket socket = serverSocket.accept();
//        FileInputStream fileStream = new FileInputStream("src/config/level1.cfg");
//        byte b[] = new byte[2002];
//
//        fileStream.read(b, 0, b.length);
//
//        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write(b, 0, b.length);
//
//    }

    public static void main(String args[]) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1337);
        System.out.println("Listening on port 8000...");

        //prints out current server directory
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            new Thread(new Server(socket)).start();
        }
    }

    public void run() {
        try {
           scanner = new Scanner(socket.getInputStream());
           processRequest();
        } catch (Exception e) {
            System.err.println("Error in processing client request.");
            e.printStackTrace();
        }
    }

    public void processRequest() throws Exception{
        int parameter = scanner.nextInt();
        if(parameter == 0) {
            //tutaj wyślemy leaderboard i zapiszemy wynik
            //sendScores();
            //saveScore();
        } else if (parameter > 0) {
            sendLevelConfig(parameter);
        }
    }

//    public void sendScores() {
//        FileInputStream fileStream = new FileInputStream("scores.txt");
//        byte b[] = new byte[2002];
//
//        fileStream.read(b, 0, b.length);
//
//        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write(b, 0, b.length);
//    }

//    public void saveScores() {
//
//    }

    public void sendLevelConfig(int parameter) throws Exception {
        FileInputStream fileStream = new FileInputStream("config/level" + parameter + ".cfg");

        byte[] b = new byte[2002];

        fileStream.read(b, 0, b.length);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(b, 0, b.length);
    }
}