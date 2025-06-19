package appTest;


import java.net.URL;

import javax.swing.ImageIcon;


import ticTacToe.gui.MainWindow;
import ticTacToe.gui.TableView;

public class AppBackgroundTest {
	
	static ImageIcon loadImage(String pathWithFileName) {
		URL url = AppBackgroundTest.class.getResource(pathWithFileName);
		if (url != null) {
            return new ImageIcon(url);
        } else {
            System.err.println("Imagem não encontrada: " + pathWithFileName);
            return null;
        }
	}
	
	public static void main(String[] args) {
		
		final String path = "../ticTacToe/images/";
		
		MainWindow window = new MainWindow();
		
		ImageIcon icon = loadImage(path + "background.jpg");
		window.setBackground(icon);
		
		
		icon = loadImage(path + "tic-tac-toe.png");
		TableView table = new TableView(50, 50, 200, 200, icon);
		window.add(table);
		
		}
}