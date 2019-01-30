package hua_rong_pass;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Hua_Rong_Pass extends JFrame implements MouseListener, KeyListener, ActionListener {

	// ================================================= Declare variables
	static JButton left;
	static JButton right;
	static JButton above;
	static JButton below;
	static Person person[] = new Person[10];
	JFrame frame = new JFrame();
	JFrame tutorialFrame = new JFrame();
	JPanel panel,tutorialPanel;
	JComboBox<String> difficulty;
	JButton gameRule;
	JButton undo = new JButton("Undo");
	JButton start = new JButton("START");
	JButton proceed = new JButton("Proceed");
	JButton restart = new JButton("Restart");
	JButton showTutorial = new JButton("Show Tutorial");
	JLabel cover = new JLabel();
	JLabel gif = new JLabel();
	JLabel gameConceptTitle, gameConcept1, gameConcept2, exit, tutorialLabel, counter;
	Image img = null;
	ArrayList<Move> moveHistory = new ArrayList<Move>();
	String gameConceptStr1, gameConceptStr2;
	int startX = -1, startY = -1;
	int endX = -1, endY = -1;
	int count = 0;
	// =================================================

	/**********************************
	 * constructor 
	 * pre: none 
	 * post: A new game is initialized
	 **********************************/
	public Hua_Rong_Pass() {
		cover();
		setInterfaces();
		frame.setVisible(true);
		frame.setResizable(false);
		validate();
	}
	
	
	/*************************
	 * Set cover picture
	 * pre: none
	 * post: cover picture set
	 *************************/
	public void cover() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 620, 800);
		
		try		//Set up background
		{
		    img = ImageIO.read(new File("images//background.png"));
		}
		catch ( IOException exc )
		{
		}
		
		img = img.getScaledInstance(608, 800, Image.SCALE_DEFAULT);
		panel = new Background(img);
		
		left = new JButton();	//Set up the bounds
		right = new JButton();
		above = new JButton();
		below = new JButton();
		panel.add(left);
		panel.add(right);
		panel.add(above);
		panel.add(below);
		left.setBounds(50, 60, 10, 625);
		left.setOpaque(true);
		left.setBackground(Color.CYAN);
		right.setBounds(560, 60, 10, 625);
		right.setOpaque(true);
		right.setBackground(Color.CYAN);
		above.setBounds(50, 50, 520, 10);
		above.setOpaque(true);
		above.setBackground(Color.CYAN);
		below.setBounds(50, 685, 520, 10);
		below.setOpaque(true);
		below.setBackground(Color.CYAN);
		frame.add(panel);
		
		ImageIcon coverPic = new ImageIcon("images//cover.png");
	    coverPic.setImage(coverPic.getImage().getScaledInstance(514, 400, Image.SCALE_DEFAULT));
	    cover.setIcon(coverPic);
	    cover.setBounds(60,275,500,420);  
	    panel.add(cover);
		
	    panel.add(start);
	    start.setFont(new Font(null , Font.BOLD, 28));
	    start.setForeground(new Color(153, 153, 153));
	    start.setBounds(207, 150, 200, 70);
	    start.setActionCommand("start");
	    start.addActionListener(this);
	    
		panel.setLayout(null);
		validate();
	}
	
	
	/***************************
	 * Set the start interfaces
	 * pre: none 
	 * post: Buttons are set
	 ***************************/
	public void setInterfaces() {	
		panel.add(restart);
		restart.setBounds(198, 715, 100, 35);
		restart.setActionCommand("restart");
		restart.addActionListener(this);
		restart.setVisible(false);

		panel.add(showTutorial);
		showTutorial.setBounds(322, 715, 100, 35);
		showTutorial.setActionCommand("Show Tutorial");
		showTutorial.addActionListener(this);
		
		panel.add(undo);
		undo.setBounds(458, 715, 100, 35);
		undo.setActionCommand("undo");
		undo.addActionListener(this);
		
		String[] difficulties = {"Hard", "Easy"};
		difficulty = new JComboBox<String>(difficulties);
		difficulty.setSelectedIndex(0);
		difficulty.addActionListener(this);
	}

	
	/******************************
	 * Display the game concept
	 * pre: start button clicked
	 * post: gameCOncept displayed
	 ******************************/
	public void gameConcept() {
		gameConceptTitle = new JLabel("The Story of Huarong Pass");
		gameConceptTitle.setFont(new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 30));
		panel.add(gameConceptTitle);
		gameConceptTitle.setBounds(70, 60, 464, 30);
		
		gameConceptStr1 = "<html><p><br/><br/> A fictionalized version of Cao Cao’s escape is recounted in Three Kingdoms, the "
				+ " classic historical novel by Luo Guanzhong (ca. 1330–1400). According to this story, "
				+ " the only way for Cao Cao to escape was through the narrow Huarong Pass. "
				+ "However, the Shu strategist Zhuge Liang had already placed his commander"
				+ " Guan Yu there, and Cao Cao was captured. But taking advantage of their old friendship, "
				+ " Cao Cao persuaded Guan Yu to let him go.<p>"
				+ "<br/><br/><p>  The rectangular board is a battlefield; the large square block at the top is Cao Cao; "
				+ "the horizontal rectangular block directly below Cao Cao is Guan Yu; "
				+ "the other eight blocks are other Shu commanders and soldiers, "
				+ "and the opening at the bottom of the board is Huarong Pass. Initially, "
				+ "the blocks are arranged with Cao Cao’s block trapped by the other nine. <p><html>";
		gameConceptStr2 = "<html><p><br/><br/> The player’s job is to slide blocks horizontally and vertically so that "
				+ "Cao Cao can eventually escape through the pass. "
				+ "<br/>Use mouse to sleclect the chess and use arrow keys to move them.<html><p>";
		gameConcept1 = new JLabel(gameConceptStr1);
		gameConcept1.setFont(new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 17));
		panel.add(gameConcept1);
		gameConcept1.setBounds(70, 94, 464, 350);
		gameConcept2 = new JLabel(gameConceptStr2);
		gameConcept2.setFont(new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 22));
		panel.add(gameConcept2);
		gameConcept2.setBounds(70, 204, 464, 590);
		
		panel.add(proceed);
		proceed.setBounds(207, 610, 200, 35);
		proceed.setActionCommand("proceed");
		proceed.addActionListener(this);
	}
	
	/******************************************
	 * Initialize the components in the game 
	 * pre: proceed button clicked 
	 * post: All the components are initialized
	 ******************************************/
	public void init() {
		restart.setVisible(true);
		counter = new JLabel("You have moved " + count + " steps");
		counter.setFont(new Font("Serif", Font.BOLD, 18));
		panel.add(counter);
		counter.setBounds(54, 7, 200, 35);
		
		exit = new JLabel("E========X========I========T");
		exit.setFont(new Font("Serif", Font.BOLD, 15));
		exit.setForeground(new Color(153, 102, 0));
		panel.add(exit);
		exit.setBounds(185, 695, 300, 20);
		
		
		for (int k = 0; k < 10; k++) {
			person[k] = new Person(k);
			panel.add(person[k]);
			person[k].addMouseListener(this);
			person[k].addKeyListener(this);
		}
		
		ImageIcon CaoCao = new ImageIcon("images//CaoCao.png");
		CaoCao.setImage(CaoCao.getImage().getScaledInstance(235, 235, Image.SCALE_DEFAULT));
		person[0].setIcon(CaoCao);

		ImageIcon GuanYu = new ImageIcon("images//GuanYu.png");
		GuanYu.setImage(GuanYu.getImage().getScaledInstance(235, 110, Image.SCALE_DEFAULT));
		person[1].setIcon(GuanYu);

		ImageIcon ZhangFei = new ImageIcon("images//ZhangFei.png");
		ZhangFei.setImage(ZhangFei.getImage().getScaledInstance(110, 235, Image.SCALE_DEFAULT));
		person[2].setIcon(ZhangFei);

		ImageIcon MaChao = new ImageIcon("images//MaChao.png");
		MaChao.setImage(MaChao.getImage().getScaledInstance(110, 235, Image.SCALE_DEFAULT));
		person[3].setIcon(MaChao);

		ImageIcon HuangZhong = new ImageIcon("images//HuangZhong.png");
		HuangZhong.setImage(HuangZhong.getImage().getScaledInstance(110, 235, Image.SCALE_DEFAULT));
		person[4].setIcon(HuangZhong);

		ImageIcon ZhaoYun = new ImageIcon("images//ZhaoYun.png");
		ZhaoYun.setImage(ZhaoYun.getImage().getScaledInstance(110, 235, Image.SCALE_DEFAULT));
		person[5].setIcon(ZhaoYun);

		ImageIcon soldier1 = new ImageIcon("images//soldier.png");
		soldier1.setImage(soldier1.getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
		person[6].setIcon(soldier1);

		ImageIcon soldier2 = new ImageIcon("images//soldier.png");
		soldier2.setImage(soldier2.getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
		person[7].setIcon(soldier2);

		ImageIcon soldier3 = new ImageIcon("images//soldier.png");
		soldier3.setImage(soldier3.getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
		person[8].setIcon(soldier3);

		ImageIcon soldier4 = new ImageIcon("images//soldier.png");
		soldier4.setImage(soldier4.getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
		person[9].setIcon(soldier4);
		
		panel.add(difficulty);
		difficulty.setBounds(72, 715, 100, 35);
		setUpHard();
		person[9].requestFocus();
		
		validate();	
	}
	
	
	/**************************************************
	 * Set up for the game in hard difficulty
	 * pre: Hard is select in the JComboBox difficulty
	 * post: Chess are set up in hard difficulty
	 **************************************************/
	public void setUpHard() {
		person[0].setBounds(185, 60, 250, 250);
		person[1].setBounds(185, 310, 250, 125);
		person[2].setBounds(60, 60, 125, 250);
		person[3].setBounds(435, 310, 125, 250);
		person[4].setBounds(60, 310, 125, 250);
		person[5].setBounds(435, 60, 125, 250);
		person[6].setBounds(60, 560, 125, 125);
		person[7].setBounds(435, 560, 125, 125);
		person[8].setBounds(185, 435, 125, 125);
		person[9].setBounds(310, 435, 125, 125);
	}
	
	
	/**************************************************
	 * Set up for the game in easy difficulty
	 * pre: Moderate is select in the JComboBox difficulty
	 * post: Chess are set up in moderate difficulty
	 **************************************************/
	public void setUpModerate() {
		person[0].setBounds(185, 310, 250, 250);
		person[1].setBounds(310, 560, 250, 125);
		person[2].setBounds(60, 60, 125, 250); 
		person[3].setBounds(435, 310, 125, 250);
		person[4].setBounds(185, 60, 125, 250);
		person[5].setBounds(435, 60, 125, 250);
		person[6].setBounds(60, 560, 125, 125);
		person[7].setBounds(185, 560, 125, 125);
		person[8].setBounds(310, 60, 125, 125);
		person[9].setBounds(310, 185, 125, 125);
	}
	
	
	/**************************************************
	 * Set up for the game in hard difficulty
	 * pre: Easy is select in the JComboBox difficulty
	 * post: Chess are set up in hard difficulty
	 **************************************************/
	public void setUpEasy() {
		person[0].setBounds(60, 435, 250, 250);
		person[1].setBounds(310, 435, 250, 125);
		person[2].setBounds(60, 60, 125, 250);
		person[3].setBounds(185, 60, 125, 250);
		person[4].setBounds(310, 60, 125, 250);
		person[5].setBounds(435, 60, 125, 250);
		person[6].setBounds(310, 310, 125, 125);
		person[7].setBounds(435, 310, 125, 125);
		person[8].setBounds(310, 560, 125, 125);
		person[9].setBounds(435, 560, 125, 125);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		Person man = (Person) e.getSource();
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			go(man, below);
		if (e.getKeyCode() == KeyEvent.VK_UP)
			go(man, above);
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			go(man, left);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			go(man, right);
	}

	public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
		startX = p.x;
		startY = p.y;
		
	}

	public void mouseReleased(MouseEvent e) {
		Person man = (Person) e.getSource();
		Point p = e.getPoint();
		endX = p.x;
		endY = p.y;

		if (Math.abs(endX - startX) > Math.abs(endY - startY)) {
			if (endX > startX)
				go(man, right);
			if (endX < startX)
				go(man, left);
		} else {
			if (endY > startY)
				go(man, below);
			if (endY < startY)
				go(man, above);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}
	
	
	/**********************************************************************************
	 * Move the select chess
	 * pre: A chess is selected and moving direction is determined
	 * post: A chess is check and moved towards the determined direction if not blocked
	 * @param man
	 * @param direction
	 **********************************************************************************/
	public void go(Person man, JButton direction) {
		Move movement = new Move(man, direction);
		boolean move = true;
		Rectangle manRect = man.getBounds();
		
		movement.reLocate();
		manRect.setLocation(movement.x, movement.y);
		Rectangle directionRect = direction.getBounds();
		for (int k = 0; k < 10; k++) {
			Rectangle personRect = person[k].getBounds();
			if ((manRect.intersects(personRect)) && (man.number != k))
				move = false;
		}
		
		if (manRect.intersects(directionRect))
			move = false;
		
		if (move == true) {
			moveHistory.add(movement);
			count++;
			counter.setText("You have moved " + count + " steps");
			man.setLocation(movement.x, movement.y);
		}

		int checkx, checky;
		checkx = person[0].getBounds().x;
		checky = person[0].getBounds().y;
		if (checkx == 185 && checky == 435) {
			person[0].setLocation(185, 435);
			win();
		}
	}

	
	/********************************************
	 * Handle the user input
	 * pre: Button pressed or JComboBox selected
	 * post: Certain action performed
	 ********************************************/
	public void actionPerformed(ActionEvent e) {
		String eventName = e.getActionCommand();
		String difficultyName = (String)difficulty.getSelectedItem();

		if (eventName.equals("start")) {	//Start the game
		    start.setVisible(false);
		    panel.remove(start);
			cover.setVisible(false);
			panel.remove(cover);
			gameConcept();
			
		}
		
		if (eventName.equals("proceed")) {	//Proceed to main game
			gameConceptTitle.setVisible(false);
			gameConcept1.setVisible(false);
			gameConcept2.setVisible(false);
			proceed.setVisible(false);
			panel.remove(proceed);
			panel.remove(gameConceptTitle);
			init();
		}
		
		if (eventName.equals("restart")) { // Restart the game
			frame.setVisible(false);
			frame.dispose(); 			// Destroy old frame
			new Hua_Rong_Pass(); 		// Create new frame
			displayTutorial(false);
			tutorialFrame.dispose();	//Destroy old tutorial frame
			moveHistory.clear();		//Clear old movement history
		}

		if (eventName.equals("Hide Tutorial")) { // Show tutorial picture
			showTutorial.setText("Show Tutorial");
			showTutorial.setActionCommand("Show Tutorial");
			displayTutorial(false);
			tutorialFrame.dispose();
		}

		if (eventName.equals("Show Tutorial")) { // Show tutorial picture
			showTutorial.setText("Hide Tutorial");
			showTutorial.setActionCommand("Hide Tutorial");
			displayTutorial(true);
		}	
		
		if (eventName.equals("undo") && count <= 0) {
			JOptionPane.showMessageDialog(this, "You havn't moved yet!");
		}
		
		if (eventName.equals("undo") && count > 0) {
			count--;
			switch (moveHistory.get(count).toString()) {
			case "below":
				person[moveHistory.get(count).num].setLocation(moveHistory.get(count).x, moveHistory.get(count).y - 125);
				break;
			case "above":
				person[moveHistory.get(count).num].setLocation(moveHistory.get(count).x, moveHistory.get(count).y + 125);
				break;
			case "left":
				person[moveHistory.get(count).num].setLocation(moveHistory.get(count).x + 125, moveHistory.get(count).y);
				break;
			case "right":
				person[moveHistory.get(count).num].setLocation(moveHistory.get(count).x - 125, moveHistory.get(count).y);	
			}
			moveHistory.remove(count);
			counter.setText("You have moved " + count + " steps");
		}
		
		if (!(eventName.equals("Hide Tutorial")|| eventName.equals("Show Tutorial") 
				|| eventName.equals("start") || eventName.equals("undo"))) {	//Change difficulty
			if (difficultyName.equals("Hard")) {
				setUpHard();
				count = 0;
				counter.setText("You have moved " + count + " steps");
				moveHistory.clear();
			}
			if (difficultyName.equals("Easy")) {
				setUpEasy();
				count = 0;
				counter.setText("You have moved " + count + " steps");
				moveHistory.clear();
			}
		}
	}
	
	
	/*************************************************
	 * Display or hide tutorial picture 
	 * pre: Boolean variable display is true or false 
	 * post: Tutorial picture is displayed or hided
	 * @param display
	 *************************************************/
	private void displayTutorial(boolean display) {
		ImageIcon tutorial = new ImageIcon("images//tutorial.png");
		tutorialPanel = new JPanel();
		tutorialLabel = new JLabel(tutorial);
		tutorialPanel.add(tutorialLabel);
		tutorialFrame.setContentPane(tutorialPanel);
		tutorialFrame.pack();
		tutorialFrame.setLocation(608, 0);
		tutorialFrame.setVisible(display);
	}
	
	
	/************************************************
	 * Display the winning message
	 * pre: User moved the Cao Cao chess to the exit
	 * post: Winning message is displayed
	 ************************************************/
	public void win() {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		
		JLabel congratsText1 = new JLabel("Congratulations");
		congratsText1.setFont(new Font("Serif", Font.BOLD, 40));
		panel.add(congratsText1);
		congratsText1.setBounds(70, 104, 400, 40);

		ImageIcon congratsGif = new ImageIcon("images//congratsGif.gif");
		congratsGif.setImage(congratsGif.getImage().getScaledInstance(512, 320, Image.SCALE_DEFAULT));
        gif.setIcon(congratsGif);
	    panel.add(gif);
	    gif.setBounds(54, 220, 512, 320);
	    
	    JLabel congratsText2 = new JLabel("You helped Cao Cao escape Hua Rong Pass");
		congratsText2.setFont(new Font("Serif", Font.BOLD, 20));
		panel.add(congratsText2);
		congratsText2.setBounds(70, 640, 400, 20);
	}
}
