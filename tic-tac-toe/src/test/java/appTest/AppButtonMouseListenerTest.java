package appTest;

import ticTacToe.component.button.Button;
import ticTacToe.gui.MainWindow;

public class AppButtonMouseListenerTest {
	
	public static void main(String[] args) {
		
		MainWindow window = new MainWindow();
		Button button = new Button(250, 100, 50, 50);
		window.add(button);
		window.addMouseListener(button.mouseListener());
	}

}
