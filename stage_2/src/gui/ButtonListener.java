package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import lrstruct.visitors.CheckMobilityVisitor;
import lrstruct.visitors.ToolTipVisitor;
import static interfaces.IBoardPositionNames.*;

import code.Game;

/**
 * Creates a new instance of the Lotus Game which holds all positions, 10 black pieces, 10 white pieces,
 * a black and white player, and a turn number for each player.
 * Drivers - Alexander Simeonov
 * Navigators - Jeremy Fritton, Joseph Mcnamera
 * @author Alexander Simeonov
 */
public class ButtonListener implements java.awt.event.ActionListener {
	/**
	 * A reference to the Game
	 */
	private Game _game;
	/**
	 * Used to deal with the Button this ButtonListener is associated with 
	 */
	private Button _button;
	/**
	 * Manages all of the Buttons, and helps this Listener access them
	 */
	private ButtonManager _buttonManager;
	/**
	 * A reference to the GUI
	 */
	private GUI _gui;
	/**
	 * ImageIcon for black pieces
	 */
	private ImageIcon _black;
	/**
	 * ImageIcon for black selected pieces
	 */
	private ImageIcon _blackSelected;
	/**
	 * ImageIcon for white pieces
	 */
	private ImageIcon _white;
	/**
	 * ImageIcon for white selected pieces
	 */	
	private ImageIcon _whiteSelected;
	/**
	 * ImageIcon for empty stacks
	 */
	private ImageIcon _empty;
	/**
	 * ImageIcon for empty selected stacks
	 */
	private ImageIcon _emptySelected;
	/**
	 * ImageIcon for the trampoline
	 */
	private ImageIcon _trampoline;
	/**
	 * ImageIcon for the trampoline when jumping is due
	 */
	private ImageIcon _trampolineJump;
	/**
	 * ImageIcon for a final(where a piece is going to be moved to) Positions
	 */
	private ImageIcon _final;
	/**
	 * ImageIcon for a selected final(where a piece is going to be moved to) Positions
	 */
	private ImageIcon _finalRollover;
	/**
	 * ImageIcon for the finish position
	 */
	private ImageIcon _finish;
	/**
	 * ImageIcon for the selected finish position
	 */
	private ImageIcon _finishSelected;
	/**
	 * ImageIcon for positions which have nothing on them
	 */
	private ImageIcon _none;
	
	/**
	 * Creates a new instance of ButtonListener
	 * @param button - the Button associated with this ButtonListener
	 * @param buttonManager - A reference to the ButtonManager which holds all the Buttons
	 * @param game - a reference to the game
	 * @param gui - a reference to the GUI
	 */
	public ButtonListener(Button button, ButtonManager buttonManager, Game game, GUI gui) {
		_game = game;
		_button = button;
		_buttonManager = buttonManager;
		_gui = gui;
		
		_black = new ImageIcon("src/gui/black.gif");
		_blackSelected = new ImageIcon("src/gui/blackSelected.gif");
		_white = new ImageIcon("src/gui/white.gif");
		_whiteSelected = new ImageIcon("src/gui/whiteSelected.gif");
		_empty = new ImageIcon("src/gui/empty.gif");
		_emptySelected = new ImageIcon("src/gui/emptySelected.gif");
		_trampoline = new ImageIcon("src/gui/trampoline.gif");
		_trampolineJump = new ImageIcon("src/gui/trampolineJump.gif");
		_final = new ImageIcon("src/gui/final.gif");
		_finalRollover = new ImageIcon("src/gui/finalRollover.gif");
		_finish = new ImageIcon("src/gui/finish.gif");
		_finishSelected = new ImageIcon("src/gui/finishSelected.gif");
		_none = new ImageIcon("src/gui/none.gif");
	}
	
