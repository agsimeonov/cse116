package code;

import interfaces.IPiece;
import interfaces.IPosition;
import interfaces.IStackOfPieces;

/**
 * Defines a Position, which contains a StackOfPieces
 * and has a name.  Generally position simply creates
 * a stack of pieces with a certain name.
 * Drivers - Alexander Simeonov, Jeremy Fritton
 * Navigators - Alexander Simeonov, Jeremy Fritton
 * @author Alexander Simeonov
 */
public class Position implements IPosition {
	/**
	 * The StackOfPieces for the current Position
	 */
	private IStackOfPieces _stackOfPieces;
	/**
	 * The current Position's name
	 */
	private String _name;

	/**
	 * The constructor for a Position, instantiates a new Position
	 * @param name Specifies what the name for the Position should be
	 */
	public Position(String name) {
		_name = name;
		_stackOfPieces = new StackOfPieces();
	}
	
	/**
	 * Accessor method that return's the Position's name
	 * @return Returns the Position's name
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * Accessor method that returns the Position's StackOfPieces
	 * @return Returns the Position's Stack of pieces
	 */
	public IStackOfPieces getStackOfPieces() {
		return _stackOfPieces;
	}

	/**
	 * Adds a Piece to the front of the Position's StackOfPieces
	 */
	public void addPiece(IPiece piece) {
		this.getStackOfPieces().getStack().insertFront(piece);
	}
}