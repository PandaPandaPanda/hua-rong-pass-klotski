package hua_rong_pass;

import java.awt.Rectangle;
import javax.swing.JButton;

public class Move {
	
	//=================================		Declare variables
	JButton direction = new JButton();
	Rectangle manRect;
	Person man;
	int x, y, num;
	//=================================
	
	/***************************************
	 * Constructor
	 * pre: man, direction input
	 * post: A new Move instance is created
	 * @param man
	 * @param directionButton
	 ***************************************/
	public Move(Person man, JButton directionButton) {
		x = man.getBounds().x;
		y = man.getBounds().y;
		num = man.number;
		manRect = man.getBounds();
		direction = directionButton;

	}
	
	
	/********************************
	 * Relocate the x, y value
	 * pre: none
	 * post: x, y values are relocate
	 ********************************/
	public void reLocate() {
		if (direction == Hua_Rong_Pass.below)
			y = y + 125;
		else if (direction == Hua_Rong_Pass.above)
			y = y - 125;
		else if (direction == Hua_Rong_Pass.left)
			x = x - 125;
		else if (direction == Hua_Rong_Pass.right)
			x = x + 125;
	}
	
	
	@Override
	public String toString() {
		if (direction == Hua_Rong_Pass.below)
			return "below";
		else if (direction == Hua_Rong_Pass.above)
			return "above";
		else if (direction == Hua_Rong_Pass.left)
			return "left";
		else if (direction == Hua_Rong_Pass.right)
			return "right";
		else
			return null;
	}
}
