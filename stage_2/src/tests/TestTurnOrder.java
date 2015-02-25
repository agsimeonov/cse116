package tests;

import static interfaces.IBoardPositionNames.*;
import junit.framework.Assert;
import interfaces.IPlayer;
import java.awt.Color;

import org.junit.Test;

public class TestTurnOrder extends TestParent {


	@Test
	public void testNextAfterFirstMove() {
		startLeft(SB4); // BLACK turn
		
		Color expected = Color.WHITE;
		Color actual = _game.getNextPlayer().getColor();
		Assert.assertEquals("After Black moves first, getNextPlayer() should return a Player whose color is WHITE, it was not.",expected,actual);
	}
	
	@Test
	public void testNextAfterSecondMove() {
		
		IPlayer expected = _game.getNextPlayer();
		
		startLeft(SB3); // BLACK turn
		startLeft(SW4); // WHITE turn
		
		IPlayer actual = _game.getNextPlayer();
		Assert.assertEquals("After both players move, it should be Black's turn again, but it was not.",expected,actual);	
	}
	
	@Test
	public void testNextAfterThirdMove() {
		startLeft(SB4); // BLACK turn
		
		IPlayer expected = _game.getNextPlayer();
		
		startLeft(SW4); // WHITE turn
		startLeft(SB4); // BLACK turn
		
		IPlayer actual = _game.getNextPlayer();
		Assert.assertEquals("After three moves, it should be White's turn again, but it was not.",expected,actual);
		
	}

	
	@Test(expected = IllegalStateException.class)
	public void testBlackTriesMovingTwice() {
		startLeft(SB4); // BLACK turn
		startLeft(SB4); // WHITE turn -- should throw InvalidMoveException
		Assert.fail("Black successfully moved twice at the start, this should not be allowed to happen.");
	}

	@Test(expected = IllegalStateException.class)
	public void testWhiteTriesMovingTwice() {
		startLeft(SB4); // BLACK turn
		startLeft(SW4); // WHITE turn
		startLeft(SW4); // BLACK turn -- should throw InvalidMoveExcception
		Assert.fail("White successfully moved twice after Black moved once, this should not be allowed to happen.");
	}

}
