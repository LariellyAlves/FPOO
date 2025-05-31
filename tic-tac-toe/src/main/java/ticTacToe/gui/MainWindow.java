package ticTacToe.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainWindow
{
    JFrame frame = new JFrame();
    PaintablePanel panel = new PaintablePanel();
    
    Timer timer = new Timer();
    TimerTask repaintTask = new TimerTask() {
		@Override
		public void run() {
			SwingUtilities.invokeLater(()->panel.repaint());
		}
    };
        
    public MainWindow()
    {
    	frame.setSize(new Dimension(600, 400));
    	frame.setResizable(false);
    	
    	frame.add(panel);
             
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        timer.scheduleAtFixedRate(repaintTask, 0, 500);
    }
    
    public void setBackground(ImageIcon icon) {
		panel.setBackground(icon);
	}
    
    public void setBackground(URL url) {
    	this.setBackground(new ImageIcon(url));
    }
    
    public void add(Paintable paintable)
    {
        panel.add(paintable);
    }

    public void remove(Paintable paintable)
    {
        panel.remove(paintable);
    }
    
    public void addMouseListener(MouseListener listener)
    {
    	panel.addMouseListener(listener);
    }
    
    public void removeMouseListener(MouseListener listener)
    {
    	panel.removeMouseListener(listener);
    }
    
    public void addMouseMotionListener(MouseMotionListener listener)
    {
    	panel.addMouseMotionListener(listener);
    }
    
    public void removeMouseMotionListener(MouseMotionListener listener)
    {
    	panel.removeMouseMotionListener(listener);
    }
}


class PaintablePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon background = null;
	
	public void setBackground(ImageIcon icon) {
		this.background = icon;
		super.repaint();
	}
	
	private Set<Paintable> paintables = new HashSet<>();
	
    public void add(Paintable paintable)
    {
        paintables.add(paintable);
        super.repaint();
    }

    public void remove(Paintable paintable)
    {
        paintables.remove(paintable);
        super.repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	
    	if(background != null)
    		g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(),this);		
    }
    
    @Override
    public void paintChildren(Graphics g)
    {
    	super.paintChildren(g);
    	
    	for(Paintable paintable : paintables)
            paintable.paint(g);
    }
}
