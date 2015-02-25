package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * A JPanel with a background image
 * Drivers - Alexander Simeonov
 * @author Alexander Simeonov
 */
public class LotusPanel extends JPanel {
	
	/**
	 * Overrides the paint method so that a background image is placed on the JPanel
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * @param graphics
	 */
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		ImageIcon background = new ImageIcon("src/gui/background.gif");
		Image lotusBackground = background.getImage();
	    graphics.drawImage(lotusBackground, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}