	/**
	 * Determines what happens with each left mouse click on the buttons
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @param click - a left mouse click
	 */
	@Override
	public void actionPerformed(ActionEvent click) {
		//Checks to see if there is already a selected Button
		if(_buttonManager.hasSelected() == true) {
			//Checks if the selected button is the first selected button, if it is deselects it.
			if(_button.getPositionName().equals(_buttonManager.getSelected().getPositionName())) {
				this.deselect(_buttonManager.getSelected());
				this.deselect(_buttonManager.getSelectedFinal());
				if(_buttonManager.getSelectedFinalSecond() == null) {
					//Do Nothing
				}
				else {
					this.deselect(_buttonManager.getSelectedFinalSecond());
				}
				if(_buttonManager.getTrampolineState() == true) {
					_buttonManager.getButton(TRAMPOLINE).getJButton().setIcon(_trampoline);
					_buttonManager.getButton(TRAMPOLINE).getJButton().setPressedIcon(_trampoline);
					_buttonManager.getButton(TRAMPOLINE).getJButton().setRolloverEnabled(true);
					_buttonManager.setTrampolineState(false);
					_buttonManager.setNoSelected();
				}
				else {
					_buttonManager.setNoSelected();
				}
			}
			//Checks to see if the button you are selecting is one of the final selected buttons
			//===<< NOTE >>=== This statement is very important for it is responsible for movement
			else if((_button.getJButton().isSelected() == true) & 
					!(_button.getPositionName().equals(_buttonManager.getSelected().getPositionName()))) {
				//Course of action for starting positions
				if((_buttonManager.getSelected().getPositionName().equals(SB1)) | 
						(_buttonManager.getSelected().getPositionName().equals(SB2)) | 
						(_buttonManager.getSelected().getPositionName().equals(SB3)) | 
						(_buttonManager.getSelected().getPositionName().equals(SB4)) | 
						(_buttonManager.getSelected().getPositionName().equals(SW1)) | 
						(_buttonManager.getSelected().getPositionName().equals(SW2)) | 
						(_buttonManager.getSelected().getPositionName().equals(SW3)) | 
						(_buttonManager.getSelected().getPositionName().equals(SW4))) {
					//Manages Start Left
					if((_button.getPositionName().equals(L1)) | (_button.getPositionName().equals(L2)) | 
							(_button.getPositionName().equals(L3))) {
						_game.getNextPlayer().startLeft(_game.getPosition(_buttonManager.getSelected().getPositionName()));
						this.labelAndToolTipManagement();
						this.deselect(_buttonManager.getSelected());
						this.deselect(_buttonManager.getSelectedFinal());
						if(_buttonManager.getSelectedFinalSecond() == null) {
							//Do Nothing
						}
						else {
							this.deselect(_buttonManager.getSelectedFinalSecond());
						}
						_buttonManager.setNoSelected();
					}
					//Manages Start Right
					else {
						_game.getNextPlayer().startRight(_game.getPosition(_buttonManager.getSelected().getPositionName()));
						this.labelAndToolTipManagement();
						this.deselect(_buttonManager.getSelected());
						this.deselect(_buttonManager.getSelectedFinal());
						if(_buttonManager.getSelectedFinalSecond() == null) {
							//Do Nothing
						}
						else {
							this.deselect(_buttonManager.getSelectedFinalSecond());
						}
						_buttonManager.setNoSelected();
					}
				}
				//Manages what happens if you are currently in the trampoline state
				else if(_buttonManager.getTrampolineState() == true) {
					_game.getPosition(_buttonManager.getSelected().getPositionName()).getStackOfPieces().getTopPiece().move();
					this.labelAndToolTipManagement();
					this.deselect(_buttonManager.getSelected());
					this.deselect(_buttonManager.getSelectedFinal());
					_buttonManager.getButton(TRAMPOLINE).getJButton().setIcon(_trampoline);
					_buttonManager.getButton(TRAMPOLINE).getJButton().setPressedIcon(_trampoline);
					_buttonManager.getButton(TRAMPOLINE).getJButton().setRolloverEnabled(true);
					_buttonManager.setTrampolineState(false);
					_buttonManager.setNoSelected();
				}
				//Course of action beyond the starting positions
				else {
					_game.getPosition(_buttonManager.getSelected().getPositionName()).getStackOfPieces().getTopPiece().move();
					this.labelAndToolTipManagement();
					this.deselect(_buttonManager.getSelected());
					this.deselect(_buttonManager.getSelectedFinal());
					_buttonManager.setNoSelected();
				}
				//Check determining whether to activate the skip Button or not.
				if(_game.getAllPositions().execute(new CheckMobilityVisitor(), _game) == false) {
					_gui.skipButtonState(true);
				}
				else {
					if(_gui.getSkipButtonState() == true) {
						_gui.skipButtonState(false);
					}
					else {
						//Do Nothing
					}									
				}
				_gui.turnDisplayState();
				_gui.endGameStateCheck(_game.getNextPlayer().getColor());
			}
			//Do nothing if In trampoline state and the button clicked is trampoline
			else if((_buttonManager.getTrampolineState() == true) & (_buttonManager.getButton(TRAMPOLINE) == _button)) {
				//Do nothing
			}
			//Reverse the fist selected.  If the button has never been selected, select it and count it as a first selected.
			else {
				this.deselect(_buttonManager.getSelected());
				this.deselect(_buttonManager.getSelectedFinal());
				if(_buttonManager.getSelectedFinalSecond() == null) {
					//Do Nothing
				}
				else {
					this.deselect(_buttonManager.getSelectedFinalSecond());
				}
				//Manages what happens if you are currently in the trampoline state
				if(_buttonManager.getTrampolineState() == true) {
					_buttonManager.getButton(TRAMPOLINE).getJButton().setIcon(_trampoline);
					_buttonManager.getButton(TRAMPOLINE).getJButton().setPressedIcon(_trampoline);
					_buttonManager.getButton(TRAMPOLINE).getJButton().setRolloverEnabled(true);
					_buttonManager.setTrampolineState(false);
					_buttonManager.setNoSelected();
					this.noSelected();
				}
				else {
					_buttonManager.setNoSelected();
					this.noSelected();
				}
			}
		}
		//Activated if there is no selected Button
		else {
			this.noSelected();
		}
	}
	/**
	 * Defines what happens if there is no selected button.
	 */
	public void noSelected() {
		//Mobility Check - Mobility is true
		if(_game.getAllPositions().execute(new CheckMobilityVisitor(), _game) == true) {
			//Who is playing check
			if(_game.getNextPlayer().getColor().equals(Color.BLACK)) {
				//Isn't empty Check
				if(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() > 0) {
					//Top Piece Color Check
					if(_game.getPosition(_button.getPositionName()).getStackOfPieces().getTopPiece().getColor().equals(Color.BLACK)) {
						this.firstSelected();
					}
					else {
						//Do Nothing
					}
				}
				else {
					//Do Nothing
				}
			}
			//Who is playing check
			else {
				//Isn't empty Check
				if(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() > 0) {
					//Top Piece Color Check
					if(_game.getPosition(_button.getPositionName()).getStackOfPieces().getTopPiece().getColor().equals(Color.WHITE)) {
						this.firstSelected();
					}
					else {
						//Do Nothing
					}
				}
				else {
					//Do Nothing
				}
			}
		}
		//Mobility Check - Mobility is false
		else {
			//Isn't empty Check
			if(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() > 0) {
				this.firstSelected();
			}
			else {
				//Do Nothing
			}
		}			
	}
	
