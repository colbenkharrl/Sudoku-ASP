//	Colben Kharrl, November, 22nd 2016, Sudoku-ASP

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WholeFrame extends JFrame {
	
//- - - INSTANCE UI VARIABLES - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	private final int FRAME_WIDTH, FRAME_HEIGHT;
	private JPanel naviBar, contentPane, gridFooter;
	private JButton solveB, createB, infoB, actionB, fileB;
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - -
	
//- - - CONSTRUCTOR - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	public WholeFrame() {
		FRAME_WIDTH = 600;
		FRAME_HEIGHT = 800;
		naviBar = navBar();
		contentPane = centerPanel();
		gridFooter = sizePanel();
		add(naviBar, BorderLayout.NORTH);
		add(contentPane, BorderLayout.CENTER);
		add(gridFooter, BorderLayout.SOUTH);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - -	
	
//- - - UI PANELS - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//	Navigation Panel | "Solve", "Create", and "i" (info)
	private JPanel navBar() {
		JPanel returnPanel = new JPanel(new GridLayout(1, 3));
		ActionListener navListener = new bListener();
		solveB = new JTButton("Solve", navListener);
		createB = new JTButton("Create", navListener);
		infoB = new JTButton("Info", navListener);
		returnPanel.add(solveB);
		returnPanel.add(createB);
		returnPanel.add(infoB);
		returnPanel.setPreferredSize(new Dimension(600, 100));
		return returnPanel;
	}
	
	//	Main content panel | Title, Sudoku board, Solve/Create button, MVSM file
	private JPanel centerPanel() {
		JLabel prompt = new JLabel("Input given numbers and solve!");
		ActionListener actionListener = new bListener();
		prompt.setPreferredSize(new Dimension(600, 75));
		JPanel returnPanel = new JPanel();
		JPanel gridPanel = gridPanel(6);
		JPanel buttonScreen = new JPanel(new GridLayout(2, 1));
		actionB = new JTButton("Action", actionListener);
		fileB = new JTButton("MVSM File", actionListener);
		buttonScreen.add(actionB);
		buttonScreen.add(fileB);
		gridPanel.setPreferredSize(new Dimension(400, 400));
		returnPanel.add(prompt, BorderLayout.NORTH);
		returnPanel.add(gridPanel, BorderLayout.CENTER);
		returnPanel.add(buttonScreen, BorderLayout.EAST);
		return returnPanel;
	}
	
	//	Grid size buttons | 6x6, 9x9, 16x16
	private JPanel sizePanel() {
		JPanel returnPanel = new JPanel(new GridLayout(1, 3));
		ActionListener actionListener = new bListener();
		returnPanel.add(new JTButton("6 x 6", actionListener));
		returnPanel.add(new JTButton("9 x 9", actionListener));
		returnPanel.add(new JTButton("16 x 16", actionListener));
		returnPanel.setPreferredSize(new Dimension(600, 140));
		return returnPanel;
	}
	
	private JPanel gridPanel(int n) {
		JPanel returnPanel = new JPanel(new GridLayout(n, n));
		ActionListener gridListener = new GridListener();
		for (int i = 0; i < Math.pow(n, 2); i++) {
			returnPanel.add(new JTButton("0", gridListener));
		}
		
		return returnPanel;
	}
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - -
	
//- - - LISTENERS - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	//	Listener for nav-bar functions Solve/Create/Info
	private class bListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			@SuppressWarnings("deprecation")
			String command = ((JButton) event.getSource()).getLabel();
			System.out.println(command);
		}
	}
	
	//	Listener for grid size
	private class GridListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			@SuppressWarnings("deprecation")
			String number = ((JButton) event.getSource()).getLabel();
			System.out.println(number);
		}
	}
	
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - -
}
