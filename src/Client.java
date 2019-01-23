import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    //private Socket socket;

    public static void main(String[] args) throws Exception {


        Socket socket = new Socket("localhost", 8000);
        Scanner scanner = new Scanner(socket.getInputStream());
        String line, data = "";

        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            if(line != null){
                if(data == null){
                    data = line;
                    data += "\n";
                    continue;
                }
                data += line;
                data += "\n";
            }
        }

        File file = new File("C://ggg/config.cfg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        System.out.println(data);

        try {

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = data.getBytes();

            fileOutputStream.write(contentInBytes);
            fileOutputStream.flush();
            fileOutputStream.close();

            System.out.println("File Saved.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
