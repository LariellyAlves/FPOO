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

public class Button  implements Paintable {
	
	private Point position = null; 
	private Dimension dimension = null;
	private boolean mouseOver = false;
	
	public Button() {
		
		this.position = new Point (0, 0);
		this.dimension = new Dimension(20,20);
	}
	
	
	public Button(int x, int y) {
		this();
		setPosition(x,y);
	}
	
	
	
	public Button(int x, int y, int width, int height) {
		this();
		setSize(width,height);
		
	}
	
	public void setPosition(int x, int y) {
		this.position = new Point(x,y);
	}
	
	public Point getPosition() {
		return (Point) this.position.clone();
	}
	
	public void setSize(int width, int height) {
		this.dimension = new Dimension(width, height);
	}
	
	public Dimension getSize() {
		return (Dimension) this.dimension.clone();
	}
	
	
	public int width() {
		return this.dimension.width;
	}
	
	
	public int height() {
		return this.dimension.height;
	}
	
	public boolean isOver(Point point) {
		
		int xLeft = this.position.x;
		int yTop = this.position.y;
		int xRight = xLeft + this.width();
		int yBotton = yTop + this.height();
		
		return((point.x > xLeft && point.x < xRight) &&
				(point.y > yTop && point.y < yBotton));
		
	}
	
	//--reacting to mouse click --------
	
	public MouseListener mouseListener() {
		
		return new MouseListenerAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent me) {
				
				if( !isOver(me.getPoint() ))
					return;
				
				System.out.println("oieeee!");
			}
		};
		
	}
	
	
	public MouseMotionListener mouseMotionListener() {
		return new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent me) {
				boolean isInside = isOver(me.getPoint());
				if(isInside && !mouseOver) {
					mouseOver = true;
					System.out.println("Mouse entrou no botão.");
				} else if (!isInside && mouseOver) {
					mouseOver = false;
					System.out.println("Mouse saiu do botão.");
				}
			}
		};
	}
	
	
	
	//---interface Paintable------


	@Override //---esse metodo sobre escreve o anterior----
	public void paint(Graphics g) {
		
	g.drawRect(position.x, position.y, dimension.width, dimension.height);
	
	if(mouseOver) {
		
		java.awt.Color originalColor = g.getColor();
		g.setColor(java.awt.Color.RED);
		
        g.drawRect(position.x + 2, position.y + 2, dimension.width - 4, dimension.height - 4);

        g.setColor(originalColor);                   
	}
		
	}
}




