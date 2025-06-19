package ticTacToe.component.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageButton extends Button {
	
	protected ImageIcon icon = null;

    public ImageButton() {
        super();
    }

    public ImageButton(int x, int y, int width, int height, ImageIcon icon) {
        super(x, y, width, height);
        this.icon = icon;
    }

    public ImageButton(int x, int y, ImageIcon icon) {
        super(x, y, icon.getIconWidth(), icon.getIconHeight());
        this.icon = icon;
    }
	
	public void setImage(ImageIcon icon) {
		this.icon = icon;
		
		}
	
	
		public ImageIcon getImage() {
			
		return this.icon;
		}
		
		
		private void drawImage(Graphics2D g2D) {
			
		g2D.drawImage(icon.getImage(),
		              position.x, position.y,
		              width(), height(), null);
	}
    		
    @Override
    public void paint(Graphics g) {
    	
    	if(icon != null)
    	drawImage((Graphics2D)g);
    	
    	if(mouseOver)
    	super.doMouseOverDecoration(g);
    	
    }
}