	/**
	 * Defines what happens whenever there has been only one click in a click sequence, resulting in a button being selected
	 */
	public void firstSelected() {
		//firstSelected Routine for Starting Positions
		if((_button.getPositionName().equals(SB1)) | (_button.getPositionName().equals(SB2)) | 
				(_button.getPositionName().equals(SB3)) | (_button.getPositionName().equals(SB4)) | 
				(_button.getPositionName().equals(SW1)) | (_button.getPositionName().equals(SW2)) | 
				(_button.getPositionName().equals(SW3)) | (_button.getPositionName().equals(SW4))) {
			//Determines whether a starting position has any pieces in it
			if(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() > 0) {
				int finalPosition = _game.getPosition(_button.getPositionName()).getStackOfPieces().getTopPiece().finalLocation();
				//Sets up the starting positions
				if(finalPosition == 4) {
					this.finalButton(_buttonManager.getButton(M1));
				}
				else if(finalPosition == 3) {
					this.startPositionsSetUp(L3, R3);
				}
				else if(finalPosition == 2) {
					this.startPositionsSetUp(L2, R2);
				}
				else if(finalPosition == 1) {
					this.startPositionsSetUp(L1, R1);
				}
				else {
					throw new IllegalArgumentException("The starting positions will never have a finalPosition number higher than 4");
				}
			}
			//The starting position no longer has pieces in it thus it is no longer necessary
			else {
				//Do Nothing
			}
		}
		//firstSelected Route for finish
		else if(_button.getPositionName().equals(FINISH)) {
			//Do Nothing
		}
		//firstSelected Routine for all other positions
		else {
			//Determines whether this position has any pieces in it
			if(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() > 0) {
				_button.getJButton().setSelected(true);
				//Special cases
				if((_button.getPositionName().equals(L1)) & 
						(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() == 1)) {
					this.finalButton(_buttonManager.getButton(L2));
				}
				else if((_button.getPositionName().equals(L1)) & 
						(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() == 2)) {
					this.finalButton(_buttonManager.getButton(L3));
				}
				else if((_button.getPositionName().equals(L2)) & 
						(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() == 1)) {
					this.finalButton(_buttonManager.getButton(L3));
				}
				else if((_button.getPositionName().equals(R1)) & 
						(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() == 1)) {
					this.finalButton(_buttonManager.getButton(R2));
				}
				else if((_button.getPositionName().equals(R1)) & 
						(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() == 2)) {
					this.finalButton(_buttonManager.getButton(R3));
				}
				else if((_button.getPositionName().equals(R2)) & 
						(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() == 1)) {
					this.finalButton(_buttonManager.getButton(R3));
				}
				else if(_button.getPositionName().equals(FINISH)) {
					//Do Nothing
				}
				else {
					//Gets the final button (Don't you just love this awesomely big method call?)
					Button finalButton = _buttonManager.getButton(_game.getPosition(_button.getPositionName()).getStackOfPieces().getTopPiece().locate().locatePosition
							(_game.getPosition(_button.getPositionName()).getStackOfPieces().getTopPiece().finalLocation()).getName());
					//Course of action for Trampoline
					if(finalButton.getPositionName().equals(TRAMPOLINE)) {
						int useTrampoline = (_game.getPosition(_button.getPositionName()).getStackOfPieces().getTopPiece().finalLocation() +
								_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight());
						Button jumpToButton = _buttonManager.getButton(_game.getPosition(_button.getPositionName())
								.getStackOfPieces().getTopPiece().locate().locatePosition(useTrampoline).getName());
						_buttonManager.getButton(TRAMPOLINE).getJButton().setIcon(_trampolineJump);
						_buttonManager.getButton(TRAMPOLINE).getJButton().setPressedIcon(_trampolineJump);
						_buttonManager.getButton(TRAMPOLINE).getJButton().setRolloverEnabled(false);
						_buttonManager.setTrampolineState(true);
						this.finalButton(jumpToButton);
					}
					//Course of action for everything else
					else {
						this.finalButton(finalButton);
					}
				}
			}
			else {
				//Do nothing
			}
		}
	}
	
