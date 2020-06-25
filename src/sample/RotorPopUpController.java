package sample;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RotorPopUpController implements Initializable {

    @FXML
    JFXComboBox leftCombo, centreCombo, rightCombo, leftComboSet, centreComboSet, rightComboSet;

    private Stage stage;

    private String leftRotorSel, centreRotorSel, rightRotorSel, leftSettingVal, centreSettingVal, rightSettingVal;

    private ObservableList<String> rotors = FXCollections.observableArrayList("I","II","III","IV","V");
    private ObservableList<String> settings = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","23","24","25","26");

    public void onButtonClick(){
        stage = (Stage) leftCombo.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leftCombo.setItems(rotors);
        leftCombo.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            try {
                setLeftRotorSel(newValue.toString());
                updateItems();
            } catch (Exception e) {
                System.out.print("");
            }
        });
        centreCombo.setItems(rotors);
        centreCombo.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            try {
                setCentreRotorSel(newValue.toString());
                updateItems();
            } catch (Exception e) {
                System.out.print("");
            }
        });
        rightCombo.setItems(rotors);
        rightCombo.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            try {
                setRightRotorSel(newValue.toString());
                updateItems();
            } catch (Exception e) {
                System.out.print("");
            }
        });
        leftComboSet.setItems(settings);
        leftComboSet.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            setLeftSettingVal(newValue.toString());
        });
        centreComboSet.setItems(settings);
        centreComboSet.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            setCentreSettingVal(newValue.toString());
        });
        rightComboSet.setItems(settings);
        rightComboSet.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            setRightSettingVal(newValue.toString());
        });
    }

    private void onSelect(Object val, JFXComboBox combo1, JFXComboBox combo2) {
        int num = 0;
        switch (val.toString()){
            case "I": num = 1;
            case "II": num = 2;
            case "III": num = 3;
            case "IV": num = 4;
            case "V": num = 5;
        }
    }

    public void initSettings(String leftRotor, String centreRotor, String rightRotor, String leftRotorSetting, String centreRotorSetting, String rightRotorSetting){
        leftCombo.setValue(leftRotor);
        centreCombo.setValue(centreRotor);
        rightCombo.setValue(rightRotor);
        leftComboSet.setValue(leftRotorSetting);
        centreComboSet.setValue(centreRotorSetting);
        rightComboSet.setValue(rightRotorSetting);
        updateItems();
    }

    public String[] getData(){
        return new String[] {getLeftRotorSel(),getCentreRotorSel(),getRightRotorSel(),getLeftSettingVal(),getCentreSettingVal(),getRightSettingVal()};
    }

    public String getLeftRotorSel() {
        return leftRotorSel;
    }

    public void setLeftRotorSel(String leftRotorSel) {
        this.leftRotorSel = leftRotorSel;
    }

    public String getCentreRotorSel() {
        return centreRotorSel;
    }

    public void setCentreRotorSel(String centreRotorSel) {
        this.centreRotorSel = centreRotorSel;
    }

    public String getRightRotorSel() {
        return rightRotorSel;
    }

    public void setRightRotorSel(String rightRotorSel) {
        this.rightRotorSel = rightRotorSel;
    }

    public String getLeftSettingVal() {
        return leftSettingVal;
    }

    public void setLeftSettingVal(String leftSettingVal) {
        this.leftSettingVal = leftSettingVal;
    }

    public String getCentreSettingVal() {
        return centreSettingVal;
    }

    public void setCentreSettingVal(String centreSettingVal) {
        this.centreSettingVal = centreSettingVal;
    }

    public String getRightSettingVal() {
        return rightSettingVal;
    }

    public void setRightSettingVal(String rightSettingVal) {
        this.rightSettingVal = rightSettingVal;
    }

    public void updateItems() {
        ObservableList<String> left = FXCollections.observableArrayList("I", "II", "III", "IV", "V");
        ObservableList<String> centre = FXCollections.observableArrayList("I", "II", "III", "IV", "V");
        ObservableList<String> right = FXCollections.observableArrayList("I", "II", "III", "IV", "V");
        left.remove(getCentreRotorSel());
        left.remove(getRightRotorSel());
        leftCombo.setItems(left);
        centre.remove(getLeftRotorSel());
        centre.remove(getRightRotorSel());
        centreCombo.setItems(centre);
        right.remove(getCentreRotorSel());
        right.remove(getLeftRotorSel());
        rightCombo.setItems(right);
    }
}
