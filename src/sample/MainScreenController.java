package sample;
import Enigma.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    private Enigma enigma;
    private StringProperty leftPos = new SimpleStringProperty("");
    private StringProperty cenPos  = new SimpleStringProperty("");
    private StringProperty rightPos  = new SimpleStringProperty("");
    private StringProperty encryptedChar  = new SimpleStringProperty("");
    private StringProperty inputBox = new SimpleStringProperty("");
    private StringProperty outputBox = new SimpleStringProperty("");

    private StringProperty enc1 = new SimpleStringProperty("");
    private StringProperty enc2 = new SimpleStringProperty("");
    private StringProperty enc3 = new SimpleStringProperty("");
    private StringProperty enc4 = new SimpleStringProperty("");
    private StringProperty enc5 = new SimpleStringProperty("");
    private StringProperty enc6 = new SimpleStringProperty("");
    private StringProperty enc7 = new SimpleStringProperty("");
    private StringProperty enc8 = new SimpleStringProperty("");
    private StringProperty enc9 = new SimpleStringProperty("");

    private String pressedKey = "Free";
    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    //Inside rotor displays
    private StringProperty leftPosUp1 = new SimpleStringProperty("");
    private StringProperty leftPosUp2 = new SimpleStringProperty("");
    private StringProperty leftPosUp3 = new SimpleStringProperty("");
    private StringProperty leftPosUp4 = new SimpleStringProperty("");
    private StringProperty leftPosDown1 = new SimpleStringProperty("");
    private StringProperty leftPosDown2 = new SimpleStringProperty("");
    private StringProperty leftPosDown3 = new SimpleStringProperty("");
    private StringProperty leftPosDown4 = new SimpleStringProperty("");
    private StringProperty cenPosUp1 = new SimpleStringProperty("");
    private StringProperty cenPosUp2 = new SimpleStringProperty("");
    private StringProperty cenPosUp3 = new SimpleStringProperty("");
    private StringProperty cenPosUp4 = new SimpleStringProperty("");
    private StringProperty cenPosDown1 = new SimpleStringProperty("");
    private StringProperty cenPosDown2 = new SimpleStringProperty("");
    private StringProperty cenPosDown3 = new SimpleStringProperty("");
    private StringProperty cenPosDown4 = new SimpleStringProperty("");
    private StringProperty rightPosUp1 = new SimpleStringProperty("");
    private StringProperty rightPosUp2 = new SimpleStringProperty("");
    private StringProperty rightPosUp3 = new SimpleStringProperty("");
    private StringProperty rightPosUp4 = new SimpleStringProperty("");
    private StringProperty rightPosDown1 = new SimpleStringProperty("");
    private StringProperty rightPosDown2 = new SimpleStringProperty("");
    private StringProperty rightPosDown3 = new SimpleStringProperty("");
    private StringProperty rightPosDown4 = new SimpleStringProperty("");

    private StringProperty leftRotorSel = new SimpleStringProperty("I");
    private StringProperty centreRotorSel = new SimpleStringProperty("II");
    private StringProperty rightRotorSel = new SimpleStringProperty("III");


    @FXML
    private Label lampA, lampB, lampC, lampD, lampE, lampF, lampG, lampH, lampI, lampJ, lampK, lampL, lampM, lampN, lampO, lampP, lampQ, lampR, lampS, lampT, lampU, lampV, lampW, lampX, lampY, lampZ;
    private Label[] lamps;

    @FXML
    private Label lampAU, lampBU, lampCU, lampDU, lampEU, lampFU, lampGU, lampHU, lampIU, lampJU, lampKU, lampLU, lampMU, lampNU, lampOU, lampPU, lampQU, lampRU, lampSU, lampTU, lampUU, lampVU, lampWU, lampXU, lampYU, lampZU;
    private Label[] lampsUnder;

    @FXML
    private Button keyA, keyB, keyC, keyD, keyE, keyF, keyG, keyH, keyI, keyJ, keyK, keyL, keyM, keyN, keyO, keyP, keyQ, keyR, keyS, keyT, keyU, keyV, keyW, keyX, keyY, keyZ;
    private Button[] keys;

    @FXML
    private AnchorPane topPanel, mainHood, encryptionPath;

    @FXML
    private JFXButton tutorial, examples;

    @FXML
    private JFXButton closePanel, closeHood;

    @FXML
    private JFXToggleButton encryptionView;

    @FXML
    private VBox menuTab;

    public void openExamplePopUp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ExamplePopUp.fxml"));
        Parent root1 = loader.load();

        Stage examplePopUp = new Stage();
        examplePopUp.setResizable(false);
        examplePopUp.setTitle("Examples");
        examplePopUp.setScene(new Scene(root1));

        examplePopUp.show();

        examples.disableProperty().bind(examplePopUp.showingProperty());
    }

    public void openTutorialPopUp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TutorialPopUp.fxml"));
        Parent root1 = loader.load();

        Stage tutorialPopUp = new Stage();
        tutorialPopUp.setResizable(false);
        tutorialPopUp.setTitle("Tutorial");
        tutorialPopUp.setScene(new Scene(root1));
        tutorialPopUp.setHeight(220);

        TutorialPopUpController controller = loader.getController();
        controller.initSettings(tutorialPopUp);

        tutorialPopUp.show();

        tutorial.disableProperty().bind(tutorialPopUp.showingProperty());
    }

    //load new popup for opening rotors
    public void openRotorPopUp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("rotorPopUp.fxml"));
        Parent root1 = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));

        RotorPopUpController controller = loader.getController();
        controller.initSettings(String.valueOf(enigma.getLeft().getRotorSelected()),String.valueOf(enigma.getCentre().getRotorSelected()),String.valueOf(enigma.getRight().getRotorSelected()),String.valueOf(enigma.getLeft().getRingSet()),String.valueOf(enigma.getCentre().getRingSet()),String.valueOf(enigma.getRight().getRingSet()));

        stage.showAndWait();

        String[] settings = controller.getData();
        if (settings[0] == enigma.getLeft().getRotorSelected()){
            enigma.getLeft().setRingSet(Integer.parseInt(settings[3]));
        } else {
            enigma.setLeft(new Rotor(settings[0],Integer.parseInt(settings[3]), getLeftPos().charAt(0)));
            setLeftRotorSel(settings[0]);
        }
        if (settings[1] == enigma.getCentre().getRotorSelected()){
            enigma.getCentre().setRingSet(Integer.parseInt(settings[4]));
        } else {
            enigma.setCentre(new Rotor(settings[1],Integer.parseInt(settings[4]), getCenPos().charAt(0)));
            setCentreRotorSel(settings[1]);
        }
        if (settings[2] == enigma.getRight().getRotorSelected()){
            enigma.getRight().setRingSet(Integer.parseInt(settings[5]));
        } else {
            enigma.setRight(new Rotor(settings[2],Integer.parseInt(settings[5]), getRightPos().charAt(0)));
            setRightRotorSel(settings[2]);
        }
    }

    //load new popup for opening rotors
    public void openPlugboardPopUp() throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("plugboardPopUp.fxml"));
        Parent root2 = loader1.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));

        PlugboardPopUpController controller = loader1.getController();
        controller.initPlugBoard(enigma.getPlugboard());

        stage.showAndWait();
        enigma.setPlugboard(controller.getPlugboard());
    }


    public void onPanelOpen(){
        topPanel.setVisible(false);
        closePanel.setVisible(true);
    }

    public void onHoodOpen(){
        mainHood.setVisible(false);
        closeHood.setVisible(true);
        encryptionView.setVisible(true);
    }

    public void closePanel(){
        topPanel.setVisible(true);
        closePanel.setVisible(false);
    }

    public void closeHood(){
        mainHood.setVisible(true);
        closeHood.setVisible(false);
        encryptionView.setVisible(false);
        encryptionView.setSelected(false);
        encryptionPath.setVisible(false);
    }

    public void keyPress(KeyEvent key){
        String chPressed = key.getText().toUpperCase();
        if (getPressedKey() == "Free") {
            if (isAlpha(chPressed)) {
                onKeyClick(chPressed.charAt(0));
                setPressedKey(chPressed);
            } else if (key.getCode() == KeyCode.BACK_SPACE) {
                backBtnClick();
                setPressedKey("Back");
            }
        }
    }

    public void keyRelease(KeyEvent key) {
        String chReleased = key.getText().toUpperCase();
        if (isAlpha(chReleased)) {
            if (chReleased.charAt(0) == getPressedKey().charAt(0)) {
                for (Label l : lamps) {
                    l.getStyleClass().clear();
                    l.getStyleClass().add("lampBoard");
                }
                for (Label lU: lampsUnder){
                    lU.getStyleClass().clear();
                    lU.getStyleClass().add("lampBoardUnder");
                }
                for (Button k: keys){
                    k.getStyleClass().clear();
                    k.getStyleClass().add("keyBoard");
                }
                setPressedKey("Free");
            }
        } else if (key.getCode() == KeyCode.BACK_SPACE) {
            if (getPressedKey() == "Back") {
                setPressedKey("Free");
            }
        }
    }

    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    public void clearBtnClick(){
        //could have confirm pop up here
        setInputBox("");
        setOutputBox("");
    }

    public void backBtnClick(){
        String inMsg = getInputBox();
        if (inMsg.length() == 1){
            clearBtnClick();
            enigma.rotateBack();
            updateRotPosImage();
        } else if (inMsg.length() != 0) {
            enigma.rotateBack();
            updateRotPosImage();
            inMsg = inMsg.substring(0, inMsg.length() - 1);
            String outMsg = getOutputBox();

            if (inMsg.charAt(inMsg.length() - 1) == ' '){
                setInputBox(inMsg.substring(0, inMsg.length() - 1));
                setOutputBox(outMsg.substring(0, outMsg.length() - 2));
            } else {
                setInputBox(inMsg);
                setOutputBox(outMsg.substring(0, outMsg.length() - 1));
            }
        }
    }

    public void menuBtnClick(){
        menuTab.setVisible(!menuTab.isVisible());
    }

    private void onKeyClick(char ch){
        char[] encArray = enigma.getPath(ch);
        String enc = String.valueOf(encArray[9]);
        setPressedKey("Click");
        updateRotPosImage();
        String inMsg = getInputBox();
        String msg = getOutputBox();
        if ((inMsg.length() + 1) % 6 == 0){
            inMsg += " ";
            msg += " ";
        }
        setInputBox(inMsg + ch);
        setOutputBox(msg + enc);
        for (Label l: lamps){
            if (l.getText().charAt(0) == enc.charAt(0)){
                l.getStyleClass().add("lampOn");
            }
        }
        for (Label lU: lampsUnder){
            if (lU.getId().charAt(4) == enc.charAt(0)){
                lU.getStyleClass().add("lampOnUnder");
            }
        }
        for (Button k: keys){
            if (k.getText().charAt(0) == ch){
                k.getStyleClass().add("pressedKeyBoard");
            }
        }
        if (encryptionView.isSelected()){
            updateEncryptionPath(encArray);
            encryptionPath.setVisible(true);
        }


        //change look of light bulb and key
    }

    public void toggleView(){
        if (!encryptionView.isSelected()){
            encryptionPath.setVisible(false);
        }
    }

    public void onKeyClickA(){
        if (getPressedKey() == "Free"){
            onKeyClick('A');
        }
    }

    public void onKeyClickB(){
        if (getPressedKey() == "Free"){
            onKeyClick('B');
        }
    }
    public void onKeyClickC(){
        if (getPressedKey() == "Free"){
            onKeyClick('C');
        }
    }
    public void onKeyClickD(){
        if (getPressedKey() == "Free"){
            onKeyClick('D');
        }
    }
    public void onKeyClickE(){
        if (getPressedKey() == "Free"){
            onKeyClick('E');
        }
    }
    public void onKeyClickF(){
        if (getPressedKey() == "Free"){
            onKeyClick('F');
        }
    }
    public void onKeyClickG(){
        if (getPressedKey() == "Free"){
            onKeyClick('G');
        }
    }
    public void onKeyClickH(){
        if (getPressedKey() == "Free"){
            onKeyClick('H');
        }
    }
    public void onKeyClickI(){
        if (getPressedKey() == "Free"){
            onKeyClick('I');
        }
    }
    public void onKeyClickJ(){
        if (getPressedKey() == "Free"){
            onKeyClick('J');
        }
    }
    public void onKeyClickK(){
        if (getPressedKey() == "Free"){
            onKeyClick('K');
        }
    }
    public void onKeyClickL(){
        if (getPressedKey() == "Free"){
            onKeyClick('L');
        }
    }
    public void onKeyClickM(){
        if (getPressedKey() == "Free"){
            onKeyClick('M');
        }
    }
    public void onKeyClickN(){
        if (getPressedKey() == "Free"){
            onKeyClick('N');
        }
    }
    public void onKeyClickO(){
        if (getPressedKey() == "Free"){
            onKeyClick('O');
        }
    }
    public void onKeyClickP(){
        if (getPressedKey() == "Free"){
            onKeyClick('P');
        }
    }
    public void onKeyClickQ(){
        if (getPressedKey() == "Free"){
            onKeyClick('Q');
        }
    }
    public void onKeyClickR(){
        if (getPressedKey() == "Free"){
            onKeyClick('R');
        }
    }
    public void onKeyClickS(){
        if (getPressedKey() == "Free"){
            onKeyClick('S');
        }
    }
    public void onKeyClickT(){
        if (getPressedKey() == "Free"){
            onKeyClick('T');
        }
    }
    public void onKeyClickU(){
        if (getPressedKey() == "Free"){
            onKeyClick('U');
        }
    }
    public void onKeyClickV(){
        if (getPressedKey() == "Free"){
            onKeyClick('V');
        }
    }
    public void onKeyClickW(){
        if (getPressedKey() == "Free"){
            onKeyClick('W');
        }
    }
    public void onKeyClickX(){
        if (getPressedKey() == "Free"){
            onKeyClick('X');
        }
    }
    public void onKeyClickY(){
        if (getPressedKey() == "Free"){
            onKeyClick('Y');
        }
    }
    public void onKeyClickZ(){
        if (getPressedKey() == "Free"){
            onKeyClick('Z');
        }
    }

    public void onKeyRelease(){
        for (Label l: lamps){
            l.getStyleClass().clear();
            l.getStyleClass().add("lampBoard");
        }
        for (Label lU: lampsUnder){
            lU.getStyleClass().clear();
            lU.getStyleClass().add("lampBoardUnder");
        }
        for (Button k: keys){
            k.getStyleClass().clear();
            k.getStyleClass().add("keyBoard");
        }
        setPressedKey("Free");
    }

    public void leftRotorDownButtonAction(){
        enigma.manualRotateDown(enigma.getLeft());
        updateRotPosImage();
    }

    public void centreRotorDownButtonAction(){
        enigma.manualRotateDown(enigma.getCentre());
        updateRotPosImage();
    }

    public void rightRotorDownButtonAction(){
        enigma.manualRotateDown(enigma.getRight());
        updateRotPosImage();
    }

    public void leftRotorUpButtonAction(){
        enigma.manualRotateUp(enigma.getLeft());
        updateRotPosImage();
    }

    public void centreRotorUpButtonAction(){
        enigma.manualRotateUp(enigma.getCentre());
        updateRotPosImage();
    }

    public void rightRotorUpButtonAction(){
        enigma.manualRotateUp(enigma.getRight());
        updateRotPosImage();
    }

    private void updateRotPosImage(){
        char left = enigma.getLeft().getCurPos();
        char cen = enigma.getCentre().getCurPos();
        char right = enigma.getRight().getCurPos();
        setLeftPos(String.valueOf(left));
        setCenPos(String.valueOf(cen));
        setRightPos(String.valueOf(right));
        updateInnerRotorImage(left, cen, right);
    }

    private void updateEncryptionPath(char[] path){
        setEnc1(String.valueOf(path[1]));
        setEnc2(String.valueOf(path[2]));
        setEnc3(String.valueOf(path[3]));
        setEnc4(String.valueOf(path[4]));
        setEnc5(String.valueOf(path[5]));
        setEnc6(String.valueOf(path[6]));
        setEnc7(String.valueOf(path[7]));
        setEnc8(String.valueOf(path[8]));
        setEnc9(String.valueOf(path[9]));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enigma = new Enigma();
        updateRotPosImage();
        keys = new Button[]{keyA, keyB, keyC, keyD, keyE, keyF, keyG, keyH, keyI, keyJ, keyK, keyL, keyM, keyN, keyO, keyP, keyQ, keyR, keyS, keyT, keyU, keyV, keyW, keyX, keyY, keyZ};
        lamps = new Label[]{lampA, lampB, lampC, lampD, lampE, lampF, lampG, lampH, lampI, lampJ, lampK, lampL, lampM, lampN, lampO, lampP, lampQ, lampR, lampS, lampT, lampU, lampV, lampW, lampX, lampY, lampZ};
        lampsUnder = new Label[]{lampAU, lampBU, lampCU, lampDU, lampEU, lampFU, lampGU, lampHU, lampIU, lampJU, lampKU, lampLU, lampMU, lampNU, lampOU, lampPU, lampQU, lampRU, lampSU, lampTU, lampUU, lampVU, lampWU, lampXU, lampYU, lampZU};
    }

    public String getLeftPos() {
        return leftPos.get();
    }

    public StringProperty leftPosProperty() {
        return leftPos;
    }

    public void setLeftPos(String leftPos) {
        this.leftPos.set(leftPos);
    }

    public String getCenPos() {
        return cenPos.get();
    }

    public StringProperty cenPosProperty() {
        return cenPos;
    }

    public void setCenPos(String cenPos) {
        this.cenPos.set(cenPos);
    }

    public String getRightPos() {
        return rightPos.get();
    }

    public StringProperty rightPosProperty() {
        return rightPos;
    }

    public void setRightPos(String rightPos) {
        this.rightPos.set(rightPos);
    }

    public String getEncryptedChar() {
        return encryptedChar.get();
    }

    public StringProperty encryptedCharProperty() {
        return encryptedChar;
    }

    public void setEncryptedChar(String encryptedChar) {
        this.encryptedChar.set(encryptedChar);
    }

    public String getInputBox() {
        return inputBox.get();
    }

    public StringProperty inputBoxProperty() {
        return inputBox;
    }

    public void setInputBox(String inputBox) {
        this.inputBox.set(inputBox);
    }

    public String getOutputBox() {
        return outputBox.get();
    }

    public StringProperty outputBoxProperty() {
        return outputBox;
    }

    public void setOutputBox(String outputBox) {
        this.outputBox.set(outputBox);
    }

    public String getPressedKey() {
        return pressedKey;
    }

    public void setPressedKey(String pressedKey) {
        this.pressedKey = pressedKey;
    }

    public String getLeftPosUp1() {
        return leftPosUp1.get();
    }

    public StringProperty leftPosUp1Property() {
        return leftPosUp1;
    }

    public void setLeftPosUp1(String leftPosUp1) {
        this.leftPosUp1.set(leftPosUp1);
    }

    public String getLeftPosUp2() {
        return leftPosUp2.get();
    }

    public StringProperty leftPosUp2Property() {
        return leftPosUp2;
    }

    public void setLeftPosUp2(String leftPosUp2) {
        this.leftPosUp2.set(leftPosUp2);
    }

    public String getLeftPosUp3() {
        return leftPosUp3.get();
    }

    public StringProperty leftPosUp3Property() {
        return leftPosUp3;
    }

    public void setLeftPosUp3(String leftPosUp3) {
        this.leftPosUp3.set(leftPosUp3);
    }

    public String getLeftPosUp4() {
        return leftPosUp4.get();
    }

    public StringProperty leftPosUp4Property() {
        return leftPosUp4;
    }

    public void setLeftPosUp4(String leftPosUp4) {
        this.leftPosUp4.set(leftPosUp4);
    }

    public String getLeftPosDown1() {
        return leftPosDown1.get();
    }

    public StringProperty leftPosDown1Property() {
        return leftPosDown1;
    }

    public void setLeftPosDown1(String leftPosDown1) {
        this.leftPosDown1.set(leftPosDown1);
    }

    public String getLeftPosDown2() {
        return leftPosDown2.get();
    }

    public StringProperty leftPosDown2Property() {
        return leftPosDown2;
    }

    public void setLeftPosDown2(String leftPosDown2) {
        this.leftPosDown2.set(leftPosDown2);
    }

    public String getLeftPosDown3() {
        return leftPosDown3.get();
    }

    public StringProperty leftPosDown3Property() {
        return leftPosDown3;
    }

    public void setLeftPosDown3(String leftPosDown3) {
        this.leftPosDown3.set(leftPosDown3);
    }

    public String getLeftPosDown4() {
        return leftPosDown4.get();
    }

    public StringProperty leftPosDown4Property() {
        return leftPosDown4;
    }

    public void setLeftPosDown4(String leftPosDown4) {
        this.leftPosDown4.set(leftPosDown4);
    }

    public String getCenPosUp1() {
        return cenPosUp1.get();
    }

    public StringProperty cenPosUp1Property() {
        return cenPosUp1;
    }

    public void setCenPosUp1(String cenPosUp1) {
        this.cenPosUp1.set(cenPosUp1);
    }

    public String getCenPosUp2() {
        return cenPosUp2.get();
    }

    public StringProperty cenPosUp2Property() {
        return cenPosUp2;
    }

    public void setCenPosUp2(String cenPosUp2) {
        this.cenPosUp2.set(cenPosUp2);
    }

    public String getCenPosUp3() {
        return cenPosUp3.get();
    }

    public StringProperty cenPosUp3Property() {
        return cenPosUp3;
    }

    public void setCenPosUp3(String cenPosUp3) {
        this.cenPosUp3.set(cenPosUp3);
    }

    public String getCenPosUp4() {
        return cenPosUp4.get();
    }

    public StringProperty cenPosUp4Property() {
        return cenPosUp4;
    }

    public void setCenPosUp4(String cenPosUp4) {
        this.cenPosUp4.set(cenPosUp4);
    }

    public String getCenPosDown1() {
        return cenPosDown1.get();
    }

    public StringProperty cenPosDown1Property() {
        return cenPosDown1;
    }

    public void setCenPosDown1(String cenPosDown1) {
        this.cenPosDown1.set(cenPosDown1);
    }

    public String getCenPosDown2() {
        return cenPosDown2.get();
    }

    public StringProperty cenPosDown2Property() {
        return cenPosDown2;
    }

    public void setCenPosDown2(String cenPosDown2) {
        this.cenPosDown2.set(cenPosDown2);
    }

    public String getCenPosDown3() {
        return cenPosDown3.get();
    }

    public StringProperty cenPosDown3Property() {
        return cenPosDown3;
    }

    public void setCenPosDown3(String cenPosDown3) {
        this.cenPosDown3.set(cenPosDown3);
    }

    public String getCenPosDown4() {
        return cenPosDown4.get();
    }

    public StringProperty cenPosDown4Property() {
        return cenPosDown4;
    }

    public void setCenPosDown4(String cenPosDown4) {
        this.cenPosDown4.set(cenPosDown4);
    }

    public String getRightPosUp1() {
        return rightPosUp1.get();
    }

    public StringProperty rightPosUp1Property() {
        return rightPosUp1;
    }

    public void setRightPosUp1(String rightPosUp1) {
        this.rightPosUp1.set(rightPosUp1);
    }

    public String getRightPosUp2() {
        return rightPosUp2.get();
    }

    public StringProperty rightPosUp2Property() {
        return rightPosUp2;
    }

    public void setRightPosUp2(String rightPosUp2) {
        this.rightPosUp2.set(rightPosUp2);
    }

    public String getRightPosUp3() {
        return rightPosUp3.get();
    }

    public StringProperty rightPosUp3Property() {
        return rightPosUp3;
    }

    public void setRightPosUp3(String rightPosUp3) {
        this.rightPosUp3.set(rightPosUp3);
    }

    public String getRightPosUp4() {
        return rightPosUp4.get();
    }

    public StringProperty rightPosUp4Property() {
        return rightPosUp4;
    }

    public void setRightPosUp4(String rightPosUp4) {
        this.rightPosUp4.set(rightPosUp4);
    }

    public String getRightPosDown1() {
        return rightPosDown1.get();
    }

    public StringProperty rightPosDown1Property() {
        return rightPosDown1;
    }

    public void setRightPosDown1(String rightPosDown1) {
        this.rightPosDown1.set(rightPosDown1);
    }

    public String getRightPosDown2() {
        return rightPosDown2.get();
    }

    public StringProperty rightPosDown2Property() {
        return rightPosDown2;
    }

    public void setRightPosDown2(String rightPosDown2) {
        this.rightPosDown2.set(rightPosDown2);
    }

    public String getRightPosDown3() {
        return rightPosDown3.get();
    }

    public StringProperty rightPosDown3Property() {
        return rightPosDown3;
    }

    public void setRightPosDown3(String rightPosDown3) {
        this.rightPosDown3.set(rightPosDown3);
    }

    public String getRightPosDown4() {
        return rightPosDown4.get();
    }

    public StringProperty rightPosDown4Property() {
        return rightPosDown4;
    }

    public void setRightPosDown4(String rightPosDown4) {
        this.rightPosDown4.set(rightPosDown4);
    }

    public void updateInnerRotorImage(char l, char c, char r){
        setRightPosUp4(findChar(r, 4));
        setRightPosUp3(findChar(r, 3));
        setRightPosUp2(findChar(r, 2));
        setRightPosUp1(findChar(r, 1));
        setRightPosDown1(findChar(r, -1));
        setRightPosDown2(findChar(r, -2));
        setRightPosDown3(findChar(r, -3));
        setRightPosDown4(findChar(r, -4));
        setCenPosUp4(findChar(c, 4));
        setCenPosUp3(findChar(c, 3));
        setCenPosUp2(findChar(c, 2));
        setCenPosUp1(findChar(c, 1));
        setCenPosDown1(findChar(c, -1));
        setCenPosDown2(findChar(c, -2));
        setCenPosDown3(findChar(c, -3));
        setCenPosDown4(findChar(c, -4));
        setLeftPosUp4(findChar(l, 4));
        setLeftPosUp3(findChar(l, 3));
        setLeftPosUp2(findChar(l, 2));
        setLeftPosUp1(findChar(l, 1));
        setLeftPosDown1(findChar(l, -1));
        setLeftPosDown2(findChar(l, -2));
        setLeftPosDown3(findChar(l, -3));
        setLeftPosDown4(findChar(l, -4));
    }

    public String findChar(char rotorPos, int n){
        int asciiNum = (int)rotorPos;
        if ((asciiNum + n) < 65) {
            return String.valueOf((char)(asciiNum + n + 26));
        } else if ((asciiNum + n) > 90) {
            return String.valueOf((char)(asciiNum + n - 26));
        } else {
            return String.valueOf((char)(asciiNum + n));
        }
    }

    public String getLeftRotorSel() {
        return leftRotorSel.get();
    }

    public StringProperty leftRotorSelProperty() {
        return leftRotorSel;
    }

    public void setLeftRotorSel(String leftRotorSel) {
        this.leftRotorSel.set(leftRotorSel);
    }

    public String getCentreRotorSel() {
        return centreRotorSel.get();
    }

    public StringProperty centreRotorSelProperty() {
        return centreRotorSel;
    }

    public void setCentreRotorSel(String centreRotorSel) {
        this.centreRotorSel.set(centreRotorSel);
    }

    public String getRightRotorSel() {
        return rightRotorSel.get();
    }

    public StringProperty rightRotorSelProperty() {
        return rightRotorSel;
    }

    public void setRightRotorSel(String rightRotorSel) {
        this.rightRotorSel.set(rightRotorSel);
    }

    public String getEnc1() {
        return enc1.get();
    }

    public StringProperty enc1Property() {
        return enc1;
    }

    public void setEnc1(String enc1) {
        this.enc1.set(enc1);
    }

    public String getEnc2() {
        return enc2.get();
    }

    public StringProperty enc2Property() {
        return enc2;
    }

    public void setEnc2(String enc2) {
        this.enc2.set(enc2);
    }

    public String getEnc3() {
        return enc3.get();
    }

    public StringProperty enc3Property() {
        return enc3;
    }

    public void setEnc3(String enc3) {
        this.enc3.set(enc3);
    }

    public String getEnc4() {
        return enc4.get();
    }

    public StringProperty enc4Property() {
        return enc4;
    }

    public void setEnc4(String enc4) {
        this.enc4.set(enc4);
    }

    public String getEnc5() {
        return enc5.get();
    }

    public StringProperty enc5Property() {
        return enc5;
    }

    public void setEnc5(String enc5) {
        this.enc5.set(enc5);
    }

    public String getEnc6() {
        return enc6.get();
    }

    public StringProperty enc6Property() {
        return enc6;
    }

    public void setEnc6(String enc6) {
        this.enc6.set(enc6);
    }

    public String getEnc7() {
        return enc7.get();
    }

    public StringProperty enc7Property() {
        return enc7;
    }

    public void setEnc7(String enc7) {
        this.enc7.set(enc7);
    }

    public String getEnc8() {
        return enc8.get();
    }

    public StringProperty enc8Property() {
        return enc8;
    }

    public void setEnc8(String enc8) {
        this.enc8.set(enc8);
    }

    public String getEnc9() {
        return enc9.get();
    }

    public StringProperty enc9Property() {
        return enc9;
    }

    public void setEnc9(String enc9) {
        this.enc9.set(enc9);
    }
}
