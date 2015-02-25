package lrstruct.visitors;

import lrstruct.LRStruct;
import lrstruct.LRStruct.IAlgo;
import gui.Button;

/**
 * A Lotus Game GUI Visitor, that finds a button based on it's name String.
 * Drivers - Alexander Simeonov
 * Navigators - Jeremy Fritton
 * @author Alexander Simeonov
 */
public class GetButtonVisitor implements IAlgo<String, Button, Button> {
	
	/**
	 * Retrieves a Button based on its name at empty case
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - current LRStruct
	 * @param name - name of the button
	 * @return A Button
	 */
	@Override
	public Button emptyCase(LRStruct<Button> host, String name) {
		throw new IllegalStateException("The Lotus Game never has no buttons.");
	}
	
	/**
	 * Retrieves a Button based on its name at non-empty case
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - current LRStruct
	 * @param name - name of the button
	 * @return A Button
	 */
	@Override
	public Button nonEmptyCase(LRStruct<Button> host, String name) {		
		if(host.getDatum().getPositionName().equals(name)) {
			return host.getDatum();
		}
		else {
			return host.getRest().execute(this, name);
		}
	}
}