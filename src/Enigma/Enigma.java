package Enigma;


import javafx.beans.binding.BooleanExpression;

public class Enigma {

    private Rotor right, centre, left;
    private Reflector reflector;
    private Plugboard plugboard;
    private boolean checkDoubleStep;

    public Enigma() {
        right = new Rotor("III", 1, 'A');
        centre = new Rotor("II", 1, 'A');
        left = new Rotor("I", 1, 'A');
        reflector = new Reflector();
        plugboard = new Plugboard();
        checkDoubleStep = false;
    }

    public void rotate() {
        if ((right.rotateRotor(true)) || (centre.getCurPos() == centre.getCarryNotch())){//double step
            if (centre.rotateRotor(true)) {
                left.rotateRotor(true);
            }
        }
    }

    public void rotateBack() {
        int centrePos = centre.getCurPos() - 1;
        int centreNotch = centre.getCarryNotch();
        int rightPos = right.getCurPos() - 2;
        int rightNotch = right.getCarryNotch();
        boolean doubleStep = (((centrePos % 26) == (centreNotch % 26)) && ((rightPos % 26) == (rightNotch % 26)));
        if ((right.rotateRotorBack(true)) || doubleStep){
            if (centre.rotateRotorBack(true)) {
                left.rotateRotorBack(true);
            }
        }
    }

    public void manualRotateDown(Rotor rotor) {//a -> b
        rotor.rotateRotor(false);
    }

    public void manualRotateUp(Rotor rotor) {
        rotor.rotateRotorBack(false);
    }

    public char[] getPath(char ch){
        rotate();
        char[] path = new char[10];
        path[0] = ch;//initial input
        path[1] = plugboard.passBoard(ch);//after plugboard
        path[2] = right.passRotFor(path[1]);//after 1st rotor
        path[3] = centre.passRotFor(path[2]);//after 2nd rotor
        path[4] = left.passRotFor(path[3]);//after 3rd rotor
        path[5] = reflector.passRef(path[4]);//after reflector
        path[6] = left.passRotBack(path[5]);//after 3rd rotor backwards
        path[7] = centre.passRotBack(path[6]);//after 2nd rotor backwards
        path[8] = right.passRotBack(path[7]);//after 1st rotor backwards
        path[9] = plugboard.passBoard(path[8]);//after plugboard backwards
        return path;
    }

    private char passRotors(char i) {
        char atRef = left.passRotFor(centre.passRotFor(right.passRotFor(i)));
        char pastRef = reflector.passRef(atRef);
        return right.passRotBack(centre.passRotBack(left.passRotBack(pastRef)));
    }

    public Rotor getRight() {
        return right;
    }

    public void setRight(Rotor right) {
        this.right = right;
    }

    public Rotor getCentre() {
        return centre;
    }

    public void setCentre(Rotor centre) {
        this.centre = centre;
    }

    public Rotor getLeft() {
        return left;
    }

    public void setLeft(Rotor left) {
        this.left = left;
    }

    public Reflector getReflector() {
        return reflector;
    }

    public void setReflector(Reflector reflector) {
        this.reflector = reflector;
    }

    public Plugboard getPlugboard() {
        return plugboard;
    }

    public void setPlugboard(Plugboard plugboard) {
        this.plugboard = plugboard;
    }
}