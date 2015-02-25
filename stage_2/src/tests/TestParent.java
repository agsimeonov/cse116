package tests;

import interfaces.IGame;
import interfaces.IPiece;

import org.junit.After;
import org.junit.Before;

import code.Game;

public class TestParent {

	protected IGame _game;
	
	@Before
	public void parentSetUp(){
		_game = new Game();
	}
	
	@After
	public void tearDown(){
		_game = null;
	}
	
	protected void startLeft(String pos){
		_game.getNextPlayer().startLeft(_game.getPosition(pos));
	}
	
	protected void startRight(String pos){
		_game.getNextPlayer().startRight(_game.getPosition(pos));
	}
	
	protected void movePiece(String pos){
		_game.getPosition(pos).getStackOfPieces().getTopPiece().move();
	}
	
	protected IPiece getPiece(String position){
		return _game.getPosition(position).getStackOfPieces().getTopPiece();
	}
	
	
}
