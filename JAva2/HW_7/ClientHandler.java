package HW_7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickName;
    private boolean running;

    public ClientHandler(Socket socket, String nickName) throws IOException {
        this.socket = socket;
        this.nickName = nickName;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        running = true;
        welcome();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void welcome() throws IOException {
        out.writeUTF("Hello, " + nickName);
        out.flush();
    }

    public void broadCastMessage(String message) throws IOException {
        for (ClientHandler client : Server.getClients()) {
            if (!client.equals(this)) {
            client.sendMessage( getNickName()+": " + message);
            }
        }
    }

    public void sendMessage( String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }
// ------------------------метод для сообщения в личку ----------------------------
    public void privateMsg(String message) throws IOException {
        for (ClientHandler client : Server.getClients()){   // проходим по пользователям
            if (message.startsWith("/w " + client.getNickName())){ // если находит /w + существующий клиент
        client.sendMessage(getNickName()+"**:"                   // тогда этому клиенту в личку с пометкой **
                + message.substring ("/w ".length()+ client.getNickName().length())+"\n");
            } // номер индекса,чтобы шаблон отправки не дубль.
        }
    }
//---------------------------------------------------------------------------------------------------
    @Override
    public void run() {
        while (running) {
            try {
                if (socket.isConnected()) {
                    String clientMessage = in.readUTF();
                    if (clientMessage.equals("_exit_")) {
                        Server.getClients().remove(this);
                        sendMessage(clientMessage);
                        System.out.println(getNickName() +" покинул чат!\n"); // добавил в случае выхода из чата
                        break;
                    }
                    if (clientMessage.startsWith("/w")){ // обозначил таким образом if
                        privateMsg(clientMessage); // в случае совпадения вызывает метод и печатает в личку
                        }
                    else {System.out.println(nickName+": "+clientMessage +"\n"); // если нет, то в общий чат
                  //  broadCastMessage(clientMessage); // закомитил чтобы ОБЩИЕ сообщения шли ТОЛЬКО в общий чат
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}