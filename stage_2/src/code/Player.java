package code;

import interfaces.IGame;
import interfaces.IPlayer;
import interfaces.IPosition;
import static interfaces.IBoardPositionNames.*;
import java.awt.Color;

import lrstruct.visitors.CheckMobilityVisitor;

/**
 * Defines a Player, which is associated with a Game
 * and has a Color.
 * Drivers - Alexander Simeonov, Jeremy Fritton, Joseph Mcnamera
 * Navigators - Alexander Simeonov, Jeremy Fritton
 * @author Alexander Simeonov
 */
public class Player implements IPlayer {
	/**
	 * The Game associated with the Player
	 */
	private IGame _game;
	/**
	 * The Player's Color
	 */
	private Color _color;
	
	/**
	 * The constructor for a Player, instantiates a new Player
	 * @param color Is the Color associated with the Player
	 * @param game Is the Game associated with the Player
	 */
	public Player(Color color, IGame game) {
		_game = game;
		_color = color;
	}
	
	/**
	 * An accessor method that returns the Color associated
	 * with the Player
	 * @return Returns the color of this player
	 */
	public Color getColor() {
		return _color;
	}

	/**
	 * Moves a Piece out of the starting area, onto the board.
	 * This method moves the Piece to the left branch
	 */
	@Override
	public void startLeft(IPosition startPos) {
		//Makes sure Black can not use white Pieces, and White can not use Black Pieces, and that the Mobility is True
		if((((this.getColor().equals(Color.BLACK)) & (_game.getPosition(SW1) == startPos)) | 
				((this.getColor().equals(Color.BLACK)) & (_game.getPosition(SW2) == startPos)) | 
				((this.getColor().equals(Color.BLACK)) & (_game.getPosition(SW3) == startPos)) | 
				((this.getColor().equals(Color.BLACK)) & (_game.getPosition(SW4) == startPos)) | 
				((this.getColor().equals(Color.WHITE)) & (_game.getPosition(SB1) == startPos)) | 
				((this.getColor().equals(Color.WHITE)) & (_game.getPosition(SB2) == startPos)) | 
				((this.getColor().equals(Color.WHITE)) & (_game.getPosition(SB3) == startPos)) | 
				((this.getColor().equals(Color.WHITE)) & (_game.getPosition(SB4) == startPos))) &
				(_game.getAllPositions().execute(new CheckMobilityVisitor(), _game) == true)) {
			throw new IllegalStateException("Player Color and Starting Positon Color mismatch");
		}
		//Takes care of special cases and moving Left
		else {
			//Makes sure this only works for starting positions
			if((startPos == (_game.getPosition(SB1))) | (startPos == (_game.getPosition(SB2))) |
					(startPos == (_game.getPosition(SB3))) |(startPos == (_game.getPosition(SB4))) |
					(startPos == (_game.getPosition(SW1))) |(startPos == (_game.getPosition(SW2))) |
					(startPos == (_game.getPosition(SW3))) |(startPos == (_game.getPosition(SW4)))) {
				//Special case
				if(startPos.getStackOfPieces().getHeight() == 1) {
					startPos.getStackOfPieces().getTopPiece().setPosition(_game.getPosition(L1));
					_game.setNextPlayer();
				}
				//Special case
				else if(startPos.getStackOfPieces().getHeight() == 2) {
					startPos.getStackOfPieces().getTopPiece().setPosition(_game.getPosition(L2));
					_game.setNextPlayer();
				}
				//Special case
				else if(startPos.getStackOfPieces().getHeight() == 3) {
					startPos.getStackOfPieces().getTopPiece().setPosition(_game.getPosition(L3));
					_game.setNextPlayer();
				}
				//Moving left
				else {
					startPos.getStackOfPieces().getTopPiece().setPosition(_game.getPosition(M1));
					_game.setNextPlayer();
				}
			}
			//When the position this is used on is not a starting position
			else {
				throw new IllegalArgumentException("Invalid starting position");
			}
		}		
	}

	/**
	 * Moves a Piece out of the starting area, onto the board.
	 * This method moves the Piece to the right branch
	 */
	@Override
	public void startRight(IPosition startPos) {
		//Makes sure Black can not use white Pieces, and White can not use Black Pieces, and that the Mobility is True
		if((((this.getColor().equals(Color.BLACK)) & (_game.getPosition(SW1) == startPos)) | 
				((this.getColor().equals(Color.BLACK)) & (_game.getPosition(SW2) == startPos)) | 
				((this.getColor().equals(Color.BLACK)) & (_game.getPosition(SW3) == startPos)) | 
				((this.getColor().equals(Color.BLACK)) & (_game.getPosition(SW4) == startPos)) | 
				((this.getColor().equals(Color.WHITE)) & (_game.getPosition(SB1) == startPos)) | 
				((this.getColor().equals(Color.WHITE)) & (_game.getPosition(SB2) == startPos)) | 
				((this.getColor().equals(Color.WHITE)) & (_game.getPosition(SB3) == startPos)) | 
				((this.getColor().equals(Color.WHITE)) & (_game.getPosition(SB4) == startPos))) &
				(_game.getAllPositions().execute(new CheckMobilityVisitor(), _game) == true)) {
			throw new IllegalStateException("Player Color and Starting Positon Color mismatch");
		}
		//Takes care of special cases and moving right
		else {
			//Makes sure this only works for starting positions
			if((startPos == (_game.getPosition(SB1))) | (startPos == (_game.getPosition(SB2))) |
					(startPos == (_game.getPosition(SB3))) |(startPos == (_game.getPosition(SB4))) |
					(startPos == (_game.getPosition(SW1))) |(startPos == (_game.getPosition(SW2))) |
					(startPos == (_game.getPosition(SW3))) |(startPos == (_game.getPosition(SW4)))) {
				//Special case
				if(startPos.getStackOfPieces().getHeight() == 1) {
					startPos.getStackOfPieces().getTopPiece().setPosition(_game.getPosition(R1));
					_game.setNextPlayer();
				}
				//Special case
				else if(startPos.getStackOfPieces().getHeight() == 2) {
					startPos.getStackOfPieces().getTopPiece().setPosition(_game.getPosition(R2));
					_game.setNextPlayer();
				}
				//Special case
				else if(startPos.getStackOfPieces().getHeight() == 3) {
					startPos.getStackOfPieces().getTopPiece().setPosition(_game.getPosition(R3));
					_game.setNextPlayer();
				}
				//Moving right
				else {
					startPos.getStackOfPieces().getTopPiece().setPosition(_game.getPosition(M1));
					_game.setNextPlayer();
				}
			}
			//When the position this is used on is not a starting position
			else {
				throw new IllegalArgumentException("Invalid starting position");
			}
		}		
	}

	/**
	 * Allows the Player to skip a turn. Can only be used if the Player has
	 * no Pieces that are able to move
	 */
	@Override
	public void skipTurn() {
		if(_game.getAllPositions().execute(new CheckMobilityVisitor(), _game) == true) {
			throw new IllegalStateException("Can not Skip turn while still mobile");
		}
		else {
			_game.setNextPlayer();
		}		
	}
}