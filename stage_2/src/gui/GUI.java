package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;

import lrstruct.visitors.ToolTipVisitor;

import code.Game;

import static interfaces.IBoardPositionNames.*;

/**
 * Creates a new instance of the Lotus Game which holds all positions, 10 black pieces, 10 white pieces,
 * a black and white player, and a turn number for each player.
 * Drivers - Alexander Simeonov, Jeremy Fritton, Joseph Mcnamera
 * Navigators - Alexander Simeonov, Jeremy Fritton, Joseph Mcnamera
 * @author Alexander Simeonov
 */
public class GUI implements Runnable {
	/**
	 * A reference to the Game
	 */
	private Game _game;
	/**
	 * The main frame
	 */
	private JFrame _mainFrame;
	/**
	 * Panel holding the Game Borad
	 */
	private JPanel _lotusPanel;
	/**
	 * A button responsible for skipping
	 */
	private JButton _skipButton;
	/**
	 * A label responsible for displaying who's turn it is currently
	 */
	private JLabel _turnDisplay;
	/**
	 * ImageIcon for black buttons
	 */
	private ImageIcon _black;
	/**
	 * ImageIcon for black selected buttons
	 */
	private ImageIcon _blackSelected;
	/**
	 * ImageIcon for white buttons
	 */
	private ImageIcon _white;
	/**
	 * ImageIcon for white selected buttons	
	 */
	private ImageIcon _whiteSelected;
	/**
	 * ImageIcon for empty buttons
	 */
	private ImageIcon _empty;
	/**
	 * ImageIcon for empty selected buttons
	 */
	private ImageIcon _emptySelected;
	/**
	 * ImageIcon for the trampoline
	 */
	private ImageIcon _trampoline;
	/**
	 * ImageIcon for the finish positions
	 */
	private ImageIcon _finish;
	/**
	 * ImageIcon for the rollover Icon of finish
	 */
	private ImageIcon _finishSelected;
	/**
	 * ImageIcon for PressedIcon
	 */
	private ImageIcon _press;
	/**
	 * ImageIcon for the skip button when it is not displayed
	 */
	private ImageIcon _play;
	/**
	 * ImageIcon for the skip button when it is displayed
	 */
	private ImageIcon _skip;
	/**
	 * ImageIcon for the skip button on rollover
	 */
	private ImageIcon _skipSelected;
	/**
	 * ImageIcon for empty spaces
	 */
	private ImageIcon _none;
	/**
	 * ImageIcon displaying that it is currently black's turn
	 */
	private ImageIcon _blackTurn;
	/**
	 * ImageIcon displaying that it is currently white's turn
	 */
	private ImageIcon _whiteTurn;
	/**
	 * A reference to the button manager
	 */
	private ButtonManager _buttonManager;
	/**
	 * A listener for the skip button
	 */
	private SkipButtonListener _skipListener;
	/**
	 * A state where the skip button is displayed on true and not on false
	 */
	private boolean _skipButtonState;
	
	/**
	 * Creates a new instance of GUI
	 */
	public GUI() {
		_game = new Game();
		_skipListener = new SkipButtonListener(_game, this);
		_skipButtonState = false;
		
		_black = new ImageIcon("src/gui/black.gif");
		_blackSelected = new ImageIcon("src/gui/blackSelected.gif");
		_white = new ImageIcon("src/gui/white.gif");
		_whiteSelected = new ImageIcon("src/gui/whiteSelected.gif");
		_empty = new ImageIcon("src/gui/empty.gif");
		_emptySelected = new ImageIcon("src/gui/emptySelected.gif");
		_trampoline = new ImageIcon("src/gui/trampoline.gif");
		_finish = new ImageIcon("src/gui/finish.gif");
		_finishSelected = new ImageIcon("src/gui/finishSelected.gif");
		_press = new ImageIcon("src/gui/press.gif");
		_play = new ImageIcon("src/gui/play.gif");
		_skip = new ImageIcon("src/gui/skip.gif");
		_skipSelected = new ImageIcon("src/gui/skipSelected.gif");
		_none = new ImageIcon("src/gui/none.gif");
		_blackTurn = new ImageIcon("src/gui/blackTurn.gif");
		_whiteTurn = new ImageIcon("src/gui/whiteTurn.gif");
		
		//Displays tooltips instantly
		ToolTipManager.sharedInstance().setInitialDelay(0);
		int noDismiss = Integer.MAX_VALUE; 
		//Makes sure tooltips are always present on rollover
		ToolTipManager.sharedInstance().setDismissDelay(noDismiss);
	}
	
