package ticTacToe.component.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import ticTacToe.component.button.Button;

public class TextButton extends Button {
	
	static private int count=1;
	
	protected String text = "button"+count++;

	public TextButton() {
		super();
	}

	public TextButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public TextButton(int x, int y) {
		super(x, y);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	private void printButtonText(Graphics2D g2D) {
		
		if(text == null || text.isBlank())
			return;
		
		Font font = new Font("Arial", Font.BOLD, 12);
		g2D.setFont(font);
		FontMetrics fm = g2D.getFontMetrics(font);
		
		String visibleText = text;
		while (fm.stringWidth(visibleText) >= this.width()) 
			visibleText = visibleText.substring(0, visibleText.length()-1);
		
		if(visibleText.isBlank())
			return;
		
		Rectangle bounds = fm.getStringBounds(visibleText, g2D).getBounds();
		int xLeft = position.x + (width()-bounds.width)/2;
		int yTop  = position.y + (height()-bounds.height)/2 + bounds.height -3;
		
		g2D.drawString(visibleText, xLeft, yTop);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
			
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(position.x, position.y, dimension.width, dimension.height);
		
		g.setColor(Color.BLACK);
		printButtonText((Graphics2D)g );
	}
}

