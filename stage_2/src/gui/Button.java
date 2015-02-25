package gui;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Holds the name of each button corresponding to it's position, the JButton, and it's corresponding JLabel.
 * Drivers - Alexander Simeonov, Joseph Mcnamera
 * Navigators - Alexander Simeonov
 * @author Alexander Simeonov
 */
public class Button {
	/**
	 * The Position Name associated with this Button
	 */
	private String _positionName;
	/**
	 * The JButton associated with this Button
	 */
	private JButton _jbutton;
	/**
	 * The JLabel associated with this Button
	 */
	private JLabel _sizeLabel;
	/**
	 * The ButtonListener associated with this Button
	 */
	private ButtonListener _buttonListener;
	
	/**
	 * Holds the name of each button corresponding to it's position, the JButton, and it's corresponding JLabel.
	 * @param positionName - the Name of the position a JButton represents
	 * @param jbutton - The JButton which Button holds
	 * @param sizeLabel - The JLabel of each button displaying the stack height number
	 */
	public Button(String positionName, JButton jbutton, JLabel sizeLabel) {
		_positionName = positionName;
		_jbutton = jbutton;
		_sizeLabel = sizeLabel;
		_buttonListener = null;
	}
	
	/**
	 * Retrieves the position name of this Button
	 * @return The position name of this button
	 */
	public String getPositionName() {
		return _positionName;
	}
	
	/**
	 * Retrieves the JButton associated with this Button
	 * @return The JButton associated with this Button
	 */
	public JButton getJButton() {
		return _jbutton;
	}
	
	/**
	 * Retrieves the JLabel associated with this Button
	 * @param label - the JLabel associated with this Button
	 */
	public void setSizeLabelText(String label) {
		_sizeLabel.setText(label);
	}
	
	/**
	 * Sets the ButtonListener for this Button
	 * @param buttonListener - the new ButtonListener for this Button
	 */
	public void setButtonListener(ButtonListener buttonListener) {
		_buttonListener = buttonListener;
	}
	
	/**
	 * Retrieves the ButtonListener for this Button
	 * @return The ButtonListener for this Button
	 */
	public ButtonListener getButtonListener() {
		return _buttonListener;
	}
}