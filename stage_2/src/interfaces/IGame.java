package interfaces;

import lrstruct.LRStruct;

/**
 * The Interface for the Game
 * Drivers - Alexander Simeonov, Jeremy Fritton
 * Navigators - Alexander Simeonov, Jeremy Fritton, Joseph Mcnamera
 * @author Carl Alphonce
 */
public interface IGame {

	/**
	 * Get a reference to the position named by the String name.
	 */
	public IPosition getPosition(String name);

	/**
	 * @return Returns true if and only if the game is over.
	 */
	public boolean gameOver();

	/**
	 * Get a reference to the player whose turn it is next.
	 */
	public IPlayer getNextPlayer();

	/**
	 * Set the reference to the player whose turn it is next.
	 */
	public void setNextPlayer();

	/**
	 * @return Returns the Black Turn number.
	 */
	public int getBlackTurn();

	/**
	 * @return Returns the White Turn number.
	 */
	public int getWhiteTurn();

	/**
	 * @return Returns the LRStruct containing all of the positions in the game.
	 */
	public LRStruct<IPosition> getAllPositions();

}