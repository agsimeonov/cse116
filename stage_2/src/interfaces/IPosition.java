package interfaces;

/**
 * Interface for the Positions
 * Drivers - Alexander Simeonov, Jeremy Fritton
 * Navigators - Alexander Simeonov, Jeremy Fritton
 * @author Carl Alphonce
 */
public interface IPosition {

	/**
	 * @return Return the stack of pieces on this position.
	 */
	public IStackOfPieces getStackOfPieces();

	/**
	 * @return Returns the name of the stack's position.
	 */
	public String getName();

	/**
	 * @param piece - the name of the piece you are currently using.
	 */
	public void addPiece(IPiece piece);

}