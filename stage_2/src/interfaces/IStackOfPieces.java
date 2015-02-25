package interfaces;

import java.awt.Color;
import java.util.List;

import lrstruct.LRStruct;

/**
 * An Intefaces for the StackOfPieces
 * Drivers - Alexander Simeonov, Joseph Mcnamera
 * Navigators - Alexander Simeonov
 * @author Carl Alphonce
 */
public interface IStackOfPieces {

	/**
	 * The height of this stack of pieces (i.e. the number of pieces in this stack).
	 */
	public int getHeight();

	/**
	 * @return Return a reference to the top piece on this stack.
	 */
	public IPiece getTopPiece();

	/**
	 * @return Returns true if and only if this stack is empty (i.e. the number of pieces in this stack is zero)
	 */
	public boolean isEmpty();

	/**
	 * @return Returns this LRStruct stack.
	 */
	public LRStruct<IPiece> getStack();
	
	/**
	 * @return Returns a List<Color> containing the colors of the pieces on this stack; the first element of the list
	 * is the top element of the stack. 
	 */
	public List<Color> getStackColors();
}