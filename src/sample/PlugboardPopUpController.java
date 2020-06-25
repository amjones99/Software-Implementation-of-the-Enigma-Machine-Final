package sample;

import Enigma.Plugboard;
import Enigma.Wire;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PlugboardPopUpController implements Initializable {

    @FXML
    private JFXButton plugA, plugB, plugC, plugD, plugE, plugF, plugG, plugH, plugI, plugJ, plugK, plugL, plugM, plugN, plugO, plugP, plugQ, plugR, plugS, plugT, plugU, plugV, plugW, plugX, plugY, plugZ;
    private StringProperty conA = new SimpleStringProperty("");
    private StringProperty conB = new SimpleStringProperty("");
    private StringProperty conC = new SimpleStringProperty("");
    private StringProperty conD = new SimpleStringProperty("");
    private StringProperty conE = new SimpleStringProperty("");
    private StringProperty conF = new SimpleStringProperty("");
    private StringProperty conG = new SimpleStringProperty("");
    private StringProperty conH = new SimpleStringProperty("");
    private StringProperty conI = new SimpleStringProperty("");
    private StringProperty conJ = new SimpleStringProperty("");
    private StringProperty conK = new SimpleStringProperty("");
    private StringProperty conL = new SimpleStringProperty("");
    private StringProperty conM = new SimpleStringProperty("");
    private StringProperty conN = new SimpleStringProperty("");
    private StringProperty conO = new SimpleStringProperty("");
    private StringProperty conP = new SimpleStringProperty("");
    private StringProperty conQ = new SimpleStringProperty("");
    private StringProperty conR = new SimpleStringProperty("");
    private StringProperty conS = new SimpleStringProperty("");
    private StringProperty conT = new SimpleStringProperty("");
    private StringProperty conU = new SimpleStringProperty("");
    private StringProperty conV = new SimpleStringProperty("");
    private StringProperty conW = new SimpleStringProperty("");
    private StringProperty conX = new SimpleStringProperty("");
    private StringProperty conY = new SimpleStringProperty("");
    private StringProperty conZ = new SimpleStringProperty("");
    private JFXButton[] plugs;
    private StringProperty[] conns = new StringProperty[]{conA, conB, conC, conD, conE, conF, conG, conH, conI, conJ, conK, conL, conM, conN, conO, conP, conQ, conR, conS, conT, conU, conV, conW, conX, conY, conZ};

    private Plugboard plugboard;

    public Plugboard getPlugboard() {
        return plugboard;
    }

    private String currentSelected = "";

    private Stage stage;

    public void onButtonClick(){
        stage = (Stage) plugA.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plugs = new JFXButton[]{plugA, plugB, plugC, plugD, plugE, plugF, plugG, plugH, plugI, plugJ, plugK, plugL, plugM, plugN, plugO, plugP, plugQ, plugR, plugS, plugT, plugU, plugV, plugW, plugX, plugY, plugZ};
        conns = new StringProperty[]{conA, conB, conC, conD, conE, conF, conG, conH, conI, conJ, conK, conL, conM, conN, conO, conP, conQ, conR, conS, conT, conU, conV, conW, conX, conY, conZ};
    }

    public void initPlugBoard(Plugboard p){
        plugboard = p;
        formatPairs();
    }

    public String[] handlePlugClick(char ch){
        String cur = currentSelected;
        int pairNum = 0;
        int counter = 1;
        for (Wire w: plugboard.getConnections()){
            if (w == null){
                pairNum = counter;
                break;
            }
            counter += 1;
        }
        if (cur == ""){
            if (plugboard.passBoard(ch) == ch){//ch is free
                currentSelected = String.valueOf(ch);
                return new String[]{String.valueOf(ch),String.valueOf(pairNum)};
            } else {
                plugboard.removeConnection(ch);
                formatPairs();//set removed pair to default colour
                return new String[]{"!","0"};
            }
        } else {
            if (plugboard.passBoard(ch) != ch || ch == cur.charAt(0)){
                currentSelected = "";
                formatPairs();
                return new String[]{"-","0"};
            } else {
                plugboard.addConnection(new Wire(cur.charAt(0), ch));
                formatPairs();
                currentSelected = "";
                return new String[]{cur,String.valueOf(pairNum)};
            }
        }
    }
    public void formatPairs(){
        for (JFXButton plug: plugs){//set all to default
            plug.getStyleClass().clear();
            plug.getStyleClass().add("plugBoardButton");
        }
        int counter = 0;
        for (StringProperty s: conns){
            s.set("");
        }
        for (Wire w: plugboard.getConnections()){//if paired, set colour
            counter+=1;
            if (w != null) {
                plugs[w.getA() - 'A'].getStyleClass().add("pair" + counter);
                conns[w.getA() - 'A'] = new SimpleStringProperty(String.valueOf(w.getB()));
                plugs[w.getB() - 'A'].getStyleClass().add("pair" + counter);
                conns[w.getB() - 'A'] = new SimpleStringProperty(String.valueOf(w.getA()));
            }
        }
    }

    public String plugClick(char ch){
        JFXButton plug = plugs[ch - 'A'];
        String[] s = handlePlugClick(ch);
        char con = s[0].charAt(0);

        if (con == '-' || con == '!'){
            return "-";
        } else if (con == ch){
            plug.getStyleClass().add("pair" + Integer.parseInt(s[1]));
            return "";
        } else {
            return String.valueOf(con);
        }
    }

    public void clickPlugA(){
        String s = plugClick('A');
        if (s != "-") setConA(s);
    }

    public void clickPlugB(){
        String s = plugClick('B');
        if (s != "-") setConB(s);
    }

    public void clickPlugC(){
        String s = plugClick('C');
        if (s != "-") setConC(s);
    }

    public void clickPlugD(){
        String s = plugClick('D');
        if (s != "-") setConD(s);
    }

    public void clickPlugE(){
        String s = plugClick('E');
        if (s != "-") setConE(s);
    }

    public void clickPlugF(){
        String s = plugClick('F');
        if (s != "-") setConF(s);
    }

    public void clickPlugG(){
        String s = plugClick('G');
        if (s != "-") setConG(s);
    }

    public void clickPlugH(){
        String s = plugClick('H');
        if (s != "-") setConH(s);
    }

    public void clickPlugI(){
        String s = plugClick('I');
        if (s != "-") setConI(s);
    }

    public void clickPlugJ(){
        String s = plugClick('J');
        if (s != "-") setConJ(s);
    }

    public void clickPlugK(){
        String s = plugClick('K');
        if (s != "-") setConK(s);
    }

    public void clickPlugL(){
        String s = plugClick('L');
        if (s != "-") setConL(s);
    }

    public void clickPlugM(){
        String s = plugClick('M');
        if (s != "-") setConM(s);
    }

    public void clickPlugN(){
        String s = plugClick('N');
        if (s != "-") setConN(s);
    }

    public void clickPlugO(){
        String s = plugClick('O');
        if (s != "-") setConO(s);
    }

    public void clickPlugP(){
        String s = plugClick('P');
        if (s != "-") setConP(s);
    }

    public void clickPlugQ(){
        String s = plugClick('Q');
        if (s != "-") setConQ(s);
    }

    public void clickPlugR(){
        String s = plugClick('R');
        if (s != "-") setConR(s);
    }

    public void clickPlugS(){
        String s = plugClick('S');
        if (s != "-") setConS(s);
    }

    public void clickPlugT(){
        String s = plugClick('T');
        if (s != "-") setConT(s);
    }

    public void clickPlugU(){
        String s = plugClick('U');
        if (s != "-") setConU(s);
    }

    public void clickPlugV(){
        String s = plugClick('V');
        if (s != "-") setConV(s);
    }

    public void clickPlugW(){
        String s = plugClick('W');
        if (s != "-") setConW(s);
    }

    public void clickPlugX(){
        String s = plugClick('X');
        if (s != "-") setConX(s);
    }

    public void clickPlugY(){
        String s = plugClick('Y');
        if (s != "-") setConY(s);
    }

    public void clickPlugZ(){
        String s = plugClick('Z');
        if (s != "-") setConZ(s);
    }

    public String getConA() {
        return conns[0].get();
    }

    public StringProperty conAProperty() {
        return conA;
    }

    public void setConA(String conA) {
        this.conns[0].set(conA);
        this.conA.set(conA);
    }

    public String getConB() {
        return conns[1].get();
    }

    public StringProperty conBProperty() {
        return conB;
    }

    public void setConB(String conB) {
        this.conns[1].set(conB);
        this.conB.set(conB);
    }

    public String getConC() {
        return conns[2].get();
    }

    public StringProperty conCProperty() {
        return conC;
    }

    public void setConC(String conC) {
        this.conns[2].set(conC);
        this.conC.set(conC);
    }

    public String getConD() {
        return conns[3].get();
    }

    public StringProperty conDProperty() {
        return conD;
    }

    public void setConD(String conD) {
        this.conns[3].set(conD);
        this.conD.set(conD);
    }

    public String getConE() {
        return conns[4].get();
    }

    public StringProperty conEProperty() {
        return conE;
    }

    public void setConE(String conE) {
        this.conns[4].set(conE);
        this.conE.set(conE);
    }

    public String getConF() {
        return conns[5].get();
    }

    public StringProperty conFProperty() {
        return conF;
    }

    public void setConF(String conF) {
        this.conns[5].set(conF);
        this.conF.set(conF);
    }

    public String getConG() {
        return conns[6].get();
    }

    public StringProperty conGProperty() {
        return conG;
    }

    public void setConG(String conG) {
        this.conns[6].set(conG);
        this.conG.set(conG);
    }

    public String getConH() {
        return conns[7].get();
    }

    public StringProperty conHProperty() {
        return conH;
    }

    public void setConH(String conH) {
        this.conns[7].set(conH);
        this.conH.set(conH);
    }

    public String getConI() {
        return conns[8].get();
    }

    public StringProperty conIProperty() {
        return conI;
    }

    public void setConI(String conI) {
        this.conns[8].set(conI);
        this.conI.set(conI);
    }

    public String getConJ() {
        return conns[9].get();
    }

    public StringProperty conJProperty() {
        return conJ;
    }

    public void setConJ(String conJ) {
        this.conns[9].set(conJ);
        this.conJ.set(conJ);
    }

    public String getConK() {
        return conns[10].get();
    }

    public StringProperty conKProperty() {
        return conK;
    }

    public void setConK(String conK) {
        this.conns[10].set(conK);
        this.conK.set(conK);
    }

    public String getConL() {
        return conns[11].get();
    }

    public StringProperty conLProperty() {
        return conL;
    }

    public void setConL(String conL) {
        this.conns[11].set(conL);
        this.conL.set(conL);
    }

    public String getConM() {
        return conns[12].get();
    }

    public StringProperty conMProperty() {
        return conM;
    }

    public void setConM(String conM) {
        this.conns[12].set(conM);
        this.conM.set(conM);
    }

    public String getConN() {
        return conns[13].get();
    }

    public StringProperty conNProperty() {
        return conN;
    }

    public void setConN(String conN) {
        this.conns[13].set(conN);
        this.conN.set(conN);
    }

    public String getConO() {
        return conns[14].get();
    }

    public StringProperty conOProperty() {
        return conO;
    }

    public void setConO(String conO) {
        this.conns[14].set(conO);
        this.conO.set(conO);
    }

    public String getConP() {
        return conns[15].get();
    }

    public StringProperty conPProperty() {
        return conP;
    }

    public void setConP(String conP) {
        this.conns[15].set(conP);
        this.conP.set(conP);
    }

    public String getConQ() {
        return conns[16].get();
    }

    public StringProperty conQProperty() {
        return conQ;
    }

    public void setConQ(String conQ) {
        this.conns[16].set(conQ);
        this.conQ.set(conQ);
    }

    public String getConR() {
        return conns[17].get();
    }

    public StringProperty conRProperty() {
        return conR;
    }

    public void setConR(String conR) {
        this.conns[17].set(conR);
        this.conR.set(conR);
    }

    public String getConS() {
        return conns[18].get();
    }

    public StringProperty conSProperty() {
        return conS;
    }

    public void setConS(String conS) {
        this.conns[18].set(conS);
        this.conS.set(conS);
    }

    public String getConT() {
        return conns[19].get();
    }

    public StringProperty conTProperty() {
        return conT;
    }

    public void setConT(String conT) {
        this.conns[19].set(conT);
        this.conT.set(conT);
    }

    public String getConU() {
        return conns[20].get();
    }

    public StringProperty conUProperty() {
        return conU;
    }

    public void setConU(String conU) {
        this.conns[20].set(conU);
        this.conU.set(conU);
    }

    public String getConV() {
        return conns[21].get();
    }

    public StringProperty conVProperty() {
        return conV;
    }

    public void setConV(String conV) {
        this.conns[21].set(conV);
        this.conV.set(conV);
    }

    public String getConW() {
        return conns[22].get();
    }

    public StringProperty conWProperty() {
        return conW;
    }

    public void setConW(String conW) {
        this.conns[22].set(conW);
        this.conW.set(conW);
    }

    public String getConX() {
        return conns[23].get();
    }

    public StringProperty conXProperty() {
        return conX;
    }

    public void setConX(String conX) {
        this.conns[23].set(conX);
        this.conX.set(conX);
    }

    public String getConY() {
        return conns[24].get();
    }

    public StringProperty conYProperty() {
        return conY;
    }

    public void setConY(String conY) {
        this.conns[24].set(conY);
        this.conY.set(conY);
    }

    public String getConZ() {
        return conns[25].get();
    }

    public StringProperty conZProperty() {
        return conZ;
    }

    public void setConZ(String conZ) {
        this.conns[25].set(conZ);
        this.conZ.set(conZ);
    }
}
