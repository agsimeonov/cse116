package gui;

import java.awt.event.ActionEvent;

import code.Game;

/**
 * A listener for the Skip button
 * Drivers - Alexander Simeonov
 * @author Alexander Simeonov
 */
public class SkipButtonListener implements java.awt.event.ActionListener {
	/**
	 * A reference to the Game
	 */
	private Game _game;
	/**
	 * A reference to the GUI
	 */
	private GUI _gui;
	
	/**
	 * Creates a new instance of SkipButtonListener
	 * @param game - a reference to the game
	 * @param gui - a reference to the GUI
	 */
	public SkipButtonListener(Game game, GUI gui) {
		_game = game;
		_gui = gui;
	}
	
	/**
	 * Skips the turn and makes sure the Skip Button State is false afterwards
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @param click - a left mouse button click
	 */
	@Override
	public void actionPerformed(ActionEvent click) {
		_game.getNextPlayer().skipTurn();
		_gui.skipButtonState(false);
		_gui.turnDisplayState();
	}
}