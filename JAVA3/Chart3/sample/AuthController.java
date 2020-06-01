package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class AuthController {

    public TextField login;
    public TextField pass;
    public Button ok;
    static ChartController controller=new ChartController();
    Auth auto = new Auth();


    public void auth(){
        if (this.login.getText().equals(this.pass.getText())) {
            auto.getStage().close();
            controller.threadMain.start();
        }
    }

}
