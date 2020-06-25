package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TutorialPopUpController implements Initializable {

    @FXML
    private AnchorPane AppFeatures, ClosedHood, OpenHood, EnigmaEnc, Rotors, Plug, Deciphering, mainScene;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initSettings(Stage s){
        stage  = s;
    }

    public void toHome(){
        AppFeatures.setVisible(false);
        ClosedHood.setVisible(false);
        OpenHood.setVisible(false);
        EnigmaEnc.setVisible(false);
        Rotors.setVisible(false);
        Plug.setVisible(false);
        Deciphering.setVisible(false);
        stage.setHeight(220);
        mainScene.setPrefHeight(200);
    }

    public void toAppFeatures(){
        AppFeatures.setVisible(true);
        ClosedHood.setVisible(false);
        OpenHood.setVisible(false);
        EnigmaEnc.setVisible(false);
        Rotors.setVisible(false);
        Plug.setVisible(false);
        Deciphering.setVisible(false);
        stage.setHeight(220);
        mainScene.setPrefHeight(200);
    }

    public void toClosedHood(){
        AppFeatures.setVisible(true);
        ClosedHood.setVisible(true);
        OpenHood.setVisible(false);
        EnigmaEnc.setVisible(false);
        Rotors.setVisible(false);
        Plug.setVisible(false);
        Deciphering.setVisible(false);
        stage.setHeight(700);
        mainScene.setPrefHeight(960);
    }

    public void toOpenHood(){
        AppFeatures.setVisible(true);
        ClosedHood.setVisible(false);
        OpenHood.setVisible(true);
        EnigmaEnc.setVisible(false);
        Rotors.setVisible(false);
        Plug.setVisible(false);
        Deciphering.setVisible(false);
        stage.setHeight(700);
        mainScene.setPrefHeight(900);
    }

    public void toEnigmaEnc(){
        AppFeatures.setVisible(false);
        ClosedHood.setVisible(false);
        OpenHood.setVisible(false);
        EnigmaEnc.setVisible(true);
        Rotors.setVisible(false);
        Plug.setVisible(false);
        Deciphering.setVisible(false);
        stage.setHeight(700);
        mainScene.setPrefHeight(970);
    }

    public void toRotors(){
        AppFeatures.setVisible(false);
        ClosedHood.setVisible(false);
        OpenHood.setVisible(false);
        EnigmaEnc.setVisible(true);
        Rotors.setVisible(true);
        Plug.setVisible(false);
        Deciphering.setVisible(false);
        stage.setHeight(700);
        mainScene.setPrefHeight(910);
    }

    public void toPlug(){
        AppFeatures.setVisible(false);
        ClosedHood.setVisible(false);
        OpenHood.setVisible(false);
        EnigmaEnc.setVisible(true);
        Rotors.setVisible(false);
        Plug.setVisible(true);
        Deciphering.setVisible(false);
        stage.setHeight(520);
        mainScene.setPrefHeight(510);
    }

    public void toDeciphering(){
        AppFeatures.setVisible(false);
        ClosedHood.setVisible(false);
        OpenHood.setVisible(false);
        EnigmaEnc.setVisible(false);
        Rotors.setVisible(false);
        Plug.setVisible(false);
        Deciphering.setVisible(true);
        stage.setHeight(700);
        mainScene.setPrefHeight(990);
    }
}
