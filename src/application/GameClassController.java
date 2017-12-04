package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.sun.media.jfxmedia.events.PlayerStateListener;

import javafx.scene.control.Button;

public class GameClassController {
	// Player1 to gracz
	Player player1;
	// Player2 to komputer
	Player player2;

	Button button1 = new Button();;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;
	Button button8;
	Button button9;
	int start = 1;
	ArrayList<Button> avButtons = new ArrayList<>();

	public GameClassController(Button button1, Button button2, Button button3, Button button4, Button button5,
			Button button6, Button button7, Button button8, Button button9) {
		super();
		this.button1 = button1;
		this.button2 = button2;
		this.button3 = button3;
		this.button4 = button4;
		this.button5 = button5;
		this.button6 = button6;
		this.button7 = button7;
		this.button8 = button8;
		this.button9 = button9;
		avButtons.add(button1);
		avButtons.add(button2);
		avButtons.add(button3);
		avButtons.add(button4);
		avButtons.add(button5);
		avButtons.add(button6);
		avButtons.add(button7);
		avButtons.add(button8);
		avButtons.add(button9);
		game();

	}

	public void game() {
		// Tworzenie graczy
		createPlayers();
		// Wybranie Kto zacznie gre
		start = whoStartFirst();
		if (start == 2)
			return;
		else
			machinaChoosen();
	}

	private void createPlayers() {
		player1 = new Player("Gracz", "X");
		player2 = new Player("Komputer", "O");
	}

	public int whoStartFirst() {
		Random randomChoose = new Random();
		int number = randomChoose.nextInt(100);
		if (number < 50) {
			System.out.println("Zaczyna u¿ytkownik");
			return 2;
		} else {
			System.out.println("Zaczyna maszyna");
			return 1;
		}
	}

	public void machinaChoosen() {
		// Sprawdzanie czy gracz wygra³ gre
		if (ifIWin(player1.getButtons())) {
			WinMethod("Gracz");
			System.out.println("Gracz wygrywa");
		} else {
			// Pobieranie list
			ArrayList<Button> listOfMachineButtons = player2.getButtons();
			System.out.println(listOfMachineButtons.size());

			if (listOfMachineButtons.size() == 0 || listOfMachineButtons == null) {
				// Maszyna wybiera 1 raz
				listOfMachineButtons.add(selectFirstTheBestButton());
				start++;
			} else {
				// System.out.println("Next");
				start++;
				Button theButton = searchBestChanceToWin();
				theButton.setText("O");
				listOfMachineButtons.add(theButton);
				availebleButtonsDelete(theButton);
				if (ifIWin(listOfMachineButtons)) {
					// metoda wygrania
					WinMethod("Maszyna");
					System.out.println("Maszyna wygrywa");
				}
			}
			// Sprawdz czy mozna wygraæ 1 ruchem
			// Sprawdz czy u¿ytkownik mo¿e wygraæ 1 ruchem
			// Wybierz najlepsze pole
		}
	}

	private Button selectFirstTheBestButton() {
		Random random = new Random();
		do {
			int number = random.nextInt(5);
			switch (number) {
			case 0: {
				boolean isOK = availebleButtons(button1);
				if (isOK) {
					button1.setText("O");
					button1.setOnAction(null);
					availebleButtonsDelete(button1);
					return button1;
				}
			}
			case 1: {
				boolean isOK = availebleButtons(button3);
				if (isOK) {
					button3.setText("O");
					availebleButtonsDelete(button3);
					button3.setOnAction(null);
					return button3;
				}
			}
			case 2: {
				boolean isOK = availebleButtons(button5);
				if (isOK) {
					button5.setText("O");
					availebleButtonsDelete(button5);
					button5.setOnAction(null);
					return button5;
				}
			}
			case 3: {
				boolean isOK = availebleButtons(button7);
				if (isOK) {
					button7.setText("O");
					availebleButtonsDelete(button7);
					button7.setOnAction(null);
					return button7;
				}
			}
			case 4: {
				boolean isOK = availebleButtons(button9);
				if (isOK) {
					button9.setText("O");
					availebleButtonsDelete(button9);
					button9.setOnAction(null);
					return button9;
				}

			}
			default: {
				boolean isOK = availebleButtons(button5);
				if (isOK) {
					button5.setText("O");
					button5.setOnAction(null);
					availebleButtonsDelete(button5);
					return button5;
				}
			}
			}
		} while (true);
	}

	public void setPlayerButton(Button button12) {
		player1.addButtons(button12);

	}

