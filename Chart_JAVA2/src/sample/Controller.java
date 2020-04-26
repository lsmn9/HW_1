package sample;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

 public TextField t1;
 public TextArea t2 = new TextArea();
 public  String text1 = "";

 public void saveText (TextArea ta){
     text1 = ta.getText()+ "\n \n" + text1;
    ta.setText(text1);
 }

 public void message() {
        t2.setText(t1.getText());
        t1.setText("");
        saveText(t2);
    }
}

