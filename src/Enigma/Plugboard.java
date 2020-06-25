package Enigma;

public class Plugboard {
	
	private Wire[] connections;
	private int numberOfConnections;
	private String freePlugs;
	final private int maxConns = 13;
	
	public Plugboard() {
		numberOfConnections = 0;
		connections = new Wire[maxConns];
		freePlugs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	}
	
	public void setConnections(Wire[] con) {
		connections = con;
	}
	
	public Wire[] getConnections() {
		return connections;
	}

	public void addConnection(Wire w){
		int counter = 0;
		for (Wire con: connections){
			if (con == null) {
				connections[counter] = w;
				return;
			}
			counter += 1;
		}
	}

	public void removeConnection(char ch){
		int counter = 0;
		for (Wire w: connections){
			if (w != null) {
				if (w.getA() == ch || w.getB() == ch) {
					connections[counter] = null;
					System.out.println(counter);
					return;
				}
			}
			counter += 1;
		}
	}

	public char passBoard(char ch) {
		for (Wire w: connections) {
			if (w != null) {
				if (w.getA() == ch) {
					return w.getB();
				}
				if (w.getB() == ch) {
					return w.getA();
				}
			}
		}
		return ch;
	}
}
