package tests;

import static interfaces.IBoardPositionNames.*;
import static interfaces.IBoardPositionNames.SB2;
import static interfaces.IBoardPositionNames.SB3;
import static interfaces.IBoardPositionNames.SB4;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestSkipTurn extends TestParent {

	@Before
	public void setUp() {
		List<String> blackList = Arrays.asList(SB1, SB2, SB3, SB4);
		List<String> whiteList = Arrays.asList(SW1, SW2, SW3, SW4);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <= i; j++) {
				startLeft(blackList.get(i)); // BLACK turn
				startLeft(whiteList.get(i)); // WHITE turn
			}
		}

	}

	@Test
	public void testBlackCanSkip() {
		try {
			_game.getNextPlayer().skipTurn();
		} catch (IllegalStateException e) {
			Assert.fail("After White copies all Black's moves, Black should be allowed to skip.");
		}
	}


	@Test public void testBlackMovesL1() {testBlackCanMoveWhite(L1);}
	@Test public void testBlackMovesL2() {testBlackCanMoveWhite(L2);}
	@Test public void testBlackMovesL3() {testBlackCanMoveWhite(L3);}
	@Test public void testBlackMovesM1() {testBlackCanMoveWhite(M1);}
	
	private void testBlackCanMoveWhite(String pos) {
		
		try{
			movePiece(pos);
		} catch (IllegalStateException e){
			Assert.fail("After White copies all Black's moves, Black should be allowed to move White's piece.");	
		}

	}

}
