package lrstruct.visitors;

import lrstruct.LRStruct;
import lrstruct.LRStruct.IAlgo;
/**
 * A Lotus Game Visitor, that finds the height of a stack.  It is also used in GameOverVisitor
 * Drivers - Alexander Simeonov
 * Navigators - Joseph Mcnamera, Jeremy Fritton
 * @author Alexander Simeonov
 */
public class StackHeightVisitor<E> implements IAlgo<Object, E, Integer> {
	
	/**
	 * Returns 0
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - current LRStruct
	 * @param _ - unused
	 * @return 0
	 */
	@Override
	public Integer 
	emptyCase(LRStruct<E> host, Object _) {
		return 0;
	}
	
	/**
	 * Returns the LRStruct height
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - current LRStruct
	 * @param _ - unused
	 * @return LRStruct height
	 */
	@Override
	public Integer nonEmptyCase(LRStruct<E> host, Object _) {
		return 1 + host.getRest().execute(this, _);
	}
}