	/**
	 * Deselects the selected buttons and their final positions
	 * @param button - the selected button to deselect
	 */
	public void deselect(Button button) {
		//Checks if the selected position is empty or not
		if(_game.getPosition(button.getPositionName()).getStackOfPieces().getHeight() > 0) {
			Color topColor = _game.getPosition(button.getPositionName()).getStackOfPieces().getTopPiece().getColor();
			//Determines whether to set the jbutton icon as white or as black and sets it's icon based on the color
			if((topColor.equals(Color.BLACK)) & (button.getPositionName().equals(FINISH) == false)) {
				button.getJButton().setIcon(_black);
				button.getJButton().setRolloverIcon(_blackSelected);
				button.getJButton().setSelectedIcon(_blackSelected);
			}
			else if(((topColor.equals(Color.WHITE)) & (button.getPositionName().equals(FINISH) == false))) {
				button.getJButton().setIcon(_white);
				button.getJButton().setRolloverIcon(_whiteSelected);
				button.getJButton().setSelectedIcon(_whiteSelected);
			}
			//Resets the Icons of the Final Position
			else {
				button.getJButton().setIcon(_finish);
				button.getJButton().setRolloverIcon(_finishSelected);
				button.getJButton().setSelectedIcon(_finishSelected);
			}
		}
		//Sets the jbutton icon as empty
		else {
			//Removes starting positions once they become empty
			if(((button.getPositionName().equals(SB1) | button.getPositionName().equals(SB2) | 
			button.getPositionName().equals(SB3) | button.getPositionName().equals(SB4) | 
			button.getPositionName().equals(SW1) |button.getPositionName().equals(SW2) | 
			button.getPositionName().equals(SW3) | button.getPositionName().equals(SW4))) & 
			(_game.getPosition(button.getPositionName()).getStackOfPieces().getHeight() == 0)) {
				button.getJButton().removeAll();
				button.getJButton().setRolloverEnabled(false);
				button.getJButton().setIcon(_none);
				button.getJButton().setSelectedIcon(_none);
				button.getJButton().setPressedIcon(_none);
				//Disables the action listener
				button.getJButton().removeActionListener(_buttonManager.getSelected().getButtonListener());
			}
			//Resets the Icons of the Final Position
			else if(button.getPositionName().equals(FINISH) == true) {
				button.getJButton().setIcon(_finish);
				button.getJButton().setRolloverIcon(_finishSelected);
				button.getJButton().setSelectedIcon(_finishSelected);
			}
			//Resets empty positions
			else {
				button.getJButton().setIcon(_empty);
				button.getJButton().setRolloverIcon(_emptySelected);
				button.getJButton().setSelectedIcon(_emptySelected);
			}
			
		}
	}
	
