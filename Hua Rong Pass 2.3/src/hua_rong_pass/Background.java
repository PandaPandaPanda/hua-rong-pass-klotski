package hua_rong_pass;

import java.awt.*;
import javax.swing.*;

public class Background extends JPanel {
	
	//================	Declare variables
	private Image img;
	//================
	
	/*****************************************
	 * Constructor
	 * pre: Image input
	 * post: A new Background instance created
	 * @param img
	 *****************************************/
	public Background(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
