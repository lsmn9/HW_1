package HW_6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import static HW_6.Client.Client.getExitWord;


public class Server {

    private static boolean isExit = true;
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8189);
        System.out.println("Сервер запущен, ожидаем подключения пользователя...");
        Socket con = server.accept();
        System.out.println("Пользователь " + con.getInetAddress()+" подключен.\nВы можете отправить ему сообщение.");// 127.0.0.1
        DataInputStream in = new DataInputStream(con.getInputStream());
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        Scanner input = new Scanner(System.in);

                Thread inputService = new Thread(() -> {
            do {
                try {
                String clientMessage = in.readUTF();
                if (clientMessage.equals(getExitWord())) {
                    isExit = false;
                    System.out.println("Shut down");
                    out.writeUTF(getExitWord());
                    out.flush();
                }

                else System.out.println("Сlient's msg: " + clientMessage); }
                catch (IOException e) {
                e.printStackTrace();
            }
                } while (isExit);

        });

        inputService.start();

        while (isExit) {
            String message = input.nextLine();
            out.writeUTF(message);
            out.flush();
        }
    }
}


// оставил для себя.
// Пример посыла файла из вебинара 6 Java2.
      //if (clientMessage.equals("send")){
//                String name = in.readUTF();
//                System.out.println("fileName = " + name);
//                int len = in.readInt();
//                System.out.println("len = " + len);
//                File file = new File(name);
//                file.createNewFile();
//                FileOutputStream os = new FileOutputStream(file);
//                for (int i = 0; i < len; i++) {
//                    os.write(in.read());
//                }
//                os.close();
//                out.writeUTF("File uploaded!");
//                out.flush();
//            }