	/**
	 * Sets up the starting positions
	 * @param finalLeft - The name of the left positions to select as final
	 * @param finalRight - The name of the right positions to select as final
	 */
	public void startPositionsSetUp(String finalLeft, String finalRight) {
		_button.getJButton().setSelected(true);
		
		_buttonManager.getButton(finalLeft).getJButton().setIcon(_final);
		_buttonManager.getButton(finalLeft).getJButton().setRolloverSelectedIcon(_finalRollover);
		_buttonManager.getButton(finalLeft).getJButton().setSelectedIcon(_final);
		_buttonManager.getButton(finalLeft).getJButton().setSelected(true);
		
		_buttonManager.getButton(finalRight).getJButton().setIcon(_final);
		_buttonManager.getButton(finalRight).getJButton().setRolloverSelectedIcon(_finalRollover);
		_buttonManager.getButton(finalRight).getJButton().setSelectedIcon(_final);
		_buttonManager.getButton(finalRight).getJButton().setSelected(true);
		
		_buttonManager.setHasSelected(true, _button, _buttonManager.getButton(finalLeft), _buttonManager.getButton(finalRight));
	}
	
	/**
	 * Sets up the final button for positions other then the starting positions
	 * @param finalButton - the final button for positions other then the starting positions
	 */
	public void finalButton(Button finalButton) {
		_button.getJButton().setSelected(true);
		
		finalButton.getJButton().setIcon(_final);
		finalButton.getJButton().setRolloverSelectedIcon(_finalRollover);
		finalButton.getJButton().setSelectedIcon(_final);
		finalButton.getJButton().setSelected(true);
		
		_buttonManager.setHasSelected(true, _button, finalButton, null);
	}
	
	/**
	 * Manages what happens to a label on movement as well as the tooltip displaying a stack composition
	 */
	public void labelAndToolTipManagement() {
		//Label for the new position of the piece moved showing new stack height
		String newPositionLabel = Integer.toString(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight()); 
		//Label for the old position of the piece moved showing new stack height
		String oldPositionLabel = Integer.toString(_game.getPosition(_buttonManager.getSelected().getPositionName()).getStackOfPieces().getHeight());
		//No labels for the finish positions
		if(_button.getPositionName().equals(FINISH)) {
			if(_game.getPosition(_buttonManager.getSelected().getPositionName()).getStackOfPieces().getHeight() > 9) {
				_buttonManager.getSelected().setSizeLabelText(oldPositionLabel);
			}
			//Only sets label for the old position here
			else {
				_buttonManager.getSelected().setSizeLabelText(" " + oldPositionLabel);
			}
			//Label displaying stack compositions
			_game.getPosition(_buttonManager.getSelected().getPositionName()).getStackOfPieces().getStack().execute(new ToolTipVisitor(), _buttonManager.getSelected().getJButton());
		}
		//Label for everything else
		else {
			//Used for centering purpouses
			if(_game.getPosition(_button.getPositionName()).getStackOfPieces().getHeight() > 9) {
				_button.setSizeLabelText(newPositionLabel);
			}
			else {
				_button.setSizeLabelText(" " + newPositionLabel);
			}
			//Manages labels
			if(_game.getPosition(_buttonManager.getSelected().getPositionName()).getStackOfPieces().getHeight() > 9) {
				_buttonManager.getSelected().setSizeLabelText(oldPositionLabel);
			}
			else {
				_buttonManager.getSelected().setSizeLabelText(" " + oldPositionLabel);
			}
			//Manages tooltips
			_game.getPosition(_button.getPositionName()).getStackOfPieces().getStack().execute(new ToolTipVisitor(), _button.getJButton());
			_game.getPosition(_buttonManager.getSelected().getPositionName()).getStackOfPieces().getStack().execute(new ToolTipVisitor(), _buttonManager.getSelected().getJButton());
		}		
	}
}