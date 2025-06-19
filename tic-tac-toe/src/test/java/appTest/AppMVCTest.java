package appTest;
import ticTacToe.model.Mark;
import ticTacToe.model.table.*;

import java.net.URL;

import javax.swing.ImageIcon;

import ticTacToe.gui.*;

public class AppMVCTest {
	
	TableModel tableModel = new SimpleTableModel();
	
	MainWindow window = new MainWindow();
	
	TableView tableView = createTableView();
	
	private ImageIcon loadImage(String fileName) {
		String path = "../ticTacToe/images/";
		URL url = getClass().getResource(path + fileName);
		return new ImageIcon(url);
	}
	
	private TableView createTableView() {
		ImageIcon icon = loadImage("tic-tac-toe.png");
		TableView table = new TableView(50, 50, 200, 200, icon);
		
		icon = loadImage("markX.png");
		table.setIconX(icon);
		
		icon = loadImage("markO.png");
		table.setIconO(icon);
		
		return table;
		
	}
	
	public AppMVCTest() {
		
		tableView.setTableModel(tableModel);
		tableView.addCellClickListener((cell)->
			tableModel.setMark(cell.lin, cell.col,
					Math.random() > 0.5 ? Mark.X : Mark.O));
		
		ImageIcon icon = loadImage("background.jpg");
		window.setBackground(icon);
		window.add(tableView);
		
		window.addMouseMotionListener(tableView.mouseMotionListener());
		window.addMouseListener(tableView.mouseListener());
	}
	
	
	public static void main(String[] args) throws Exception {
		AppMVCTest app = new AppMVCTest();
		
	}

}
