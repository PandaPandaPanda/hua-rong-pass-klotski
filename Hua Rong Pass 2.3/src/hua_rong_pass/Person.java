package hua_rong_pass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Person extends JButton implements FocusListener {
	
	//==========	Declare variables
	int number;
	//==========
	
	/*****************************************
	 * Constructor
	 * pre: number input
	 * post: A new Person instance is created
	 * @param number
	 *****************************************/
	Person(int number) {
		this.number = number;
		addFocusListener(this);
	}

	public void focusGained(FocusEvent e) {
		setBackground(Color.red);
	}

	public void focusLost(FocusEvent e) {
		setBackground(Color.blue);
	}
}
