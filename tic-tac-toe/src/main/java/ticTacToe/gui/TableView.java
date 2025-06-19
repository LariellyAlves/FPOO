package ticTacToe.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import ticTacToe.component.AbstractComponent;
import ticTacToe.component.button.ImageButton;
import ticTacToe.gui.util.CellClickListener;
import ticTacToe.model.Mark;
import ticTacToe.model.table.ReadOnlyTableModel;

public class TableView extends AbstractComponent{
	private ImageIcon icon = null;
	private ImageIcon iconX, iconO;
	private ImageButton[][] table = new ImageButton[3][3];
	private ReadOnlyTableModel tableModel;
	List<CellClickListener> cellClicklisteners = new ArrayList<>();
	
	public void addCellClickListener(CellClickListener listener) {
		cellClicklisteners.add(listener);
	}
	
	public void removeCellClickListener(CellClickListener listener) { 
		cellClicklisteners.remove(listener);
	}
	
	private void dispatchCellClickEvent(int lin, int col) {
		CellClickEvent event = new CellClickEvent(lin, col);
		cellClicklisteners.forEach(listener -> listener.onClick(event));
		
	}
	
	public void setIconX(ImageIcon icon) {
		this.iconX = icon;
	}
	
	public void setIconO(ImageIcon icon) {
		this.iconO = icon;
	}
	
	public ImageIcon iconOf(Mark mark) {
		return ((mark == Mark.O) ? iconO :
			   ((mark == Mark.X) ? iconX : null));
	}
	
	public void setTableModel(ReadOnlyTableModel tableModel) {
		this.tableModel = tableModel;
	}
	
	public TableView() {
		super();
		super.dimension = new Dimension(100,100);
		fillTable();
	}
	
	
	
	public TableView(ImageIcon icon) {
		super();
		this.icon = icon;
	}
	
	
	public TableView(int x, int y, int width, int height, ImageIcon icon) {
		super(x, y, width, height);	
		this.icon = icon;
		fillTable();
	}
	
	private void fillTable() {
		int cellWidth = (width()-20)/3;
		int cellHeight = (height()-20)/3;
		
		for(int lin = 0; lin < table.length; lin++){
			for(int col = 0; col < table[lin].length; col++){
				int x = position.x + 5 + (lin * (cellHeight + 5));
				int y = position.y + 5 + (col * (cellWidth+ 5));
				table[lin][col] = new ImageButton(x, y, cellWidth, cellHeight, null);
			}
		}
	}
	
	private void paintChildren(Graphics g) {
		if (tableModel == null)
			throw new RuntimeException("Error: TableModel is null at TableView!");
		
		for(int lin = 0; lin < table.length; lin++) {
			for(int col = 0; col < table[lin].length; col++) {
				Mark mark = tableModel.getMark(lin, col);
				table[lin][col].setImage(iconOf(mark));
				table[lin][col].paint(g);
			}
		}
	}
	
	
	@Override
	protected void onMouseMove(MouseEvent me) {
		
		for(int lin=0; lin<table.length; lin++) {
			for(int col=0; col<table[lin].length; col++) {
		
				table[lin][col].mouseMotionListener().mouseMoved(me);
			}
		}
	}
	
	
	@Override
	protected void onMouseClick(MouseEvent me) {
		for(int lin=0; lin<table.length; lin++) {
			
			for(int col=0; col<table[lin].length; col++) {
				
				if(table[lin][col].isOver(me.getPoint()))
					dispatchCellClickEvent(lin, col);
			}
		}
	}
	
	
	
	@Override
	public void paint(Graphics g) {

		if (icon == null)
			throw new RuntimeException("Error: icon is null at TableView!");
		
		g.drawImage(icon.getImage(), position.x, position.y, width(), height(), null);
		
		paintChildren(g);
		
	}
	
	
}