package ticTacToe.gui.score;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import ticTacToe.component.AbstractComponent;
import ticTacToe.component.button.TextButton;
import ticTacToe.model.score.ReadOnlyScoreModel;

public class ScoreView extends AbstractComponent {

	TextButton buttonX = new TextButton();
	TextButton buttonO = new TextButton();
	ImageIcon background;
	ReadOnlyScoreModel scoreModel;
	
	public ScoreView(int x, int y, int width, int height, ReadOnlyScoreModel scoreModel) {
		
		super(x, y, width, height);
		this.scoreModel = scoreModel;
		
		int btnWidth  = ((width-20) / 2 );
		int btnHeight = ((height/2) - 10);
		buttonX.setSize(btnWidth, btnHeight);
		buttonO.setSize(btnWidth, btnHeight);
		
		int btnX = x + 5;
		int btnY  = y + height/2;
		buttonO.setPosition(btnX, btnY);
		buttonX.setPosition(btnX + btnWidth + 10, btnY);
	}
	
	public void setBackground(ImageIcon icon) {
		background = icon;
	}
	
	@Override
	public void paint(Graphics g) {
		
		g.drawImage(background.getImage(),
				    position.x, position.y,
				    width(), height(), null);
		
		buttonX.setText(""+scoreModel.scoreX());
		buttonO.setText(""+scoreModel.scoreO());
		
		buttonX.paint(g);
		buttonO.paint(g);
	}
}
