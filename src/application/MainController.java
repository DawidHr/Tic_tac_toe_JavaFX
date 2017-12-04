package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainController implements Initializable {
	@FXML
	Button button1;
	@FXML
	Button button2;
	@FXML
	Button button3;
	@FXML
	Button button4;
	@FXML
	Button button5;
	@FXML
	Button button6;
	@FXML
	Button button7;
	@FXML
	Button button8;
	@FXML
	Button button9;
	GameClassController game;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		game = new GameClassController(button1, button2, button3, button4, button5, button6,
				button7, button8, button9);
	}

	public void actionButtonSelect(Event e) {
	if(e.getSource().equals(button1)) {
		button1.setText("X");
		button1.setOnAction(null);
		game.availebleButtonsDelete(button1);
		game.setPlayerButton(button1);
	}
	else if(e.getSource().equals(button2)) {
		button2.setText("X");
		button2.setOnAction(null);
		game.availebleButtonsDelete(button2);
		game.setPlayerButton(button2);
	}
	else if(e.getSource().equals(button3)) {
		button3.setText("X");
		button3.setOnAction(null);
		game.availebleButtonsDelete(button3);
		game.setPlayerButton(button3);
	}
	else if(e.getSource().equals(button4)) {
		button4.setText("X");
		button4.setOnAction(null);
		game.availebleButtonsDelete(button4);
		game.setPlayerButton(button4);
	}
	else if(e.getSource().equals(button5)) {
		button5.setText("X");
		button5.setOnAction(null);
		game.availebleButtonsDelete(button5);
		game.setPlayerButton(button5);	
	}
	else if(e.getSource().equals(button6)) {
		button6.setText("X");
		button6.setOnAction(null);
		game.availebleButtonsDelete(button6);
		game.setPlayerButton(button6);	
	}
	else if(e.getSource().equals(button7)){
		button7.setText("X");
		button7.setOnAction(null);
		game.availebleButtonsDelete(button7);
		game.setPlayerButton(button7);	
	}
	else if(e.getSource().equals(button8)) {
		button8.setText("X");
		button8.setOnAction(null);
		game.availebleButtonsDelete(button8);
		game.setPlayerButton(button8);	
	}
	else {
		button9.setText("X");
		button9.setOnAction(null);
		game.availebleButtonsDelete(button9);
		game.setPlayerButton(button9);	
	}
	game.machinaChoosen();
	
	}
}
