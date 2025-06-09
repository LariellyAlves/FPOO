package ticTacToe.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

import ticTacToe.component.AbstractComponent;

public class Table extends AbstractComponent{
	private ImageIcon  icon;
	
	
	public Table() {
		super();
	}
	
	
	
	public Table(ImageIcon icon) {
		super();
		this.icon = icon;
	}
	
	
	
	
	public Table(int x, int y, int width, int height, ImageIcon icon) {
		super(x, y, width, height);	
		this.icon      =  icon;
	}
	
	
	
	@Override
	public void paint(Graphics g) {
	
		if(icon == null)
			return;
		
		int xLeft = position.x;
		int yTop = position.y;
		int width = dimension.width;
		int height = dimension.height;
		
		g.drawImage(icon.getImage(), xLeft, yTop, width, height, null);
		
	}
	
	
}