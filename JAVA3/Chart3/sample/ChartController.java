package sample;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class ChartController {


    public Button off, on;
    public TextField t1;
    public TextArea t2;
    public String text1 = "";
    public Label line;

    private final static String HOST = "localhost";
    private  static  Socket socket;
    private  static DataInputStream in;
    private  static DataOutputStream out;


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
                }
                t2.setText(message);
                saveText(t2);
                System.out.println(message);
            }



    } catch (Exception e) {
        e.printStackTrace();
    }
    });


    public void connection(){

        Auth auto = new Auth();
        try {
            auto.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

if (auto.getStage().isShowing() == false){
      //  threadMain.start();
        off.setDisable(false);
        on.setDisable(true);
        line.setText("Онлайн");}
    }


    public void disconnection(){

        try {
            sendMsg("_exit_");
        } catch (IOException e) {
            e.printStackTrace();
        }
        line.setText("Офлайн");
        off.setDisable(true);
        on.setDisable(false);
    }

    public void message() {
        try {
            sendMsg(t1.getText());
            this.t1.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
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

