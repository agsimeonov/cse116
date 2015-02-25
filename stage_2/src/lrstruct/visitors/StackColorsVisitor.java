package lrstruct.visitors;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import interfaces.IPiece;
import lrstruct.LRStruct;
import lrstruct.LRStruct.IAlgo;
/**
 * A Lotus Game Visitor, that finds the colors of all the pieces in a stack.
 * Drivers - Alexander Simeonov, Jeremy Fritton
 * Navigators - Alexander Simeonov, Jeremy Fritton
 * @author Alexander Simeonov
 */

public class StackColorsVisitor implements IAlgo<Object, IPiece, List<Color>> {
	/**
	 * A List for colors in a stack of pieces
	 */
	private List<Color> _color;
	
	/**
	 * Creates a new instance of StackColorsVisitor
	 * Creates the List of Colors
	 */
	public StackColorsVisitor() {
		_color = new ArrayList<Color>();
	}
	
	/**
	 * Returns the list of colors for empty state
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - The current LRStruct
	 * @param _ - unused
	 * @return The List of colors
	 */
	@Override
	public List<Color> emptyCase(LRStruct<IPiece> host, Object _) {
		return _color;
	}
	
	/**
	 * Returns the list of colors for non-empty state
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - The current LRStruct
	 * @param _ - unused
	 * @return The List of colors
	 */
	@Override
	public List<Color> nonEmptyCase(LRStruct<IPiece> host, Object _) {
		_color.add(host.getDatum().getColor());
		host.getRest().execute(this, _);
		return _color;
	}
}