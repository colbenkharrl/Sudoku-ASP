//	Colben Kharrl, November, 22nd 2016, Sudoku-ASP

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;

public class WholeFrame extends JFrame {
	
//- - - INSTANCE UI VARIABLES - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	private final int FRAME_WIDTH, FRAME_HEIGHT;
	private JPanel naviBar, contentPane, gridFooter, gridPanel, buttonScreen;
	private JButton solveB, createB, infoB, actionB, fileB;
	private JTButton[] gridBs;
	private JScrollPane pane;
	private JTextArea text;
	private JLabel prompt;
	private char state;
	private int n;
	private boolean viewingText;
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - -
	
//- - - CONSTRUCTOR - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	public WholeFrame() {
		FRAME_WIDTH = 1000;
		FRAME_HEIGHT = 1000;
		state = 's';
		n = 9;
		viewingText = false;
		naviBar = navBar();
		contentPane = centerPanel(state, n);
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
		solveB = new JTButton("Solve", navListener, 'h');
		createB = new JTButton("Create", navListener, 'h');
		infoB = new JTButton("Info", navListener, 'h');
		returnPanel.add(solveB);
		returnPanel.add(createB);
		returnPanel.add(infoB);
		returnPanel.setPreferredSize(new Dimension(600, 50));
		return returnPanel;
	}
	
	//	Main content panel | Title, Sudoku board, Solve/Create button, MVSM file
	private JPanel centerPanel(char state, int size) {
		gridBs = new JTButton[(int) Math.pow(size, 2)] ;
		JPanel returnPanel = new JPanel();
		if (state != 'i') {
			if (state == 's') {
				prompt = new JLabel("Input given numbers and solve!");
			} else {
				prompt = new JLabel("Create a new, solvable sudoku puzzle!");
			}
			prompt.setFont(new Font("Monospaced", 1, 18));
			prompt.setHorizontalAlignment(JLabel.CENTER);
			prompt.setPreferredSize(new Dimension(650, 50));
			gridPanel = gridPanel(size);
			ActionListener actionListener = new bListener();
			buttonScreen = new JPanel(new GridLayout(2, 1));
			if (state == 'c') {
				actionB = new JTButton("Create!", actionListener, 'b');
			} else {
				actionB = new JTButton("Solve!", actionListener, 'b');
			}
			fileB = new JTButton("MVSM File", actionListener, 'b');
			buttonScreen.add(actionB);
			buttonScreen.add(fileB);
			gridPanel.setPreferredSize(new Dimension(650, 650));
			returnPanel.add(prompt, BorderLayout.NORTH);
			returnPanel.add(gridPanel, BorderLayout.CENTER);
			returnPanel.add(buttonScreen, BorderLayout.EAST);
		}
		else {
			JLabel title = new JLabel("Sudoku-ASP, powered by MVSM.");
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setFont(new Font("Monospaced", 1, 20));
			title.setPreferredSize(new Dimension(600, 100));
			JTextArea text = new JTextArea();
			text.setText("Sudoku-ASP\n\nSudoku-ASP is an application that captures a glimpse of answer set programming,"
					+ " and translates the results into a relatable puzzle, Sudoku.\n\nAnswer Set Programming\n\nAnswer"
					+ " set programming (ASP) is a form of declarative programming oriented towards difficult search problems."
					+ " It is based on the stable model semantics of logic programming. In ASP, search problems are reduced"
					+ " to computing stable models, and answer set solvers — programs for generating stable models—are used to"
					+ " perform search.\n\nMVSM\n\nSystem MVSM is a prototype implementation multi-valued propositional formulas"
					+ " under the stable model semantics computed by grounder and solver gringo and claspD. This reduction is"
					+ " based on the intensional function elimination theorem in Bartholomew & Lee 2012. The system is a toolchain"
					+ " that includes MVSM-compiler, F2LP, gringo, and claspD, as2transition.\n\nThe implementation first compiles"
					+ " the multi-valued formula into a propositional formula. F2LP is used to turn this propositional formula into"
					+ " a logic program. Gringo and claspD are then used to ground the logic program and find the stable models of"
					+ " the program. Finally, as2transition syntactically converts propositional atoms back into multi-valued atoms.");
			text.setEditable(false);
			text.setLineWrap(true);
			text.setWrapStyleWord(true);
			text.setPreferredSize(new Dimension(500, 600));
			returnPanel.add(title, BorderLayout.NORTH);
			returnPanel.add(text, BorderLayout.CENTER);
		}
		returnPanel.setBackground(Color.white);
		return returnPanel;
		
	}
	
	//	Grid size buttons | 6x6, 9x9, 16x16
	private JPanel sizePanel() {
		JPanel returnPanel = new JPanel(new GridLayout(1, 3));
		ActionListener actionListener = new bListener();
		returnPanel.add(new JTButton("6 x 6", actionListener, 'h'));
		returnPanel.add(new JTButton("9 x 9", actionListener, 'h'));
		returnPanel.add(new JTButton("16 x 16", actionListener, 'h'));
		returnPanel.setPreferredSize(new Dimension(600, 50));
		return returnPanel;
	}
	
	private JPanel gridPanel(int n) {
		JPanel returnPanel = new JPanel(new GridLayout(n, n));
		ActionListener gridListener = new GridListener();
		for (int i = 0; i < Math.pow(n, 2); i++) {
			JTButton gridButton = new JTButton(Integer.toString(0), gridListener, 'g');
			gridBs[i] = gridButton;
			returnPanel.add(gridButton);
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
			switch (command) {
			case "6 x 6":
				n = 6;
				remove(contentPane);
				contentPane = centerPanel(state, n);
				add(contentPane, BorderLayout.CENTER);
				break;
			case "9 x 9":
				n = 9;
				remove(contentPane);
				contentPane = centerPanel(state, n);
				add(contentPane, BorderLayout.CENTER);
				break;
			case "16 x 16":
				n = 16;
				remove(contentPane);
				contentPane = centerPanel(state, n);
				add(contentPane, BorderLayout.CENTER);
				break;
			case "Solve!":
				if (viewingText) {
					contentPane.remove(pane);
					fileB.setLabel("MVSM File");
				} else {
					contentPane.remove(gridPanel);
				}
				contentPane.remove(gridPanel);
				contentPane.remove(buttonScreen);
				text = new JTextArea();
				text.setText("Enter MVSM ouput code here..");
				text.setEditable(true);
				text.setLineWrap(true);
				text.setWrapStyleWord(true);
				text.setPreferredSize(new Dimension(650, 650));
				text.setBackground(Color.black);
				text.setForeground(Color.white);
				pane = new JScrollPane(text);
				((JButton) event.getSource()).setLabel("Update");
				contentPane.add(pane, BorderLayout.CENTER);
				contentPane.add(buttonScreen, BorderLayout.EAST);
				viewingText = true;
				break;
			case "Create!":
				if (viewingText) {
					contentPane.remove(pane);
					fileB.setLabel("MVSM File");
				} else {
					contentPane.remove(gridPanel);
				}
				contentPane.remove(buttonScreen);
				text = new JTextArea();
				text.setText("Enter MVSM ouput code here..");
				text.setEditable(true);
				text.setLineWrap(true);
				text.setWrapStyleWord(true);
				text.setPreferredSize(new Dimension(650, 650));
				text.setBackground(Color.black);
				text.setForeground(Color.white);
				pane = new JScrollPane(text);
				((JButton) event.getSource()).setLabel("Update");
				contentPane.add(pane, BorderLayout.CENTER);
				contentPane.add(buttonScreen, BorderLayout.EAST);
				viewingText = true;
				break;
			case "Update":
				String output = text.getText();
				fillBoard(output);
				System.out.println(output);
				contentPane.remove(pane);
				contentPane.remove(buttonScreen);
				if (state == 's') {
					((JButton) event.getSource()).setLabel("Solve!");
				} else {
					((JButton) event.getSource()).setLabel("Create!");
				}
				contentPane.add(gridPanel, BorderLayout.CENTER);
				contentPane.add(buttonScreen, BorderLayout.EAST);
				viewingText = false;				
				break;
			case "MVSM File":
				if (viewingText) {
					contentPane.remove(pane);
					if (state == 's') {
						actionB.setLabel("Solve!");
					} else {
						actionB.setLabel("Create!");
					}
				} else {
					contentPane.remove(gridPanel);
				}
				contentPane.remove(gridPanel);
				contentPane.remove(buttonScreen);
				text = new JTextArea();
				text.setText(MVSMFile(n));
				text.setEditable(false);
				text.setLineWrap(true);
				text.setWrapStyleWord(true);
				text.setPreferredSize(new Dimension(650, 650));
				text.setBackground(Color.black);
				text.setForeground(Color.white);
				pane = new JScrollPane(text);
				pane.createVerticalScrollBar();
				((JButton) event.getSource()).setLabel("Return");
				contentPane.add(pane, BorderLayout.CENTER);
				contentPane.add(buttonScreen, BorderLayout.EAST);
				viewingText = true;
				break;
			case "Return":
				contentPane.remove(pane);
				contentPane.remove(buttonScreen);
				((JButton) event.getSource()).setLabel("MVSM File");
				contentPane.add(gridPanel, BorderLayout.CENTER);
				contentPane.add(buttonScreen, BorderLayout.EAST);
				viewingText = false;
				break;
			case "Solve":
				state = 's';
				remove(contentPane);
				contentPane = centerPanel(state, n);
				add(contentPane, BorderLayout.CENTER);
				viewingText = false;
				break;
			case "Create":
				state = 'c';
				remove(contentPane);
				contentPane = centerPanel(state, n);
				add(contentPane, BorderLayout.CENTER);
				viewingText = false;
				break;
			case "Info":
				state = 'i';
				remove(contentPane);
				contentPane = centerPanel(state, n);
				add(contentPane, BorderLayout.CENTER);
				viewingText = false;
				break;
			default:
				break;
			}
			revalidate();
			repaint();
		}
		
		private String MVSMFile(int size) {
			if (state == 's') {
				String header;
				if (n != 6) {
					header = " :- sorts\n num.\n\n :- objects\n 1.." + n + "\t:: num.\n\n :- variables\n\tR, R1, C, C1, N :: num.\n\n"
							+ " :- constants\n\t a(num,num)\t:: num.\n\n % Set defualt value for cells\n{a(R,C)=N}.\n\n % Two of the same number cannot share a column\n <- a(R,C)=N & a(R1,C)=N & R!=R1.\n\n"
							+ " % Two of the same number cannot share a row\n <- a(R,C)=N & a(R,C1)=N & C!=C1.\n\n % Two of the same number cannot be in the same subsquare\n <- a(R,C)=N & a(R1,C1)=N & R!=R1 & C!=C1 & ((R-1)/" + (int) Math.sqrt(n) 
							+ "*" + (int) Math.sqrt(n) + " + (C-1)/" + (int) Math.sqrt(n) + ") == ((R1-1)/" + (int) Math.sqrt(n) + "*" 
							+ (int) Math.sqrt(n) + " + (C1-1)/" + (int) Math.sqrt(n) + ").\n\n";
				} else {
					header = " :- sorts\n num.\n\n :- objects\n 1..6\t:: num.\n\n :- variables\n\tR, R1, C, C1, N :: num.\n\n :- constants\n"
							+ "\ta(num,num):: num.\n\n {a(R,C)=N}.\n\n <- a(R,C)=N & a(R1,C)=N & R!=R1.\n <- a(R,C)=N & a(R,C1)=N & C!=C1.\n"
							+ " <- a(R,C)=N & a(R1,C1)=N & R!=R1 & C!=C1 & ((R-1)/2*2 + (C-1)/3) == ((R1-1)/2*2 + (C1-1)/3).\n\n";
				}
				String constraints = "";
				for (int i = 0; i < Math.pow(n, 2); i++) {
					if (Integer.parseInt(gridBs[i].getLabel()) != 0) {
						if ((i+1) % n == 0) {
							constraints += " a(" + ((i)/n + 1) + "," + n + ")=" + gridBs[i].getLabel() + ".";
						} else {
							constraints += " a(" + ((i+1)/n + 1) + "," + (i+1) % n + ")=" + gridBs[i].getLabel() + ".";
						}
					}
				}
				return header + constraints;
			}
			else {
				return " :- sorts\n num;\n step;\n astep.\n\n :- objects\n 1..9\t:: num;\n 0..9\t:: digit;\n 0..36\t:: step;\n 0..35\t:: astep."
						+ "\n\n :- variables\n R,R1,C,C1,R\t:: num;\n Bool\t:: boolean;\n N, N1\t:: digit;\n ST\t:: step;\n T\t:: astep.\n\n"
						+ " :- constants\n value(num,num,step)\t:: digit;\n set(num,num,num,astep)\t:: boolean.\n\n % Set default values\n"
						+ " {value(R,C,0)=0}.\n {set(R,C,N,T)=Bool}.\n\n % A block cannot have more than one value \n <- value(R,C,T)=N &"
						+ " value(R,C,T)=N1 & N!=N1.\n\n % Effect of setting a cell\n value(R,C,T+1)=N <- set(R,C,N,T) & N!=0.\n\n % A cell"
						+ " can only be set if it has yet to be set\n <- set(R,C,N,T) & value(R,C,T)!=0.\n\n % Law of inertia\n {value(R,C,T+1)=N}"
						+ " <- value(R,C,T)=N.\n\n % Sudoku rules\n <- a(R,C)=N & a(R1,C)=N & R!=R1.\n <- a(R,C)=N & a(R,C1)=N & C!=C1.\n <- a(R,C)=N"
						+ " & a(R1,C1)=N & R!=R1 & C!=C1 & ((R-1)/3*3 + (C-1)/3) == ((R1-1)/3*3 + (C1-1)/3).";
			}
			
		}
		
		private void fillBoard(String output) {
			Scanner in = new Scanner(output);
			int i = 0;
			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (!(line.contains("="))) {
					return;
				}
				int inc = 0;
				while(line.charAt(inc) != '=') {
					inc++;
				}
				inc++;
				gridBs[i].setLabel(Character.toString(line.charAt(inc)));
				gridBs[i].setBackground(Color.green);
				i++;
			}
		}
	}
	
	//	Listener for grid buttons
	private class GridListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			@SuppressWarnings("deprecation")
			int number = Integer.parseInt(((JButton) event.getSource()).getLabel());
			if (number != n) {
				number++;
				((JTButton) event.getSource()).setColor(Color.BLACK, Color.BLACK);
			} else {
				number = 0;
				((JTButton) event.getSource()).setColor(Color.BLACK, Color.WHITE);
			}
			System.out.println(number);
			((JButton) event.getSource()).setLabel(Integer.toString(number));
		}
	}
	
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - -
}
