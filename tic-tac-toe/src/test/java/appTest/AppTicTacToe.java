package appTest;

import java.net.URL;

import javax.swing.ImageIcon;

import ticTacToe.component.button.TextButton;
import ticTacToe.gui.MainWindow;
import ticTacToe.gui.TableView;
import ticTacToe.gui.score.ScoreView;
import ticTacToe.model.Mark;
import ticTacToe.control.*;
import ticTacToe.model.score.ScoreModel;
import ticTacToe.model.score.SimpleScoreModel;
import ticTacToe.model.table.SimpleTableModel;
import ticTacToe.model.table.TableModel;


public class AppTicTacToe {
	
	//Model
	TableModel tableModel = new SimpleTableModel();
	ScoreModel scoreModel = new SimpleScoreModel();
	   
    //View
	MainWindow mainWindow;
	TableView tableView;
	ScoreView scoreView;
	
	//Control
	GameControl gameControl;
	TextButton btnNewGame;
	
	private ImageIcon loadImage(String fileName)
	{
	   String path = "../ticTacToe/images/";
       URL url = getClass().getResource(path + fileName);
       return new ImageIcon(url);
    }

	private void createGameControl(){
		
		VirtualPlayer virtualPLayer = new SimpleVirtualPlayer(tableModel, Mark.O);
		 gameControl = new GameControl(tableModel, scoreModel, virtualPLayer);
	}
	
	private void createMainWindow()
	{
		ImageIcon icon = loadImage("background.jpg");
		mainWindow = new MainWindow();
		mainWindow.setBackground(icon);
	}
	
	private void createTableView()
    {
		ImageIcon icon = loadImage("tic-tac-toe.png");
		tableView = new TableView(50,50,200,200,icon);
		tableView.setTableModel(tableModel);

		icon = loadImage("markX.png");
		tableView.setIconX(icon);

		icon = loadImage("markO.png");
		tableView.setIconO(icon);

		
		tableView.addCellClickListener(gameControl.cellClickListener());
		mainWindow.add(tableView);
		mainWindow.addMouseMotionListener(tableView.mouseMotionListener());
		mainWindow.addMouseListener(tableView.mouseListener());
   }

	private void createScoreView()
	{	
		ImageIcon icon = loadImage("scoreBackground.png");
		scoreView = new ScoreView(300, 50, 100, 100, scoreModel);
		scoreView.setBackground(icon);
		mainWindow.add(scoreView);
	}
	
	private void createButtonNewGame()
	{
		btnNewGame = new TextButton( 300, 250, 100, 50);
		btnNewGame.setText("New Game");
		btnNewGame.addButtonClickListener(gameControl.newGameButtonClickListener());
		mainWindow.add(btnNewGame);
		mainWindow.addMouseListener(btnNewGame.mouseListener());
		mainWindow.addMouseMotionListener(btnNewGame.mouseMotionListener());
	}
	
	public AppTicTacToe() 
	{
		createGameControl();
		createMainWindow();
		createTableView();
		createScoreView();
		createButtonNewGame();
	}
	   
	public static void main(String[] args) throws Exception {
	   AppTicTacToe app = new AppTicTacToe();
    }
}

