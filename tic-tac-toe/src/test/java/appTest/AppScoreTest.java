package appTest;

import java.net.URL;

import javax.swing.ImageIcon;

import ticTacToe.gui.MainWindow;
import ticTacToe.gui.score.ScoreView;
import ticTacToe.model.score.SimpleScoreModel;

public class AppScoreTest {
	//model
	private SimpleScoreModel scoreModel;
	//view
	private ScoreView scoreView;
	private MainWindow window;
	//control
	private void play(int times) throws InterruptedException {
		for(int i=0; i<times; i++) {
			Thread.sleep(1_000);
			if(Math.random() < 0.5)
				scoreModel.incScoreO();
			else
				scoreModel.incScoreX();
		}
	}
	
	public AppScoreTest() {
		scoreModel = new SimpleScoreModel();
		scoreView = new ScoreView(100,100,80,100, scoreModel);
		URL url = AppScoreTest.class
				.getResource("../ticTacToe/images/scoreBackground.png");
		ImageIcon background = new ImageIcon(url);
		scoreView.setBackground(background);
		window = new MainWindow();
		window.add(scoreView);
	}
	
	public static void main(String args[]) throws InterruptedException {
		AppScoreTest test = new AppScoreTest();
		test.play(20);
	}
		
}