	public boolean availebleButtons(Button theButton) {
		boolean av = false;
		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(theButton)) {
				av = true;
				break;
			}
		}
		return av;
	}

	public void availebleButtonsDelete(Button theButton) {
		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(theButton)) {
				// System.out.println("Kasowanie przycisku: " + theButton);
				avButtons.remove(i);
				break;
			}
		}
	}

	public Button searchBestChanceToWin() {
		ArrayList<Button> player2Buttons = player2.getButtons();
		// Sprawdzanie czy maszyna wygra 1 ruchem
		Button theButton = searchIfCanWinorBlock(player2Buttons);
		if (theButton != null) {
			return theButton;
		}
		// Sprawdzanie czy przeciwnik mo¿e wygrac 1 ruchem
		Button theButton2 = searchIfCanWinorBlock(player1.getButtons());
		if (theButton2 != null) {
			return theButton2;
		}
		int intbutton1 = 0;
		int intbutton2 = 0;
		int intbutton3 = 0;
		int intbutton4 = 0;
		int intbutton5 = 0;
		int intbutton6 = 0;
		int intbutton7 = 0;
		int intbutton8 = 0;
		int intbutton9 = 0;
		System.out.println("Size: " + avButtons.size());
		for (int i = 0; i < avButtons.size(); i++) {
			Button b = avButtons.get(i);
			int number = bb(b);
			switch (number) {
			case 1: {
				System.out.println("Jedynka dostepna");
				intbutton1 = button1chanceToWin();
				break;
			}
			case 2: {
				System.out.println("2 dostepna");
				intbutton2 = button2chanceToWin();
				break;
			}
			case 3: {
				System.out.println("3 dostepna");
				intbutton3 = button3chanceToWin();
				break;
			}
			case 4: {
				System.out.println("4 dostepna");
				intbutton4 = button4chanceToWin();
				break;
			}
			case 5: {
				System.out.println("5 dostepna");
				intbutton5 = button5chanceToWin();
				break;
			}
			case 6: {
				System.out.println("6 dostepna");
				intbutton6 = button6chanceToWin();
				break;
			}
			case 7: {
				System.out.println("7 dostepna");
				intbutton7 = button7chanceToWin();
				break;
			}
			case 8: {
				System.out.println("8 dostepna");
				intbutton8 = button8chanceToWin();
				break;
			}
			default: {
				System.out.println("9 dostepna");
				intbutton9 = button9chanceToWin();
			}
			}
		}
		printAvButtons();
		System.out.println("Liczpa punktow: ");
		System.out.println("1: " + intbutton1);
		System.out.println("2: " + intbutton2);
		System.out.println("3: " + intbutton3);
		System.out.println("4: " + intbutton4);
		System.out.println("5: " + intbutton5);
		System.out.println("6: " + intbutton6);
		System.out.println("7: " + intbutton7);
		System.out.println("8: " + intbutton8);
		System.out.println("9: " + intbutton9);
		System.out.println("/n/n");

		// Proba
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(intbutton1);
		list.add(intbutton2);
		list.add(intbutton3);
		list.add(intbutton4);
		list.add(intbutton5);
		list.add(intbutton6);
		list.add(intbutton7);
		list.add(intbutton8);
		list.add(intbutton9);
		Collections.sort(list);

		if (list.get(8).equals(intbutton1))
			return button1;
		else if (list.get(8).equals(intbutton2))
			return button2;
		else if (list.get(8).equals(intbutton3))
			return button3;
		else if (list.get(8).equals(intbutton4))
			return button4;
		else if (list.get(8).equals(intbutton5))
			return button5;
		else if (list.get(8).equals(intbutton6))
			return button6;
		else if (list.get(8).equals(intbutton7))
			return button7;
		else if (list.get(8).equals(intbutton8))
			return button8;
		else
			return button9;

	}

	public int bb(Button b) {
		int i = 0;
		if (b.equals(button1))
			i = 1;
		else if (b.equals(button2))
			i = 2;
		else if (b.equals(button3))
			i = 3;
		else if (b.equals(button4))
			i = 4;
		else if (b.equals(button5))
			i = 5;
		else if (b.equals(button6))
			i = 6;
		else if (b.equals(button7))
			i = 7;
		else if (b.equals(button8))
			i = 8;
		else
			i = 9;
		return i;
	}

	// Proba dla button1
	public int button1chanceToWin() {
		ArrayList<Button> playeList = player2.getButtons();

		// Piewczy mo¿liwosæ
		int chanceToWin = 99;
		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(button2))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button3))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button5))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button9))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button4))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button7))
				chanceToWin = chanceToWin + 33;
			else {

			}

		}
		for (int i = 0; i < playeList.size(); i++) {
			if (playeList.get(i).equals(button2))
				chanceToWin = chanceToWin + 33;
			else if (playeList.get(i).equals(button3))
				chanceToWin = chanceToWin + 33;
			else if (playeList.get(i).equals(button5))
				chanceToWin = chanceToWin + 33;
			else if (playeList.get(i).equals(button9))
				chanceToWin = chanceToWin + 33;
			else if (playeList.get(i).equals(button4))
				chanceToWin = chanceToWin + 33;
			else if (playeList.get(i).equals(button7))
				chanceToWin = chanceToWin + 33;
			else {

			}
		}
		for (int j = 0; j < playeList.size(); j++) {
			for (int i = 0; i < playeList.size(); i++) {
				if (playeList.get(i).equals(button2) && (playeList.get(j).equals(button3)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button5) && (playeList.get(j).equals(button9)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button4) && (playeList.get(j).equals(button7)))
					chanceToWin = 1000;
				else {

				}
			}
		}
		return chanceToWin;
		// return chance to win
	}

	public int button2chanceToWin() {
		ArrayList<Button> playeList = player2.getButtons();
		int chanceToWin = 66;

		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(button1))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button3))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button5))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button8))
				chanceToWin = chanceToWin + 33;
			else {

			}

		}
		for (int i = 0; i < playeList.size(); i++) {
			if (playeList.get(i).equals(button1))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button3))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button5))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button8))
				chanceToWin = chanceToWin + 66;
			else {

			}
		}
		for (int j = 0; j < playeList.size(); j++) {
			for (int i = 0; i < playeList.size(); i++) {
				if (playeList.get(i).equals(button1) && (playeList.get(j).equals(button3)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button5) && (playeList.get(j).equals(button8)))
					chanceToWin = 1000;
				else {

				}
			}
		}
		return chanceToWin;
	}

	public int button3chanceToWin() {
		ArrayList<Button> playeList = player2.getButtons();
		int chanceToWin = 99;

		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(button1))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button2))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button5))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button6))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button7))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button9))
				chanceToWin = chanceToWin + 33;
			else {

			}

		}
		for (int i = 0; i < playeList.size(); i++) {
			if (playeList.get(i).equals(button1))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button2))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button5))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button6))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button7))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button9))
				chanceToWin = chanceToWin + 66;
			else {

			}
		}
		for (int j = 0; j < playeList.size(); j++) {
			for (int i = 0; i < playeList.size(); i++) {
				if (playeList.get(i).equals(button1) && (playeList.get(j).equals(button2)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button5) && (playeList.get(j).equals(button7)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button6) && (playeList.get(j).equals(button9)))
					chanceToWin = 1000;
				else {

				}
			}
		}
		return chanceToWin;
	}

	public int button4chanceToWin() {
		ArrayList<Button> playeList = player2.getButtons();
		int chanceToWin = 66;

		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(button1))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button7))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button5))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button6))
				chanceToWin = chanceToWin + 33;
			else {

			}

		}
		for (int i = 0; i < playeList.size(); i++) {
			if (playeList.get(i).equals(button1))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button7))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button5))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button6))
				chanceToWin = chanceToWin + 66;
			else {

			}
		}
		for (int j = 0; j < playeList.size(); j++) {
			for (int i = 0; i < playeList.size(); i++) {
				if (playeList.get(i).equals(button1) && (playeList.get(j).equals(button7)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button5) && (playeList.get(j).equals(button6)))
					chanceToWin = 1000;
				else {

				}
			}
		}
		return chanceToWin;
	}

	public int button5chanceToWin() {
		ArrayList<Button> playeList = player2.getButtons();
		int chanceToWin = 132;

		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(button1))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button2))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button3))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button4))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button6))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button7))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button8))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button9))
				chanceToWin = chanceToWin + 33;
			else {

			}

		}
		for (int i = 0; i < playeList.size(); i++) {
			if (playeList.get(i).equals(button1))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button2))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button3))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button4))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button6))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button7))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button8))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button9))
				chanceToWin = chanceToWin + 66;
			else {

			}
		}
		for (int j = 0; j < playeList.size(); j++) {
			for (int i = 0; i < playeList.size(); i++) {
				if (playeList.get(i).equals(button2) && (playeList.get(i).equals(button8)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button4) && (playeList.get(i).equals(button6)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button1) && (playeList.get(i).equals(button9)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button3) && (playeList.get(i).equals(button7)))
					chanceToWin = 1000;
				else {

				}
			}
		}
		return chanceToWin;
	}

	public int button6chanceToWin() {
		ArrayList<Button> playeList = player2.getButtons();
		int chanceToWin = 66;

		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(button3))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button2))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button4))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button5))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button9))
				chanceToWin = chanceToWin + 33;
			else {

			}

		}
		for (int i = 0; i < playeList.size(); i++) {
			if (playeList.get(i).equals(button3))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button2))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button4))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button5))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button9))
				chanceToWin = chanceToWin + 66;
			else {

			}
		}
		for (int j = 0; j < playeList.size(); j++) {
			for (int i = 0; i < playeList.size(); i++) {
				if (playeList.get(i).equals(button3) && (playeList.get(j).equals(button9)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button4) && (playeList.get(j).equals(button5)))
					chanceToWin = 1000;
				else {

				}
			}
		}
		return chanceToWin;
	}

	public int button7chanceToWin() {
		ArrayList<Button> playeList = player2.getButtons();
		int chanceToWin = 99;

		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(button1))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button4))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button3))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button5))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button9))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button8))
				chanceToWin = chanceToWin + 33;
			else {

			}

		}
		for (int i = 0; i < playeList.size(); i++) {
			if (playeList.get(i).equals(button1))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button4))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button3))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button5))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button9))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button8))
				chanceToWin = chanceToWin + 66;
			else {

			}
		}
		for (int j = 0; j < playeList.size(); j++) {
			for (int i = 0; i < playeList.size(); i++) {
				if (playeList.get(i).equals(button1) && (playeList.get(j).equals(button4)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button3) && (playeList.get(j).equals(button5)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button8) && (playeList.get(j).equals(button9)))
					chanceToWin = 1000;
				else {

				}
			}
		}
		return chanceToWin;
	}

	public int button8chanceToWin() {
		ArrayList<Button> playeList = player2.getButtons();
		int chanceToWin = 66;

		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(button2))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button5))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button7))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button9))
				chanceToWin = chanceToWin + 33;
			else {

			}

		}
		for (int i = 0; i < playeList.size(); i++) {
			if (playeList.get(i).equals(button2))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button5))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button7))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button9))
				chanceToWin = chanceToWin + 66;
			else {

			}
		}
		for (int j = 0; j < playeList.size(); j++) {
			for (int i = 0; i < playeList.size(); i++) {
				if (playeList.get(i).equals(button2) && (playeList.get(j).equals(button5)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button7) && (playeList.get(j).equals(button9)))
					chanceToWin = 1000;
				else {

				}
			}
		}
		return chanceToWin;
	}

	public int button9chanceToWin() {
		ArrayList<Button> playeList = player2.getButtons();
		int chanceToWin = 99;

		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(button1))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button5))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button3))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button6))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button7))
				chanceToWin = chanceToWin + 33;
			else if (avButtons.get(i).equals(button8))
				chanceToWin = chanceToWin + 33;
			else {

			}

		}
		for (int i = 0; i < playeList.size(); i++) {
			if (playeList.get(i).equals(button1))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button5))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button3))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button6))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button7))
				chanceToWin = chanceToWin + 66;
			else if (playeList.get(i).equals(button8))
				chanceToWin = chanceToWin + 66;
			else {

			}
		}
		for (int j = 0; j < playeList.size(); j++) {
			for (int i = 0; i < playeList.size(); i++) {
				if (playeList.get(i).equals(button3) && (playeList.get(j).equals(button6)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button1) && (playeList.get(j).equals(button5)))
					chanceToWin = 1000;
				else if (playeList.get(i).equals(button7) && (playeList.get(j).equals(button8)))
					chanceToWin = 1000;
				else {

				}
			}
		}
		return chanceToWin;
	}

	public boolean ifIWin(ArrayList<Button> theButton) {
		ArrayList<Button> list = theButton;
		// czy wygral
		boolean ifWin = false;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				for (int k = 0; k < list.size(); k++) {
					if (list.get(i).equals(button1) && list.get(j).equals(button2) && list.get(k).equals(button3))
						ifWin = true;
					else if (list.get(i).equals(button1) && list.get(j).equals(button5) && list.get(k).equals(button9))
						ifWin = true;
					else if (list.get(i).equals(button1) && list.get(j).equals(button4) && list.get(k).equals(button7))
						ifWin = true;
					else if (list.get(i).equals(button2) && list.get(j).equals(button5) && list.get(k).equals(button8))
						ifWin = true;
					else if (list.get(i).equals(button3) && list.get(j).equals(button5) && list.get(k).equals(button7))
						ifWin = true;
					else if (list.get(i).equals(button3) && list.get(j).equals(button6) && list.get(k).equals(button9))
						ifWin = true;
					else if (list.get(i).equals(button4) && list.get(j).equals(button5) && list.get(k).equals(button6))
						ifWin = true;
					else if (list.get(i).equals(button7) && list.get(j).equals(button8) && list.get(k).equals(button9))
						ifWin = true;
					else {

					}
				}
			}
		}
		return ifWin;
	}

	public void WinMethod(String player) {
		winWindow w = new winWindow();
		w.showWinWindow();
	}

	public void printAvButtons() {
		for (int i = 0; i < avButtons.size(); i++) {
			System.out.println(avButtons.get(i));
		}
	}

	// Sprawdzanie czy mo¿na wygraæ 1 ruchem lub czy przeciwnik moze wygrac 1 ruchem
	public Button searchIfCanWinorBlock(ArrayList<Button> list) {
		ArrayList<Button> playeList = list;
		for (int i = 0; i < playeList.size(); i++) {
			for (int j = 0; j < playeList.size(); j++) {
				if (playeList.get(i).equals(button1) && playeList.get(j).equals(button2)) {
					if (isAvaileble(button3))
						return button3;
				} else if (playeList.get(i).equals(button1) && playeList.get(j).equals(button3)) {
					if (isAvaileble(button2))
						return button2;
				} else if (playeList.get(i).equals(button1) && playeList.get(j).equals(button5)) {
					if (isAvaileble(button9))
						return button9;
				} else if (playeList.get(i).equals(button1) && playeList.get(j).equals(button9)) {
					if (isAvaileble(button5))
						return button5;
				} else if (playeList.get(i).equals(button1) && playeList.get(j).equals(button4)) {
					if (isAvaileble(button7))
						return button7;
				} else if (playeList.get(i).equals(button1) && playeList.get(j).equals(button7)) {
					if (isAvaileble(button4))
						return button4;
				} else if (playeList.get(i).equals(button2) && playeList.get(j).equals(button3)) {
					if (isAvaileble(button1))
						return button1;
				} else if (playeList.get(i).equals(button5) && playeList.get(j).equals(button9)) {
					if (isAvaileble(button1))
						return button1;
				} else if (playeList.get(i).equals(button4) && playeList.get(j).equals(button7)) {
					if (isAvaileble(button1))
						return button1;
				} else if (playeList.get(i).equals(button2) && playeList.get(j).equals(button5)) {
					if (isAvaileble(button8))
						return button8;
				} else if (playeList.get(i).equals(button2) && playeList.get(j).equals(button8)) {
					if (isAvaileble(button5))
						return button5;
				} else if (playeList.get(i).equals(button5) && playeList.get(j).equals(button8)) {
					if (isAvaileble(button2))
						return button2;
				} else if (playeList.get(i).equals(button3) && playeList.get(j).equals(button5)) {
					if (isAvaileble(button7))
						return button7;
				} else if (playeList.get(i).equals(button3) && playeList.get(j).equals(button7)) {
					if (isAvaileble(button5))
						return button5;
				} else if (playeList.get(i).equals(button3) && playeList.get(j).equals(button6)) {
					if (isAvaileble(button9))
						return button9;
				} else if (playeList.get(i).equals(button3) && playeList.get(j).equals(button9)) {
					if (isAvaileble(button6))
						return button6;
				} else if (playeList.get(i).equals(button5) && playeList.get(j).equals(button7)) {
					if (isAvaileble(button3))
						return button3;
				} else if (playeList.get(i).equals(button6) && playeList.get(j).equals(button9)) {
					if (isAvaileble(button3))
						return button3;
				} else if (playeList.get(i).equals(button4) && playeList.get(j).equals(button5)) {
					if (isAvaileble(button6))
						return button6;
				} else if (playeList.get(i).equals(button4) && playeList.get(j).equals(button6)) {
					if (isAvaileble(button5))
						return button5;
				} else if (playeList.get(i).equals(button5) && playeList.get(j).equals(button6)) {
					if (isAvaileble(button4))
						return button4;
				} else if (playeList.get(i).equals(button7) && playeList.get(j).equals(button8)) {
					if (isAvaileble(button9))
						return button9;
				} else if (playeList.get(i).equals(button7) && playeList.get(j).equals(button9)) {
					if (isAvaileble(button8))
						return button8;
				} else if (playeList.get(i).equals(button8) && playeList.get(j).equals(button9)) {
					if (isAvaileble(button7))
						return button7;
				} else {

				}
			}
		}
		return null;
	}

	public boolean isAvaileble(Button theButton) {
		for (int i = 0; i < avButtons.size(); i++) {
			if (avButtons.get(i).equals(theButton)) {
				return true;
			} else {

			}
		}
		return false;
	}

}
