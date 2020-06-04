package sample;

import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;

public class AuthController {

    public Label info; // просто метка информации
    public TextField login; //логин
    public TextField pass; //пароль
    public Button ok;
    public CheckBox auth; // выбор авторизации
    public CheckBox regist; // или регистрации
    Auth auto = new Auth();

    public void labelName(){ //метод переключения метки
        if (auth.isSelected())info.setText("Авторизация");
        else info.setText("Регистрация");
    }

    public void authChoice(){ //метод переключения галки для авторизации
        if (auth.isSelected()) regist.setSelected(false);
        else regist.setSelected(true);

    }

    public void registChoice(){ // также метод переключения галки для регистрации
        if (regist.isSelected()) auth.setSelected(false);
        else auth.setSelected(true);
    }

    public void auth() throws Exception { // метод авторизации или регистрации
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/lsmn/IdeaProjects/Java3_0520/loginpass");
        Statement stmt = connection.createStatement();

        if (auth.isSelected()){ // если галка на авторизации
        ResultSet rs = stmt.executeQuery("select Pass from logpass where Login =" + "'"+this.login.getText()+"'"); // ищем пароль для логина
         if (rs.isClosed()){ // если такого логина
            info.setText("Bad login");
         }
            else { //если логин всё-таки есть
                if (rs.getString(1).equals(this.pass.getText())) { // и если пароль совпадает
                    ResultSet rsline = stmt.executeQuery("select  Line from logpass where Login =" + "'"+this.login.getText()+"'"); // смотрим статус
                    if (rsline.getString(1).equals("off")){ // если статус = "оff"
                        stmt.executeUpdate("update logpass set Line = 'on' where Login=" + "'"+this.login.getText()+"'"); // ставим его = "оn"
                       // ResultSet rslogin = stmt.executeQuery("select  UserID from logpass where Login =" + "'"+this.login.getText()+"'");//игнорировать
                        auto.getStage().close(); // закрываем сцену авторизации
                        Chart chart = new Chart(); // сцена чата
                        chart.start(new Stage(), login.getText()); // передаем сцену и строку в перегруженный метод start() в классе Chart
                    }
                    else info.setText("User is already joined"); // если статус уже = "on"
                } else info.setText("Bad password");  // если пароль не подошел к логину
            }
        } else { // в случае регистрации
            if (login.getText().length()<4) {info.setText("Введите логин не менее 4 символов");
            } //  короткий логин
            else if (pass.getText().isEmpty() || pass.getText().length()<6) {info.setText("Пароль должен быть больше 5 символов");
            } //кортокий пароль
            else {
                ResultSet rs = stmt.executeQuery("select Login from logpass where Login =" + "'"+this.login.getText()+"'");//ищем реальные логины
//                String l = "'" + this.login.getText() + "'";
//                String p = "'" + this.pass.getText() + "'";
                if (!rs.isClosed()) { // если нашлись
                    info.setText("Такой логин уже существует!");
                } else { // если нет
                    stmt.executeUpdate("insert into logpass(Login, Pass) values ( '','')" ); // вносим новую запись и аналогично авторизации:
                    auto.getStage().close(); // закрываем окно
                    Chart chart = new Chart(); // сцена чата
                    chart.start(new Stage(), login.getText()); // передаем сцену и строку в start()
                }
            }
        } stmt.close(); // закрываем базу
    }
}


