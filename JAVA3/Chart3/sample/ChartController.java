package sample;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ChartController {

    public Button off, on; // кнопки подключения, отключения
    public TextField t1; // место ввода
    public TextArea t2; //окно чата
    public String text1 = "";
    public Label line; // статус подключения
    private final static String HOST = "localhost";
    private  static  Socket socket;
    private  static DataInputStream in;
    private  static DataOutputStream out;
    int cnt =0; //счетчик

    Thread threadMain = new Thread(()->{

        try { socket = new Socket(HOST, 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            boolean running = true;

            while (running) {
                String message = null;
                try {
                    message = in.readUTF();
                    if (message.equals("_exit_")) {
                        in.close();
                        out.close();
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    running = false;
                }
                t2.setText(message);
                saveText(t2);
                System.out.println(message);
            }
        } catch (Exception e) {
            t2.setText("Ошибка подключения");
            e.printStackTrace();
        }
    });

    public void connection() { //метод подключения

        threadMain.start(); //запускаем поток
        off.setDisable(false);
        on.setDisable(true);
        line.setText("Онлайн");

   }

    public void disconnection() throws ClassNotFoundException, SQLException, IOException { // метод отключения
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/lsmn/IdeaProjects/Java3_0520/loginpass");
        Statement stmt = connection.createStatement();
        // передаем статус в базу = OFF
        stmt.executeUpdate("update logpass set Line = 'off' where Login =" + "'"+ Chart.stage.getTitle().substring(Chart.titlelong)+"'"); // тут наш логин
        sendMsg("_exit_"); // для выхода
        line.setText("Офлайн");
        off.setDisable(true);
        on.setDisable(false);
        stmt.close(); // закрытие базы
        Platform.exit();
    }

    public void message() throws IOException { // метод посыла сообщения
        try {
            // пока по-другому не придумал
        if(socket.isConnected()) { //если есть соединение
            if (cnt++==0){ // и счетчик =0, который сразу увеличивается, так нужно только для первого сообщения
                sendMsg("/mynickis" + Chart.stage.getTitle().substring(Chart.titlelong)); // посылаем свой логин для обработки в ClientHandler
            }
        }
            sendMsg(t1.getText()); // обычный посыл сообщения
            this.t1.clear();
        } catch (NullPointerException e){
            t2.setText("Нет подключения");
            System.out.println("Нет подключения");
        }
    }

    private void saveText (@NotNull TextArea ta){
        text1 = ta.getText()+ "\n \n" + text1;
        ta.setText(text1);
    }

    private static void sendMsg(String msg) throws IOException {
        out.writeUTF(msg);
        out.flush();
    }
}