	/**
	 * Sets up the GUI
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		_mainFrame = new JFrame("Lotus");
		_mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_mainFrame.getContentPane().setBackground(Color.GRAY);
		_mainFrame.setLayout(new GridBagLayout());
		_mainFrame.setMinimumSize(new java.awt.Dimension(470,540));
		_buttonManager = new ButtonManager();
		
		_lotusPanel = new LotusPanel();
		_lotusPanel.setBackground(Color.GRAY);
		_lotusPanel.setLayout(new GridLayout(10,9));
		this.lotusButtons();
		
		_mainFrame.add(_lotusPanel);
		_mainFrame.pack();
		//Retrieves the size of the screen this application is started on
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = _mainFrame.getSize().width;
		int height = _mainFrame.getSize().height;
		int x = (screenSize.width-width) / 2;
		int y = (screenSize.height-height) / 2;
		//Places the JFrame at the center of your screen on Startup
		_mainFrame.setLocation(x, y);
		_mainFrame.setVisible(true);
	}
	
	/**
	 * Determines whether it's the end of the game and if it is, proceeds to end the game
	 * and display a Congratulations JDialog
	 * @param playerColor
	 */
	public void endGameStateCheck(Color playerColor) {
		//What happens when it is truely the end of the game
		if(_game.gameOver() == true) {
			_mainFrame.setEnabled(false);
			JDialog Congratulations = new JDialog();
			Congratulations.setAlwaysOnTop(true);
			Congratulations.setTitle("Congratulations!");
			//If after moving the NextPlayer is white black wins the game
			if(playerColor.equals(Color.WHITE)) {
				JLabel whiteWins = new JLabel("BLACK WINS THE GAME!");
				whiteWins.setFont(new Font("Dialog", 1, 35));
				Congratulations.add(whiteWins);
			}
			//If after moving the NextPlayer is black white wins the game
			else {
				JLabel blackWins = new JLabel("WHITE WINS THE GAME!");
				blackWins.setFont(new Font("Dialog", 1, 35));
				Congratulations.add(blackWins);
			}
			Congratulations.pack();
			Congratulations.setResizable(false);
			Congratulations.setLocationRelativeTo(_mainFrame);
			Congratulations.setVisible(true);
		}
		else {
			//Do nothing
		}
	}
	
	/**
	 * Sets up the buttons on the Lotus Panel
	 */
	public void lotusButtons() {	
		//From top left to bottom right, going right, all the buttons and spaces
		//within the game are specified here:
		
		//Grid Position(s) 1
		_turnDisplay = new JLabel();
		this.turnDisplayState();
		_lotusPanel.add(_turnDisplay);
		//Grid Position(s) 2
		this.skipButton();
		//Grid Position(s) 2-4
		this.noButton(2);
		//Grid Position(s) 5
		this.addButton(M6);
		//Grid Position(s) 6-10
		this.noButton(5);
		//Grid Position(s) 11
		this.addButton(M5);
		//Grid Position(s) 12-16
		this.noButton(5);
		//Grid Position(s) 17
		this.addButton(M7);
		//Grid Position(s) 18-21
		this.noButton(4);
		//Grid Position(s) 22
		this.addButton(L1);
		//Grid Position(s) 23
		this.noButton(1);
		//Grid Position(s) 24
		this.addButton(R1);
		//Grid Position(s) 25-27
		this.noButton(3);
		//Grid Position(s) 28
		this.addButton(TRAMPOLINE);
		//Grid Position(s) 29-31
		this.noButton(3);
		//Grid Position(s) 32
		this.addButton(SW1);
		//Grid Position(s) 33-35 
		this.noButton(3);
		//Grid Position(s) 36
		this.addButton(M8);
		//Grid Position(s) 37-38
		this.noButton(2);
		//Grid Position(s) 39
		this.addButton(L2);
		//Grid Position(s) 40
		this.addButton(SB1);
		//Grid Position(s) 41
		this.addButton(SW2);
		//Grid Position(s) 42
		this.addButton(SB2);
		//Grid Position(s) 43
		this.addButton(R2);
		//Grid Position(s) 44-48
		this.noButton(5);
		//Grid Position(s) 49
		this.addButton(SB3);
		//Grid Position(s) 50
		this.addButton(SW3);
		//Grid Position(s) 51
		this.addButton(SB4);
		//Grid Position(s) 52-54
		this.noButton(3);
		//Grid Position(s) 55
		this.addButton(M4);
		//Grid Position(s) 56
		this.noButton(1);
		//Grid Position(s) 57
		this.addButton(L3);
		//Grid Position(s) 58
		this.noButton(1);
		//Grid Position(s) 59
		this.addButton(SW4);
		//Grid Position(s) 60
		this.noButton(1);
		//Grid Position(s) 61
		this.addButton(R3);
		//Grid Position(s) 62
		this.noButton(1);
		//Grid Position(s) 63
		this.addButton(M9);
		//Grid Position(s) 64-67
		this.noButton(4);
		//Grid Position(s) 68
		this.addButton(M1);
		//Grid Position(s) 69-73
		this.noButton(5);
		//Grid Position(s) 74
		this.addButton(M3);
		//Grid Position(s) 75-79
		this.noButton(5);
		//Grid Position(s) 80
		this.addButton(M10);
		//Grid Position(s) 81-85
		this.noButton(5);
		//Grid Position(s) 86
		this.addButton(M2);
		//Grid Position(s) 87
		this.noButton(1);
		//Grid Position(s) 88
		this.addButton(FINISH);
		//Grid Position(s) 89-90
		this.noButton(2);		
		
		_lotusPanel.setPreferredSize(new java.awt.Dimension(450,500));
	}
	
