package lrstruct.visitors;

import interfaces.IPosition;
import lrstruct.LRStruct;
import lrstruct.LRStruct.IAlgo;
/**
 * A Lotus Game Visitor, that finds a position based on it's name String.
 * @author Alexander Simeonov
 */
public class GetPositionVisitor implements IAlgo<String, IPosition, IPosition> {
	
	/**
	 * Retrieves a Position based on its name at empty case
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - current LRStruct
	 * @param name - name of the position
	 * @return A Position
	 */
	@Override
	public IPosition emptyCase(LRStruct<IPosition> host, String name) {
		throw new IllegalStateException("The Lotus Game never has no positions.");
	}
	
	/**
	 * Retrieves a Position based on its name at non-empty case
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - current LRStruct
	 * @param name - name of the position
	 * @return A Position
	 */
	@Override
	public IPosition nonEmptyCase(LRStruct<IPosition> host, String name) {
		if(host.getDatum().getName().equals(name)) {
			return host.getDatum();
		}
		else {
			return host.getRest().execute(this, name);
		}
	}
}