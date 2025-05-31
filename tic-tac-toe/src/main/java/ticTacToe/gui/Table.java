package ticTacToe.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Table implements Paintable{

	private Point      position;
	private Dimension  dimension;
	private ImageIcon  icon;
	
	
	public Table() {
		this.position = new Point(0,0);
		this.dimension = new Dimension (0,0);
		this.icon = null;
	}
	
	public Table(ImageIcon icon) {
		this();
		this.icon = icon;
	}
	
	
	public Table(int x, int y, int width, int height, ImageIcon icon) {
		this.position = new Point(x, y);
		this.dimension = new Dimension(width, height);
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
