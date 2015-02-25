package lrstruct.visitors;

import interfaces.IPiece;
import java.awt.Color;
import lrstruct.LRStruct;
import lrstruct.LRStruct.IAlgo;

/**
 * A Lotus Game Visitor, that determines whether the game has reached it's end or not.
 * Drivers - Alexander Simeonov
 * Navigators - Jeremy Fritton
 * @author Alexander Simeonov
 */
public class GameOverVisitor implements IAlgo<Object, IPiece, Boolean> {
	/**
	 * LRStruct for the Color Black of Black Pieces
	 */
	private LRStruct<Color> _black;
	/**
	 * LRStruct for the Color White of White Pieces
	 */
	private LRStruct<Color> _white;
	/**
	 * Determines whether the game is over or not
	 */
	private boolean _gameOver;
	
	/**
	 * Creates black and white color LRSructs
	 * Creates a new instance of GameOverVisitor
	 */
	public GameOverVisitor() {
		_black = new LRStruct<Color>();
		_white = new LRStruct<Color>();
		_gameOver = false;
	}
	
	/**
	 * An LRStruct at it's empty state. Determines Game Over State
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - current LRStruct
	 * @param _ - unneeded
	 * @return - The state of the game true - over false - not over
	 */
	@Override
	public Boolean emptyCase(LRStruct<IPiece> host, Object _) {
		return _gameOver;
	}
	
	/**
	 * An LRStruct at it's non-empty state. Determines Game Over State
	 * @see lrstruct.LRStruct.IAlgo#emptyCase(lrstruct.LRStruct, java.lang.Object)
	 * @param host - current LRStruct
	 * @param _ - unneeded
	 * @return - The state of the game true - over false - not over
	 */
	@Override
	public Boolean nonEmptyCase(LRStruct<IPiece> host, Object _) {
		//Takes the Finish position and parses blacks
		if(host.getDatum().getColor().equals(Color.BLACK)) {
			_black.insertFront(host.getDatum().getColor());
			host.getRest().execute(this, null);
		}
		//Takes the Finish position and parses whites
		else {
			_white.insertFront(host.getDatum().getColor());
			host.getRest().execute(this, null);
		}
		if((host.execute(new StackHeightVisitor<IPiece>(), null) < 10)) {
			_gameOver = false;
		}
		else if(_black.execute(new StackHeightVisitor<Color>(), null) == 10) {
			_gameOver = true;
		}
		else if(_white.execute(new StackHeightVisitor<Color>(), null) == 10) {
			_gameOver = true;
		}
		else {
			_gameOver = false;
		}
		return _gameOver;
	}
}