package Server;

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
       // welcome();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void welcome() throws IOException {
        out.writeUTF("Hello, write you message :)");
        out.flush();
    }
    // метод общего сообщения
    public void broadCastMessage(String message) throws IOException {
        for (ClientHandler client : Server.getClients()) {
            if (client.equals(this)) {
            client.sendMessage( "You: " + message);
            }
            else client.sendMessage( getNickName()+": " + message);
        }
    }

// метод личного сообщения
    public void privateMsg(String message) throws IOException {
        for (ClientHandler client : Server.getClients()){   // проходим по пользователям
            if (client.equals(this))client.sendMessage( "You->" + message.substring("/w".length()));
            if (message.startsWith("/w" + client.getNickName())){ // если находит /w + существующий клиент
                client.sendMessage(getNickName()+"**:"                   // тогда этому клиенту в личку с пометкой **
                + message.substring ("/w".length()+ client.getNickName().length()));
            } // номер индекса,чтобы шаблон отправки не дублировались
        }
    }

    private void sendMessage( String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }

    @Override
    public void run() {
        while (running) {
            try {
                if (socket.isConnected()) {
                    String clientMessage = in.readUTF();
                    // устанавливаем ник
                    if (clientMessage.startsWith("/mynickis")){
                        String nick = clientMessage.substring("/mynickis".length());
                        clientMessage = "Hello! I'm online!";
                        setNickName(nick);
                    }
                    // выходим и сообщаем об этом
                    if (clientMessage.equals("_exit_")) {
                        Server.getClients().remove(this);
                        sendMessage(clientMessage);
                        System.out.println(getNickName() +" покинул чат!\n"); // в консоль
                        broadCastMessage(getNickName() +" покинул чат!");  // другим клиентам
                        break;
                    }
                    // личное сообщение
                    if (clientMessage.startsWith("/w")){ // обозначил таким образом if
                        privateMsg(clientMessage); // в случае совпадения вызывает метод и печатает в личку
                        }
                    else {System.out.println(nickName+": "+clientMessage +"\n"); // если нет, то в общий чат
                    broadCastMessage(clientMessage);
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}