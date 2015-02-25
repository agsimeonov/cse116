package code;

import java.awt.Color;
import java.util.List;
import lrstruct.LRStruct;
import lrstruct.visitors.StackColorsVisitor;
import lrstruct.visitors.StackHeightVisitor;
import interfaces.IPiece;
import interfaces.IStackOfPieces;

/**
 * Defines a stack that holds Pieces
 * Drivers - Alexander Simeonov, Joseph Mcnamera
 * Navigators - Alexander Simeonov
 * @author Alexander Simeonov
 */
public class StackOfPieces implements IStackOfPieces {
	/**
	 * This is where the Pieces are stored.
	 */
	private LRStruct<IPiece> _stack;

	/**
	 * The constructor for a StackOfPieces, instantiates a new StackOfPieces
	 */
	public StackOfPieces() {
		_stack = new LRStruct<IPiece>();
	}
	
	/**
	 * Accessor method that returns the internal LRStruct
	 * that is used to hold the Pieces
	 * @return Returns this StackOfPieces
	 */
	public LRStruct<IPiece> getStack() {		
		return _stack;
	}
	
	/**
	 * Returns the number of Pieces in the StackOfPieces.  A Stack's Height.
	 * @return Returns the Stack height
	 */
	public int getHeight() {
		return _stack.execute(new StackHeightVisitor<IPiece>(), null);
	}

	/**
	 * Returns the Piece at the top of the StackOfPieces
	 * @return Returns the Top Piece
	 */
	public IPiece getTopPiece() {
		return _stack.getDatum();
	}

	/**
	 * Returns true if there are no Pieces in the StackOfPieces,
	 * otherwise returns false
	 * @return Returns true if the list is empty, and false if it's not.
	 */
	public boolean isEmpty() {
		return this.getHeight() == 0;
	}

	/**
	 * A list of colors representing each piece in this stack from top to bottom
	 * @return Returns a List<Color> containing the colors of the pieces on this stack; the first element of the list is the top element of the stack. 
	 */
	@Override
	public List<Color> getStackColors() {
		return _stack.execute(new StackColorsVisitor(), null);
	}
}