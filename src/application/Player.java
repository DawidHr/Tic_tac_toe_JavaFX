package application;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class Player {
private String name;
private String printChar;
private ArrayList<Button> buttons = new ArrayList<Button>();
public Player() {
	super();
}
public Player(String name, String printChar) {
	super();
	this.name = name;
	this.printChar = printChar;
}

public void addButtons(Button theButton) {
	buttons.add(theButton);
}
public ArrayList<Button> getButtons() {
	return buttons;
}


}
