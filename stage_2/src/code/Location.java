package code;

import static interfaces.IBoardPositionNames.*;
import interfaces.IGame;
import interfaces.IPosition;

/**
 * Manages the location of each legal position.  
 * This class assigns numbers to each legal position, 
 * representing the order in which they appear in on the board.
 * Assigns 0 to starting position - A number in place for GUI purposes.
 * Drivers - Alexander Simeonov
 * Navigators - Alexander Simeonov, Jeremy Fritton, Joseph Mcnamera
 * @author Alexander Simeonov
 */
public class Location {
	/**
	 * Integer that specifies the current location
	 */
	private int _location;
	/**
	 * A reference to the Game
	 */
	private IGame _game;
	/**
	 * A reference to the position associated with this location.
	 */
	private IPosition _position;
	
	/**
	 * The constructor for Location, instantiates a new Location.
	 * @param position Is a reference to the Position that will be
	 *                 associated with the newly instantiated Location.
	 * @param game     Is a reference to the Game that will be associated
	 * 				   with the newly instantiated Location
	 */
	public Location(IPosition position, IGame game) {
		_game = game;
		_position = position;
		if(_game.getPosition(SB1) == _position) {
			_location = 0;
		}
		else if(_game.getPosition(SB2) == _position) {
			_location = 0;
		}
		else if(_game.getPosition(SB3) == _position) {
			_location = 0;
		}
		else if(_game.getPosition(SB4) == _position) {
			_location = 0;
		}
		else if(_game.getPosition(SW1) == _position) {
			_location = 0;
		}
		else if(_game.getPosition(SW2) == _position) {
			_location = 0;
		}
		else if(_game.getPosition(SW3) == _position) {
			_location = 0;
		}
		else if(_game.getPosition(SW4) == _position) {
			_location = 0;
		}
		else if(_game.getPosition(L1) == _position) {
			_location = 1;
		}
		else if(_game.getPosition(L2) == _position) {
			_location = 2;
		}
		else if(_game.getPosition(L3) == _position) {
			_location = 3;
		}
		else if(_game.getPosition(R1) == _position) {
			_location = 1;
		}
		else if(_game.getPosition(R2) == _position) {
			_location = 2;
		}
		else if(_game.getPosition(R3) == _position) {
			_location = 3;
		}
		else if(_game.getPosition(M1) == _position) {
			_location = 4;
		}
		else if(_game.getPosition(M2) == _position) {
			_location = 5;
		}
		else if(_game.getPosition(M3) == _position) {
			_location = 6;
		}
		else if(_game.getPosition(M4) == _position) {
			_location = 7;
		}
		else if(_game.getPosition(TRAMPOLINE) == _position) {
			_location = 8;
		}
		else if(_game.getPosition(M5) == _position) {
			_location = 9;
		}
		else if(_game.getPosition(M6) == _position) {
			_location = 10;
		}
		else if(_game.getPosition(M7) == _position) {
			_location = 11;
		}
		else if(_game.getPosition(M8) == _position) {
			_location = 12;
		}
		else if(_game.getPosition(M9) == _position) {
			_location = 13;
		}
		else if(_game.getPosition(M10) == _position) {
			_location = 14;
		}
		else if(_game.getPosition(FINISH) == _position) {
			_location = 15;
		}
		else {
			throw new IllegalArgumentException("Invalid Position");
		}
	}
	
	/**
	 * Returns the Position of the current Location.
	 * @param location Is which Location to find the Position of
	 * @return A position which corresponds to the current Location
	 */
	public IPosition locatePosition(int location) {
		if(location == 4) {
			return _game.getPosition(M1);
		}
		else if(location == 5) {
			return _game.getPosition(M2);
		}
		else if(location == 6) {
			return _game.getPosition(M3);
		}
		else if(location == 7) {
			return _game.getPosition(M4);
		}
		else if(location == 8) {
			return _game.getPosition(TRAMPOLINE);
		}
		else if(location == 9) {
			return _game.getPosition(M5);
		}
		else if(location == 10) {
			return _game.getPosition(M6);
		}
		else if(location == 11) {
			return _game.getPosition(M7);
		}
		else if(location == 12) {
			return _game.getPosition(M8);
		}
		else if(location == 13) {
			return _game.getPosition(M9);
		}
		else if(location == 14) {
			return _game.getPosition(M10);
		}
		else if(location == 15) {
			return _game.getPosition(FINISH);
		}
		else {
			throw new IllegalArgumentException("Invalid Position");
		}
	}
	
	/**
	 * An accessor method to get _location
	 * @return An integer that specifies the current Location
	 */
	public int getLocation(){
		return _location;
	}
	
	/**
	 * Computes the final Location, with a safety-net at 15 which is the 
	 * finish position
	 * @param current Is the current Location
	 * @param amount Is the stack height, or how many spaces the Piece
	 *               has to move.
	 * @return Is where the Piece needs to move to.
	 */
	public int finalLocation(int current, int amount) {
		if((current + amount) > 15) {
			return 15;
		}
		else {
			return current + amount;
		}
	}
}