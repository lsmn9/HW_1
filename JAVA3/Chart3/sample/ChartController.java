package sample;

import javafx.application.Platform;
import javafx.scene.control.*;

import java.io.*;
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
    int cnt =0; //счетчик для некоторых действий

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
                if (cnt==1){ // cnt станет =1, после запуска, это ниже
                    saveText(t2); //чтобы история не пропала
                    cnt++; // чтобы повтороно не заходить
                }
                t2.appendText(message+"\n");
                System.out.println(message);
                // запись истории в файл = логин.txt
                File file = new File("src/History/"+Chart.stage.getTitle().substring(Chart.titlelong)+"history.txt");
                file.createNewFile(); // если файла нет - создаем его
                try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))){
                    writer.println(" "+message); //пробел дабвил для реализации чтения кириллицы из истории
                }
            }
        } catch (Exception e) {
            t2.setText("Ошибка подключения");
            e.printStackTrace();
        }
    });

    public void connection() throws IOException { //метод подключения

        // при подключении выводим историю
        String path = "src/History/"+Chart.stage.getTitle().substring(Chart.titlelong)+"history.txt"; // путь файла
        File file = new File(path);
        if (file.exists() & file.length()>0){ // если такой файл существует(то есть есть история)
            try (RandomAccessFile random1 = new RandomAccessFile(path,"r")) {
                String history="";
                int bytes = 0;
                int lines = 0; // общее количество строк
                final int LINES = 5; // сколько строк с конца выводить
                while (bytes != -1) { // пока есть, что считывать
                    random1.readLine(); // читаем строчку
                    lines++; // после прочтения +1 строка
                    bytes = random1.read(); // проверяем есть ли байты еще
                }
                random1.close();
                try (RandomAccessFile random2 = new RandomAccessFile(path, "r")){
                    bytes = 0; // обнуляем
                    int position =0; // внутренний счетчик
                    while (bytes!=-1){
                        byte [] k = random2.readLine().getBytes("ISO-8859-1"); //считываем строку
                        position++;
                        bytes = random2.read();
                        if (lines <= LINES){ //если строк меньше необходимых
                            history = history + new String(k)+"\n"; //выводим всю историю
                        }else{
                            if (position > (lines-LINES)){ //если больше необходимых
                                history = history + new String(k)+"\n"; // только часть со строки position
                            }
                        }
                    }
                    random2.close();
                    t2.setText(history); // выводим на экран
                }
            }
        }
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
            if (cnt==0){ // и счетчик =0, который сразу увеличивается, так как нужно только для первого сообщения
                sendMsg("/mynickis" + Chart.stage.getTitle().substring(Chart.titlelong)); // посылаем свой логин для обработки в ClientHandler
                cnt++; // сnt = 1;
            }
        }
            sendMsg(t1.getText()); // обычный посыл сообщения
            this.t1.clear();
        } catch (NullPointerException e){
            t2.setText("Нет подключения");
            System.out.println("Нет подключения");
        }
    }

    private void saveText (TextArea ta){
        text1 = text1 + ta.getText();
        ta.setText(text1);
    }

    private void sendMsg(String msg) throws IOException {
        out.writeUTF(msg);
        out.flush();
    }


}

