package gui;

import lrstruct.LRStruct;
import lrstruct.visitors.GetButtonVisitor;

/**
 * Knows about all the Game buttons and takes care of different states they are in.
 * Drivers - Alexander Simeonov, Joseph Mcnamera
 * Navigators - Jeremy Fritton, Joseph Mcnamera
 * @author Alexander Simeonov
 */
public class ButtonManager {
	/**
	 * An LRStruct containing all the Buttons
	 */
	private LRStruct<Button> _buttons;
	/**
	 * Variable determining whether there are selected buttons
	 */
	private boolean _hasSelected;
	/**
	 * Boolean determining whether the Game is in the Trampoline State
	 */
	private boolean _trampolineState;
	/**
	 * Holds the selected button
	 */
	private Button _selected;
	/**
	 * Holds the selected final button
	 */
	private Button _selectedFinal;
	/**
	 * Used to hold a second final button when needed
	 */
	private Button _selectedFinalSecond;
	
	/**
	 * Creates a new instance of ButtonManager
	 * Holds all the Buttons
	 */
	public ButtonManager() {
		_buttons = new LRStruct<Button>();
		_hasSelected = false;
		//When there is no selected Button or Final Button, their value is null
		_selected = null;
		_selectedFinal = null;
		_selectedFinalSecond = null;
		_trampolineState = false;
	}
	
	/**
	 * Used to add a button to the Button Manager
	 * @param button - the Button to be added to this Button Manager
	 */
	public void add(Button button) {
		_buttons.insertFront(button);
	}
	
	/**
	 * Retrieves a button based on it's name string
	 * @param name - the name of the button/position
	 * @return Retruns a button holding the specified name
	 */
	public Button getButton(String name) {
		return _buttons.execute(new GetButtonVisitor(), name);
	}
	
	/**
	 * Sets the selected state as selected
	 * @param hasSelected - sets has selected if there is a selected button
	 * @param selected - The Button that was selected
	 * @param selectedFinal - The final button in terms of the selected button
	 * @param selectedFinalSecond - if needed the second final button in terms of the selected button, if not needed set as null
	 */
	public void setHasSelected(boolean hasSelected, Button selected, Button selectedFinal, Button selectedFinalSecond) {
		_hasSelected = hasSelected;
		_selected = selected;
		_selectedFinal = selectedFinal;
		_selectedFinalSecond = selectedFinalSecond;
	}
	
	/**
	 * Sets the selected state as no selected
	 */
	public void setNoSelected() {
		_hasSelected = false;
		_selected.getJButton().setSelected(false);
		_selected = null;
		_selectedFinal.getJButton().setSelected(false);
		_selectedFinal = null;
		if(_selectedFinalSecond == null) {
			//Do Nothing
		}
		else {
			_selectedFinalSecond.getJButton().setSelected(false);
			_selectedFinalSecond = null;
		}		
	}
	
	/**
	 * Returns the selected state as true or false
	 * @return the selected state
	 */
	public Boolean hasSelected() {
		return _hasSelected;
	}
	
	/**
	 * Retrieves the selected Button
	 * @return the selected Button
	 */
	public Button getSelected() {
		return _selected;
	}
	
	/**
	 * Retrieves the Final selected Button
	 * @return the final selected Button
	 */
	public Button getSelectedFinal() {
		return _selectedFinal;
	}
	
	/**
	 * Retrieves the Second final selected Button when needed
	 * @return the second final selected Button
	 */
	public Button getSelectedFinalSecond() {
		return _selectedFinalSecond;
	}
	
	/**
	 * Determines whether a final button has been selected
	 * @param clicked - the button clicked
	 * @return Returns true only if a final button has been selected
	 */
	public Boolean isFinalSelected(Button clicked) {
		if((clicked == _selectedFinal) | (clicked == _selectedFinalSecond)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Retrieves the trampoline state
	 * @return the trampoline state
	 */
	public boolean getTrampolineState() {
		return _trampolineState;
	}
	
	/**
	 * Sets the trampoline state
	 * @param state -true only if currently in trampoline state
	 */
	public void setTrampolineState(boolean state) {
		_trampolineState = state;
	}
}