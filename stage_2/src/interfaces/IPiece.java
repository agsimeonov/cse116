package interfaces;

import java.awt.Color;
import code.Location;

/**
 * An interface for Pieces
 * Drivers - Alexander Simeonov, Jeremy Fritton
 * Navigators - Alexander Simeonov, Jeremy Fritton, Joseph Mcnamera
 * @author Carl Alphonce
 */
public interface IPiece {

	/**
	 * Get the color of this piece.
	 */
	public Color getColor();

	/**
	 * Move this piece from its current position, as many spaces as its stack is high.
	 */
	public void move();

	/**
	 * @return Return a reference to the position that this piece is on.
	 */
	public IPosition getPosition();

	/**
	 * @param position - the name of the position you are setting the piece in.
	 */
	public void setPosition(IPosition position);

	/**
	 * @return Returns the final location of the piece.
	 */
	public int finalLocation();

	/**
	 * @return Returns the current location of the piece.
	 */
	public Location locate();

}