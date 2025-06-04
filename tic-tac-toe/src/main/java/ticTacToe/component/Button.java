package ticTacToe.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import ticTacToe.gui.Paintable;
import ticTacToe.gui.util.MouseListenerAdapter;

public class Button  extends AbstractComponent{
	
	private Point position = null; 
	private Dimension dimension = null;
	private boolean mouseOver = false;
	
	public Button() {  
		super();
	}
	
	
	public Button(int x, int y) {  
		super(x, y);
	}
	
	
	
	public Button(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}
	
	
	
	
	//---interface Paintable------


	@Override 
	public void paint(Graphics g) {
		
	g.drawRect(position.x, position.y, dimension.width, dimension.height);
	 
	 if(mouseOver) {
		 doMouseOverDecoration(g);
	 }
	
	
}
	
	
	
	private void doMouseOverDecoration(Graphics g) {
		
		if(mouseOver) {
			
			java.awt.Color originalColor = g.getColor();
			g.setColor(java.awt.Color.RED);
			
	        g.drawRect(position.x + 2, position.y + 2, dimension.width - 4, dimension.height - 4);

	        g.setColor(originalColor);                   
		}
		
	}
}




