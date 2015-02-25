package code;

import java.awt.Color;
import static interfaces.IBoardPositionNames.*;
import interfaces.IGame;
import interfaces.IPiece;
import interfaces.IPosition;

/**
 * Keeps track of a Piece's Color and its Position
 * Drivers - Alexander Simeonov, Jeremy Fritton
 * Navigators - Alexander Simeonov, Jeremy Fritton, Joseph Mcnamera
 * @author Alexander Simeonov
 */
public class Piece implements IPiece {
	/**
	 * The Piece's Color
	 */
	private Color _color;
	/**
	 * The Piece's Position
	 */
	private IPosition _position;
	/**
	 * The Game associated with the Piece
	 */
	private IGame _game;
	
	/**
	 * The Constructor for the Piece, instantiates a new Piece
	 * @param color Is the new Piece's Color
	 * @param position Is where the Piece starts out
	 * @param game Is the Game that will be associated with
	 *             the newly instantiated Piece
	 */
	public Piece(Color color, IPosition position, IGame game) {
		_color = color;
		_position = position;
		_game = game;
	}
	
	/**
	 * The accessor method for the _color instance variable
	 * @return Returns the Piece's Color.
	 */
	public Color getColor() {
		return _color;
	}

	/**
	 * Moves the Current Piece
	 * Takes into consideration the special cases when moving the Piece
	 */
	public void move() {
		//Makes sure that the Piece is at the top of it's stack
		if(this.getPosition().getStackOfPieces().getTopPiece() == this) {
			//Special case, and it's final location
			if((this.getPosition() == _game.getPosition(L1)) & 
					(this.getPosition().getStackOfPieces().getHeight() == 1)) {
				this.setPosition(_game.getPosition(L2));
				_game.setNextPlayer();
			}
			//Special case, and it's final location
			else if((this.getPosition() == _game.getPosition(L1)) & 
					(this.getPosition().getStackOfPieces().getHeight() == 2)) {
				this.setPosition(_game.getPosition(L3));
				_game.setNextPlayer();
			}
			//Special case, and it's final location
			else if((this.getPosition() == _game.getPosition(L2)) & 
					(this.getPosition().getStackOfPieces().getHeight() == 1)) {
				this.setPosition(_game.getPosition(L3));
				_game.setNextPlayer();
			}
			//Special case, and it's final location
			else if((this.getPosition() == _game.getPosition(R1)) & 
					(this.getPosition().getStackOfPieces().getHeight() == 1)) {
				this.setPosition(_game.getPosition(R2));
				_game.setNextPlayer();
			}
			//Special case, and it's final location
			else if((this.getPosition() == _game.getPosition(R1)) & 
					(this.getPosition().getStackOfPieces().getHeight() == 2)) {
				this.setPosition(_game.getPosition(R3));
				_game.setNextPlayer();
			}
			//Special case, and it's final location
			else if((this.getPosition() == _game.getPosition(R2)) & 
					(this.getPosition().getStackOfPieces().getHeight() == 1)) {
				this.setPosition(_game.getPosition(R3));
				_game.setNextPlayer();
			}
			//Movement procedure for non-special locations
			else {
				//Trampoline functionality
				if(this.finalLocation() == 8) {
					int useTrampoline = this.finalLocation() + this.getPosition().getStackOfPieces().getHeight();
					this.setPosition(this.locate().locatePosition(useTrampoline));
					_game.setNextPlayer();
				}
				//Movement procedure for everything else
				else {
					this.setPosition(this.locate().locatePosition(this.finalLocation()));
					_game.setNextPlayer();
				}
			}
		}
		//If the piece is no on top of it's stack
		else {
			throw new IllegalStateException("This piece is not currently on the top of it's stack");
		}		
	}
	
	/**
	 * Accessor method that returns the Piece's current Position.
	 * @return The Piece's current Position
	 */
	@Override
	public IPosition getPosition() {
		return _position;
	}

	/**
	 * Moves a piece from it's current position to the one specified
	 * Is a mutator Method that sets _position
	 * @param position - Position you want to move this Piece to
	 */
	public void setPosition(IPosition position) {
		this.getPosition().getStackOfPieces().getStack().removeFront();
		_position = position;
		this.getPosition().addPiece(this);
		
	}
	
	/**
	 * Returns the Piece's current Position
	 * @return Returns the Location of this Position
	 */
	@Override
	public Location locate() {
		return new Location(this.getPosition(), _game);
	}
	
	/**
	 * Moves the Piece.
	 * Determines the number of the current Location, as well as the
	 * height of the current StackOfPieces, and calls finalLocation()
	 * on the Location returned by this.locate() to do the actual moving
	 * @return Returns the Location this Piece is bound to move to
	 */
	public int finalLocation() {
		int current = this.locate().getLocation();
		int amount = this.getPosition().getStackOfPieces().getHeight();
		return this.locate().finalLocation(current, amount);
	}
}