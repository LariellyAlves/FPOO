package ticTacToe.component.button;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import ticTacToe.component.AbstractComponent;
import ticTacToe.component.button.ButtonClickEvent.MouseButton;
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
	
	
	


      @FunctionalInterface
      public interface ButtonClickListener {
    	  
    	  void onClick(ButtonClickEvent e);
	
}
      
      
      //--Observer Pattern------
      Set<ButtonClickListener> buttonClickListeners = new HashSet<>();
      
      public void addButtonClickListener(ButtonClickListener listener) {
    	  buttonClickListeners.add(listener);
      }
      
      
      public void removeButtonClickListener(ButtonClickListener listener) {
    	  buttonClickListeners.remove(listener);
      }
      
      
      private void dispatchButtonClickEvent(MouseEvent me) {
    	  
    	  MouseButton button = ((me.getButton() == MouseEvent.BUTTON1) ? MouseButton.LEFT : 
    		  (me.getButton() == MouseEvent.BUTTON2) ? MouseButton.MIDLE : 
    			  MouseButton.RIGHT);
    	  
    	  
    	  ButtonClickEvent event = new ButtonClickEvent(this, button);
    	  buttonClickListeners.forEach(listener->listener.onClick(event));
      }
      
      
      @Override
      protected void onMouseClick(MouseEvent me) {
     	  dispatchButtonClickEvent(me);
       }
       
       
}




