package appTest;

import ticTacToe.component.button.Button;
import ticTacToe.gui.MainWindow;

public class AppButtonClickTest {



	public static void main(String[] args) {
		
		MainWindow window = new MainWindow();
		Button button = new Button(100, 100, 50, 50);
		window.add(button);
		window.addMouseMotionListener(button.mouseMotionListener());
		window.addMouseListener(button.mouseListener());
		
		
		button.addButtonClickListener((event)->{
			System.out.println("Botão clicado" + event.source);
			System.out.println("Point: " + event.source.getPosition());
			System.out.println("Dimension: " + event.source.getSize());
			System.out.println("MouseButton: " + event.mouseButton.name());
		});
	}
	
}
