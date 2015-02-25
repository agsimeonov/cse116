package lrstruct.visitors;

import java.awt.Color;
import java.util.HashSet;

import static interfaces.IBoardPositionNames.*;
import interfaces.IGame;
import interfaces.IPosition;
import lrstruct.LRStruct;
import lrstruct.LRStruct.IAlgo;

/**
 * A Lotus Game Visitor that returns false only if either player has no pieces of their own they can possibly move
 * Drivers - Alexander Simeonov, Joseph Mcnamera
 * Navigators - Alexander Simeonov, Jeremy Fritton, Joseph Mcnamera
 * @author Alexander Simeonov
 */
public class CheckMobilityVisitor implements IAlgo<IGame, IPosition, Boolean> {
	/**
	 * True if movement is possible false if movement is not possible
	 */
	public boolean _canMove;
	/**
	 * A HashSet holding either one or two colors
	 */
	public HashSet<Color> _oneColor;
	
	/**
	 * Sets can move as initially true, holds the HashSet for colors
	 * Creates a new instance of CheckMobilityVisitor
	 */
	public CheckMobilityVisitor() {
		_canMove = true;
		_oneColor = new HashSet<Color>();
	}
	
	/**
	 * An LRStruct at it's empty state. Determines Mobility
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - current LRStruct
	 * @param game - reference to the game
	 * @return returns whether movement is currently possible
	 */
	@Override
	public Boolean emptyCase(LRStruct<IPosition> host, IGame game) {
			return _canMove;
	}
	
	/**
	 * An LRStruct at it's non-empty state. Determines Mobility
	 * @see lrstruct.LRStruct.IAlgo#nonEmptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - current LRStruct
	 * @param game - reference to the game
	 * @return returns whether movement is currently possible
	 */
	@Override
	public Boolean nonEmptyCase(LRStruct<IPosition> host, IGame game) {
		//Skips the trampoline and finish positions
		if((host.getDatum() == game.getPosition(TRAMPOLINE)) | (host.getDatum() == game.getPosition(FINISH))) {
			host.getRest().execute(this, game);
		}
		else {
			//For the current black player
			if(game.getNextPlayer().getColor().equals(Color.BLACK)) {
				//Returns true if any of the starting positions have pieces
				if((game.getPosition(SB1).getStackOfPieces().getHeight() > 0) | (game.getPosition(SB2).getStackOfPieces().getHeight() > 0) | 
						(game.getPosition(SB3).getStackOfPieces().getHeight() > 0) | (game.getPosition(SB4).getStackOfPieces().getHeight() > 0)) {
					_canMove = true;
				}
				//Fills up the HashSet wit the color of all top pieces
				else {
					if(host.getDatum().getStackOfPieces().getHeight() > 0) {
						_oneColor.add(host.getDatum().getStackOfPieces().getTopPiece().getColor());
						host.getRest().execute(this, game);
					}
					else {
						host.getRest().execute(this, game);
					}
				}
				//Determines whether movement is possible based on the contents of the HasSet
				if(_oneColor.contains(Color.WHITE) & !(_oneColor.contains(Color.BLACK))) {
					_canMove = false;
				}
				else {
					_canMove = true;
				}
			}
			//For the current white player
			else if(game.getNextPlayer().getColor().equals(Color.WHITE)) {
				//Returns true if any of the starting positions have pieces
				if((game.getPosition(SW1).getStackOfPieces().getHeight() > 0) | (game.getPosition(SW2).getStackOfPieces().getHeight() > 0) | 
						(game.getPosition(SW3).getStackOfPieces().getHeight() > 0) | (game.getPosition(SW4).getStackOfPieces().getHeight() > 0)) {
					_canMove = true;
				}
				//Fills up the HashSet wit the color of all top pieces
				else {
					if(host.getDatum().getStackOfPieces().getHeight() > 0) {
						_oneColor.add(host.getDatum().getStackOfPieces().getTopPiece().getColor());
						host.getRest().execute(this, game);
					}
					else {
						host.getRest().execute(this, game);
					}
				}
				//Determines whether movement is possible based on the contents of the HasSet
				if(_oneColor.contains(Color.BLACK) & !(_oneColor.contains(Color.WHITE))) {
					_canMove = false;
				}
				else {
					_canMove = true;
				}
			}
			else {
				throw new IllegalArgumentException("The only Player Colors allowed are black and white");
			}
		}		
		return _canMove;		
	}
}