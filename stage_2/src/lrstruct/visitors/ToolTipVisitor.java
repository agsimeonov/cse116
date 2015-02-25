package lrstruct.visitors;

import java.awt.Color;

import javax.swing.JButton;

import interfaces.IPiece;
import lrstruct.LRStruct;
import lrstruct.LRStruct.IAlgo;

/**
 * Creates tooltips for a Button, which display the contents of a stack
 * @author Alexander Simeonov
 */
public class ToolTipVisitor implements IAlgo<JButton, IPiece, JButton> {
	/**
	 * Simply a control number for a while loop
	 */
	private int _control;
	/**
	 * The ToolTip String
	 */
	private String _toolTip;
	
	/**
	 * Creates a new instance of ToolTipVisitor
	 * Sets the initial control as 1
	 */
	public ToolTipVisitor() {
		_control = 1;
	}
	
	/**
	 * Returns no tooltip on empty Sate
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - this LRStruct
	 * @param button - button on which to place the tooltip on
	 * @return A Button with a brand new tooltip
	 */
	@Override
	public JButton emptyCase(LRStruct<IPiece> host, JButton button) {
		button.setToolTipText("");
		return button;
	}
	
	/**
	 * Returns a tooltip on non-empty Sate showing the composition of a stack
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - this LRStruct
	 * @param button - button on which to place the tooltip on
	 * @return A Button with a brand new tooltip
	 */
	@Override
	public JButton nonEmptyCase(LRStruct<IPiece> host, JButton button) {
		//Only active on the first pass
		while(_control == 1) {
			//Begins setting up the tooltip for a stack with a black to piece
			if(host.getDatum().getColor().equals(Color.BLACK)) {
				_toolTip = "<html><center><font size = 2>";
				_toolTip = _toolTip + "&#9679";
				_control++;
				host.getRest().execute(this, button);
				_toolTip = _toolTip + "</html>";
			}
			//Begins setting up the tooltip for a stack with a white to piece
			else {
				_toolTip = "<html><center><font size = 2>";
				_toolTip = _toolTip + "&#9675";
				_control++;
				host.getRest().execute(this, button);
				_toolTip = _toolTip + "</html>";
			}
		}
		//Only active after first pass
		if(_control == 1) {
			//Do Nothing
		}
		//Sets up the rest of the tooltip displaying contents in the stack
		else {
			_toolTip = _toolTip + "<br>";
			if(host.getDatum().getColor().equals(Color.BLACK)) {
				_toolTip = _toolTip + "&#9679";
				host.getRest().execute(this, button);
			}
			else {
				_toolTip = _toolTip + "&#9675";
				host.getRest().execute(this, button);
				
			}
		}
		_toolTip = _toolTip + "</font></center></html>";
		button.setToolTipText(_toolTip);
		return button;
	}
}