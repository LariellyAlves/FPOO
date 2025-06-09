package ticTacToe.component.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageButton extends Button {
	
	protected Image image;

    public ImageButton() {
        super();
    }

    public ImageButton(int x, int y) {
        super(x, y);
    }

    public ImageButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
	
    
    public void setImage(String imagePath) {
    	
    	try {
    		this.image = ImageIO.read(new File(imagePath));
    	} catch (Exception e) {
    		System.out.println("Erro: "+ e.getMessage());
    	}
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.WHITE);
        g2D.fillRect(position.x, position.y, dimension.width, dimension.height);

        if (image != null) {
            int imgWidth = image.getWidth(null);
            int imgHeight = image.getHeight(null);

            float scaleX = (float) dimension.width / imgWidth;
            float scaleY = (float) dimension.height / imgHeight;
            float scale = Math.min(scaleX, scaleY);

            int drawWidth = (int) (imgWidth * scale);
            int drawHeight = (int) (imgHeight * scale);

            int drawX = position.x + (dimension.width - drawWidth) / 2;
            int drawY = position.y + (dimension.height - drawHeight) / 2;

            g2D.drawImage(image, drawX, drawY, drawWidth, drawHeight, null);
        } else {
            g2D.setColor(Color.RED);
            g2D.drawString("No Image", position.x + 5, position.y + 15);
        }
    }
}