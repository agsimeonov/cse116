package code;

import java.awt.Color;
import java.util.HashSet;
import interfaces.IGame;
import interfaces.IPlayer;
import interfaces.IPosition;
import static interfaces.IBoardPositionNames.*;
import lrstruct.LRStruct;
import lrstruct.visitors.GameOverVisitor;
import lrstruct.visitors.GetPositionVisitor;

/**
 * Creates a new instance of the Lotus Game which holds all positions, 10 black pieces, 10 white pieces,
 * a black and white player, and a turn number for each player.
 * Drivers - Alexander Simeonov, Jeremy Fritton
 * Navigators - Alexander Simeonov, Jeremy Fritton, Joseph Mcnamera
 * @author Alexander Simeonov
 */
public class Game implements IGame {
	/**
	 * This is an LRStruct of all possible positions on the Board
	 */
	private LRStruct<IPosition> _positions;
	/**
	 * The Black Color used for each white Piece
	 */
	private Color _black = Color.BLACK;
	/**
	 * The Black Color used for each black Piece
	 */
	private Color _white = Color.WHITE;
	/**
	 * A Player referencing the current Player
	 */
	private IPlayer _player;
	/**
	 * The black Player
	 */
	private IPlayer _b;
	/**
	 * The white Player
	 */
	private IPlayer _w;
	/**
	 * The turn number for the black Player
	 */
	private int _blackTurn;
	/**
	 * The turn number for the white Player
	 */
	private int _whiteTurn;
	
	/**
	  * Creates a new instance of the Lotus Game which holds all positions, 10 black pieces, 10 white pieces,
	  * a black and white player, and a turn number for each player.
	 */
	public Game() {
		_blackTurn = 1;
		_whiteTurn = 1;
		
		HashSet<String> name = new HashSet<String>();
		name.add(SB1);
		name.add(SB2);
		name.add(SB3);
		name.add(SB4);
		name.add(SW1);
		name.add(SW2);
		name.add(SW3);
		name.add(SW4);
		name.add(L1);
		name.add(L2);
		name.add(L3);
		name.add(R1);
		name.add(R2);
		name.add(R3);
		name.add(M1);
		name.add(M2);
		name.add(M3);
		name.add(M4);
		name.add(TRAMPOLINE);
		name.add(M5);
		name.add(M6);
		name.add(M7);
		name.add(M8);
		name.add(M9);
		name.add(M10);
		name.add(FINISH);
		
		_positions = new LRStruct<IPosition>();
		for (String n : name) {
			_positions.insertFront(new Position(n));
		}
		
		this.getPosition(SB1).addPiece(new Piece(_black, this.getPosition(SB1), this));
		this.getPosition(SB2).addPiece(new Piece(_black, this.getPosition(SB2), this));
		this.getPosition(SB2).addPiece(new Piece(_black, this.getPosition(SB2), this));
		this.getPosition(SB3).addPiece(new Piece(_black, this.getPosition(SB3), this));
		this.getPosition(SB3).addPiece(new Piece(_black, this.getPosition(SB3), this));
		this.getPosition(SB3).addPiece(new Piece(_black, this.getPosition(SB3), this));
		this.getPosition(SB4).addPiece(new Piece(_black, this.getPosition(SB4), this));
		this.getPosition(SB4).addPiece(new Piece(_black, this.getPosition(SB4), this));
		this.getPosition(SB4).addPiece(new Piece(_black, this.getPosition(SB4), this));
		this.getPosition(SB4).addPiece(new Piece(_black, this.getPosition(SB4), this));
		
		this.getPosition(SW1).addPiece(new Piece(_white, this.getPosition(SW1), this));
		this.getPosition(SW2).addPiece(new Piece(_white, this.getPosition(SW2), this));
		this.getPosition(SW2).addPiece(new Piece(_white, this.getPosition(SW2), this));
		this.getPosition(SW3).addPiece(new Piece(_white, this.getPosition(SW3), this));
		this.getPosition(SW3).addPiece(new Piece(_white, this.getPosition(SW3), this));
		this.getPosition(SW3).addPiece(new Piece(_white, this.getPosition(SW3), this));
		this.getPosition(SW4).addPiece(new Piece(_white, this.getPosition(SW4), this));
		this.getPosition(SW4).addPiece(new Piece(_white, this.getPosition(SW4), this));
		this.getPosition(SW4).addPiece(new Piece(_white, this.getPosition(SW4), this));
		this.getPosition(SW4).addPiece(new Piece(_white, this.getPosition(SW4), this));
		
		_b = new Player(_black, this);
		_w = new Player(_white, this);
		_player = _b;
	}
	
	/**
	 * A method that looks for a location based on it's name string.
	 * @see interfaces.IGame#getPosition(java.lang.String)
	 * @param name - The name of the position you are looking for.
	 * @return The position that holds the name you were looking for.
	 */
	public IPosition getPosition(String name) {
		return _positions.execute(new GetPositionVisitor(), name);
	}
	
	/**
	 * A method that retrieves an LRStruct containing all of the positions in the Game.
	 * @see interfaces.IGame#getAllPositions()
	 * @return An LRStruct containing all of the positions that a Game holds.
	 */
	public LRStruct<IPosition> getAllPositions() {
		return _positions;
	}

	/**
	 * Determines whether the game is over or not.
	 * @see interfaces.IGame#gameOver()
	 * @return Returns true if the game is over, returns false if the game is not over.
	 */
	public boolean gameOver() {
		return this.getPosition(FINISH).getStackOfPieces().getStack().execute(new GameOverVisitor(), null);
	}

	/**
	 * Determines the player who's turn it is currently.
	 * @see interfaces.IGame#getNextPlayer()
	 * @return Returns the current player.
	 */
	public IPlayer getNextPlayer() {
		return _player;
	}
	
	/**
	 * Switches the players so that the next player can play
	 * @see interfaces.IGame#setNextPlayer()
	 */
	public void setNextPlayer() {
		if(_player == _b) {
			_player = _w;
			_blackTurn = _blackTurn + 1;
		}
		else {
			_player = _b;
			_whiteTurn = _whiteTurn + 1;
		}
	}
	
	/**
	 * Gets the current turn number for the black player
	 * @see interfaces.IGame#getBlackTurn()
	 * @return Returns the current turn number for the black player.
	 */
	public int getBlackTurn() {
		return _blackTurn;
	}
	
	/**
	 * Gets the current turn number for the white player
	 * @see interfaces.IGame#getWhiteTurn()
	 * @return Returns the current turn number for the white player.
	 */
	public int getWhiteTurn() {
		return _whiteTurn;
	}
}