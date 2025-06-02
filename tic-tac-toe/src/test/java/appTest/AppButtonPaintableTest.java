package appTest;

import ticTacToe.component.Button;
import ticTacToe.gui.MainWindow;

public class AppButtonPaintableTest {

    public static void main(String[] args) {

        MainWindow window = new MainWindow();

        // Botão 1
        Button button1 = new Button();
        window.add(button1);
        window.addMouseListener(button1.mouseListener());
        window.addMouseMotionListener(button1.mouseMotionListener());

        // Botão 2
        Button button2 = new Button(100, 150);
        window.add(button2);
        window.addMouseListener(button2.mouseListener());
        window.addMouseMotionListener(button2.mouseMotionListener());

        // Botão 3
        Button button3 = new Button(200, 200, 50, 50);
        window.add(button3);
        window.addMouseListener(button3.mouseListener());
        window.addMouseMotionListener(button3.mouseMotionListener());
    }
}