	/**
	 * Sets the TurnDisplay to display who's turn it is currently
	 * This method can be used to update the Turn Display
	 */
	public void turnDisplayState() {
		if(_game.getNextPlayer().getColor().equals(Color.BLACK)) {
			_turnDisplay.setIcon(_blackTurn);
		}
		else {
			_turnDisplay.setIcon(_whiteTurn);
		}
	}
	
	/**
	 * Sets up the Skip Button
	 */
	public void skipButton() {
		_skipButton = new JButton();
		_skipButton.setIcon(_play);
		_skipButton.setRolloverIcon(_play);
		_skipButton.setPressedIcon(_play);
		_skipButton.setBorderPainted(false);
		_skipButton.setContentAreaFilled(false);
		_skipButton.setFocusPainted(false);
		_lotusPanel.add(_skipButton);
	}
	
	/**
	 * Manually sets the Skip Button State
	 * @param state
	 */
	public void skipButtonState(boolean state) {
		//If true displays the skip icon and enable the button
		if(state == true) {
			_skipButton.addActionListener(_skipListener);
			_skipButton.setIcon(_skip);
			_skipButton.setRolloverIcon(_skipSelected);
			_skipButton.setPressedIcon(_press);
			_skipButtonState = true;
		}
		//If false displays the play icon and disables the skip button
		else {
			_skipButton.removeActionListener(_skipListener);
			_skipButton.setIcon(_play);
			_skipButton.setRolloverIcon(_play);
			_skipButton.setPressedIcon(_play);
			_skipButtonState = false;
		}
	}
	
	/**
	 * Retrives the Skip Button State
	 * @return the state of the Skip Button
	 */
	public boolean getSkipButtonState() {
		return _skipButtonState;
	}
	
	/**
	 * Adds a button to the Lotus Panel
	 * @param positionName - the name of the position/button
	 */
	public void addButton(String positionName) {
		ImageIcon startIcon;
		ImageIcon rolloverIcon;
		//Special case for Finish icon
		if(positionName.equals(FINISH)) {
			startIcon = _finish;
			rolloverIcon = _finishSelected;
		}
		//Special case for Trampoline icon
		else if(positionName.equals(TRAMPOLINE)) {
			startIcon = _trampoline;
			rolloverIcon = _trampoline;
		}
		//Empty buttons icons
		else if(_game.getPosition(positionName).getStackOfPieces().getHeight() == 0) {
			startIcon = _empty;
			rolloverIcon = _emptySelected;
		}
		//Black button icons
		else if(_game.getPosition(positionName).getStackOfPieces().getTopPiece().getColor().equals(Color.BLACK)) {
			startIcon = _black;
			rolloverIcon = _blackSelected;
		}
		//White button icons
		else {
			startIcon = _white;
			rolloverIcon = _whiteSelected;
		}
		
		JButton jbutton = new JButton(startIcon);
		
		//Sets the size label
		JLabel size = new JLabel(" " + Integer.toString(_game.getPosition(positionName).getStackOfPieces().getHeight()));
		size.setForeground(Color.GREEN);
		//Makes sure the finish does not have a size label
		if(positionName.equals(FINISH) | positionName.equals(TRAMPOLINE)) {
			//Do Nothing
		}
		else {
			jbutton.add(size);
		}
		
		//Creates the initial tooltips
		_game.getPosition(positionName).getStackOfPieces().getStack().execute(new ToolTipVisitor(), jbutton);
		
		/*
		 * Everything else here sets up the icons and creates the Buttons
		 */
		jbutton.setBorderPainted(false);
		jbutton.setContentAreaFilled(false);
		jbutton.setFocusPainted(false);
		jbutton.setRolloverIcon(rolloverIcon);
		jbutton.setSelectedIcon(rolloverIcon);		
		if(jbutton.getIcon().equals(_trampoline)) {
			jbutton.setPressedIcon(_trampoline);
		}
		else {
			jbutton.setPressedIcon(_press);
		}
		Button button = new Button(positionName, jbutton, size);
		_buttonManager.add(button);
		ButtonListener buttonListener = new ButtonListener(button, _buttonManager, _game, this);
		jbutton.addActionListener(buttonListener);
		button.setButtonListener(buttonListener);
		_lotusPanel.add(jbutton);
	}
	
	/**
	 * Designates amount of empty 50x50 pixel spaces on the board, in sequential left to right order.
	 */
	public void noButton(int amount) {
		int control = 1;
		while(control <= amount) {
			JLabel noButton = new JLabel(_none);			
			_lotusPanel.add(noButton);
			control++;
		}
	}
}