package HW_6.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    public static String getExitWord() {
        return exitWord;
    }
    private static final String exitWord = "QuitFromSystem!";
    private static boolean isExit = true;


    public static void main(String[] args) throws IOException {
        String message;
        Socket socket = new Socket("localhost", 8189);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner input = new Scanner(System.in);

        Thread toReadService = new Thread(() -> {
            do {
                String messageFromServer;
                try {
                    messageFromServer = in.readUTF();

                  System.out.println("Serves's msg: " + messageFromServer);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (isExit);

        });

       toReadService.start();

            do {
                System.out.println("Введите сообщение.Для выхода введите " + exitWord);
                message = input.nextLine();
                out.writeUTF(message);
                out.flush();
                if (message.equals(exitWord)) {

                    System.out.println("Finished");
                    isExit = false;
                }
            } while (isExit);
    }
}

//оставил для себя.
// Пример как послать файл из вебинараю
//            if (message.equals("send")) {
//                FileInputStream is = new FileInputStream("src/main/java/lesson6/client/file.txt");
//                String name = "file.txt";
//                int length = is.available();
//                System.out.println("length = " + length);
//                out.writeUTF("send");
//                out.writeUTF(name);
//                out.writeInt(length);
//                for (int i = 0; i < length; i++) {
//                    out.write(is.read());
//                }
//                out.flush();
//            } else {
//
//                }



