package interfaces;

import java.awt.Color;

/**
 * Interface for the Players
 * Drivers - Alexander Simeonov, Jeremy Fritton, Joseph Mcnamera
 * Navigators - Alexander Simeonov, Jeremy Fritton
 * @author Carl Alphonce
 */
public interface IPlayer {

	/**
	 * Get the color that the player is playing.
	 */
	public Color getColor();

	/**
	 * Move from startPos along the left starting track.
	 */
	public void startLeft(IPosition startPos);

	/**
	 * Move from startPos along the right starting track.
	 */
	public void startRight(IPosition startPos);

	/**
	 * Skips his/her turn.
	 */
	public void skipTurn();
}