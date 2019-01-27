import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/*
    Client class implements all necessary methods to send requests and process responses from the server
 */
public class Client {

    private Socket socket;
    private PrintStream stream;
    private Scanner scanner;

    public Client() {
        try {
            socket = new Socket("localhost", 1337);
        } catch (Exception e) {
            System.out.println("Could not initiate a web socket.");
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) throws Exception {
////
//        Socket socket = new Socket("localhost", 8000);
//        Scanner scanner = new Scanner(socket.getInputStream());
//        PrintWriter configFile = new PrintWriter("config.cfg");
//        String line;
//
//        while(scanner.hasNextLine()) {
//            line = scanner.nextLine();
//            System.out.println(line);
//            if(line != null){
//                configFile.println(line);
//            }
//        }
//        configFile.close();
//
//        System.out.println("File saved.");
//    }

    /*
     *  Method sends request to the server depending on given parameter
     * @int parameter
     */
    private void sendRequest(int parameter) {
        try {
            stream = new PrintStream(socket.getOutputStream());
            stream.println(parameter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        Method sends request for level config
     */
    public void getLevel(int levelNumber) {
        sendRequest(levelNumber);
        processLevelResponse(levelNumber);
    }

    /*
        Method sends request for leaderboard
    */
    public void getScores() {
        sendRequest(0);
        processLeaderboardResponse();
    }

    /*
       Method processes level response from the server
     */
    private void processLevelResponse(int levelNumber) {
        try {
            scanner = new Scanner(socket.getInputStream());
            PrintWriter configFile = new PrintWriter("src/config/level" + levelNumber + ".cfg");
            String line;
            System.out.println("dupa");
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
                if (line != null) {
                    configFile.println(line);
                }
            }
            configFile.close();

            System.out.println("File saved.");
        } catch(Exception e) {
            System.out.println("Could not process the response.");
            System.out.println(e);
        }
    }

    /*
       Method processes leaderboard response from the server
     */
    private void processLeaderboardResponse() {
        try {
            scanner = new Scanner(socket.getInputStream());
            PrintWriter configFile = new PrintWriter("src/config/scores.text");
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line != null) {
                    configFile.println(line);
                }
            }
            configFile.close();

            System.out.println("File saved.");
        } catch(Exception e) {
            System.out.println("Could not process the response.");
            System.out.println(e);
        }
    }
}
