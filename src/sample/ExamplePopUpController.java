package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ExamplePopUpController implements Initializable {

    @FXML
    private JFXTextArea input1, input2, input3, input4;

    @FXML
    private AnchorPane page1, page2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void checkExample1(){
        String input = input1.getText().toUpperCase();
        if (input.equals("HELLO WORLD")){
            input1.setStyle("-fx-text-fill: green;");
        } else {
            input1.setStyle("-fx-text-fill: red;");
        }
    }

    public void checkExample2(){
        String input = input2.getText().toUpperCase();
        if (input.equals("THE SUBMARINE IS PATROLLING THE ENGLISH CHANNEL")){
            input2.setStyle("-fx-text-fill: green;");
        } else {
            input2.setStyle("-fx-text-fill: red;");
        }
    }

    public void checkExample3(){
        String input = input3.getText().toUpperCase();
        if (input.equals("THE BRITISH JUST WONT SURRENDER")  || input.equals("THE BRITISH JUST WON'T SURRENDER")){
            input3.setStyle("-fx-text-fill: green;");
        } else {
            input3.setStyle("-fx-text-fill: red;");
        }
    }

    public void checkExample4(){
        String input = input4.getText().toUpperCase();
        if (input.equals("TOMORROW MORNING WE WILL SEND THE TANKS INTO PARIS")){
            input4.setStyle("-fx-text-fill: green;");
        } else {
            input4.setStyle("-fx-text-fill: red;");
        }
    }

    public void nextExamplesPage(){
        page2.setVisible(true);
        page1.setVisible(false);
    }

    public void previousExamplesPage(){
        page1.setVisible(true);
        page2.setVisible(false);
    }

}
