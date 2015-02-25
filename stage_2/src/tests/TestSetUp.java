package tests;

import static interfaces.IBoardPositionNames.FINISH;
import static interfaces.IBoardPositionNames.L1;
import static interfaces.IBoardPositionNames.L2;
import static interfaces.IBoardPositionNames.L3;
import static interfaces.IBoardPositionNames.M1;
import static interfaces.IBoardPositionNames.M10;
import static interfaces.IBoardPositionNames.M2;
import static interfaces.IBoardPositionNames.M3;
import static interfaces.IBoardPositionNames.M4;
import static interfaces.IBoardPositionNames.M5;
import static interfaces.IBoardPositionNames.M6;
import static interfaces.IBoardPositionNames.M7;
import static interfaces.IBoardPositionNames.M8;
import static interfaces.IBoardPositionNames.M9;
import static interfaces.IBoardPositionNames.R1;
import static interfaces.IBoardPositionNames.R2;
import static interfaces.IBoardPositionNames.R3;
import static interfaces.IBoardPositionNames.SB1;
import static interfaces.IBoardPositionNames.SB2;
import static interfaces.IBoardPositionNames.SB3;
import static interfaces.IBoardPositionNames.SB4;
import static interfaces.IBoardPositionNames.SW1;
import static interfaces.IBoardPositionNames.SW2;
import static interfaces.IBoardPositionNames.SW3;
import static interfaces.IBoardPositionNames.SW4;
import static interfaces.IBoardPositionNames.TRAMPOLINE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import interfaces.IGame;
import interfaces.IPiece;
import interfaces.IPlayer;
import interfaces.IPosition;
import interfaces.IStackOfPieces;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import code.Game;

/*
 * This suite of tests checks that the game is in a correct configuration at start-up.
 * It does this by checking that,
 *   - each position on the board is created with a stack of of the correct height
 *   - each black start stack has black pieces
 *   - each white start stack has white pieces
 *   - the game is not over
 *   - black plays first
 *   
 *   Note the use of the private methods, like "stackHeightTest", to minimize the amount of 
 *   code duplication in the test.  These private methods are not themselves test methods
 *   (they are not annotated with the "@Test" annotation) but are called, with different 
 *   parameters, by the various individual test methods.
 *   
 *   Note the use of a static import of an interface to ensure that all test classes
 *   use the same names for the board positions.  This is a new use of an import and
 *   a new use of an interface (to define constants).
 *   
 *   This is written as "import static tests.BoardPositionNames.*", which imports all
 *   names defined in the tests.BoardPositionNames interface.  This is appropriate, since
 *   we are using all the constants defined in the interface.  This way we do not have to
 *   list them individually (though Eclipse will quite happily do so for us).  It is
 *   generally a poor practice to import using "*" UNLESS ALL names so imported are
 *   actually used - it can lead so subtle naming conflicts that are difficult to
 *   detect and track down.
 *   
 *   Note too that by using constants (the variables are declared "static" and "final"
 *   for the names, and putting the String names of the positions in just one place, we
 *   can easily change the names of the positions for the tests, if we need to.
 */

public class TestSetUp extends TestParent {
	
	/* BEGIN TESTS FOR STACK HEIGHTS
	 * 
	 *   I've made each test method very focused: each method is testing the height of a
	 *   given stack.
	 *   
	 */
	
	@Test public void testBlackStartStack_sb1_SetUp() { stackHeightTest(SB1,1); }
	@Test public void testBlackStartStack_sb2_SetUp() { stackHeightTest(SB2,2); }
	@Test public void testBlackStartStack_sb3_SetUp() { stackHeightTest(SB3,3); }
	@Test public void testBlackStartStack_sb4_SetUp() { stackHeightTest(SB4,4); }

	@Test public void testWhiteStartStack_sw1_SetUp() { stackHeightTest(SW1,1); }
	@Test public void testWhiteStartStack_sw2_SetUp() { stackHeightTest(SW2,2); }
	@Test public void testWhiteStartStack_sw3_SetUp() { stackHeightTest(SW3,3); }
	@Test public void testWhiteStartStack_sw4_SetUp() { stackHeightTest(SW4,4); }
	
	@Test public void testLeftTrack_l1_SetUp() { stackHeightTest(L1,0); }
	@Test public void testLeftTrack_l2_SetUp() { stackHeightTest(L2,0); }
	@Test public void testLeftTrack_l3_SetUp() { stackHeightTest(L3,0); }
	
	@Test public void testRightTrack_r1_SetUp() { stackHeightTest(R1,0); }
	@Test public void testRightTrack_r2_SetUp() { stackHeightTest(R2,0); }
	@Test public void testRightTrack_r3_SetUp() { stackHeightTest(R3,0); }

	@Test public void testMainTrack_m1_SetUp() { stackHeightTest(M1,0); }
	@Test public void testMainTrack_m2_SetUp() { stackHeightTest(M2,0); }
	@Test public void testMainTrack_m3_SetUp() { stackHeightTest(M3,0); }
	@Test public void testMainTrack_m4_SetUp() { stackHeightTest(M4,0); }
	@Test public void testMainTrack_trampoline_SetUp() { stackHeightTest(TRAMPOLINE,0); }
	@Test public void testMainTrack_m5_SetUp() { stackHeightTest(M5,0); }
	@Test public void testMainTrack_m6_SetUp() { stackHeightTest(M6,0); }
	@Test public void testMainTrack_m7_SetUp() { stackHeightTest(M7,0); }
	@Test public void testMainTrack_m8_SetUp() { stackHeightTest(M8,0); }
	@Test public void testMainTrack_m9_SetUp() { stackHeightTest(M9,0); }
	@Test public void testMainTrack_m10_SetUp() { stackHeightTest(M10,0); }
	@Test public void testMainTrack_finish_SetUp() { stackHeightTest(FINISH,0); }

	/*
	 * This is a utility method which factors out the common code from all of the stack height test methods.
	 */
	private void stackHeightTest(String positionName, int expected) {
		IPosition pos = _game.getPosition(positionName);
		IStackOfPieces s = pos.getStackOfPieces();
		int actual = s.getHeight(); 
		assertTrue("I expected the stack on the position named \""+positionName+"\" to have height "+expected+" at the start of the game, but it had height "+actual,
				expected == actual);
	}

	// END TESTS FOR STACK HEIGHTS
	

	/* BEGIN TESTS FOR START STACKS HAVING PIECES OF THE CORRECT COLORS
	 * 
	 * Here the tests are a bit more compact - each test method checks that ALL the pieces
	 * in a given stack have the correct color.  This makes the coding a bit more compact,
	 * but note that we lose out on information should one of these tests fail: we do not 
	 * know WHICH of the pieces in the stack (which one, or which two, ...) were of the 
	 * wrong color.  This is a tradeoff which you need to weigh in writing your tests.  At
	 * this point in your learning how to test I would prefer you err on making the tests
	 * too narrow and specific.  It is better to do that than to write tests which check too 
	 * much functionality, and difficult to train yourself to break things down to an elemental 
	 * level.
	 *   
	 */
	
	@Test public void testBlackStartStack_sb1_PieceColor() { stackColorTest(SB1, java.awt.Color.BLACK); }
	@Test public void testBlackStartStack_sb2_PieceColor() { stackColorTest(SB2, java.awt.Color.BLACK); }
	@Test public void testBlackStartStack_sb3_PieceColor() { stackColorTest(SB3, java.awt.Color.BLACK); }
	@Test public void testBlackStartStack_sb4_PieceColor() { stackColorTest(SB4, java.awt.Color.BLACK); }

	@Test public void testWhiteStartStack_sw1_PieceColor() { stackColorTest(SW1, java.awt.Color.WHITE); }
	@Test public void testWhiteStartStack_sw2_PieceColor() { stackColorTest(SW2, java.awt.Color.WHITE); }
	@Test public void testWhiteStartStack_sw3_PieceColor() { stackColorTest(SW3, java.awt.Color.WHITE); }
	@Test public void testWhiteStartStack_sw4_PieceColor() { stackColorTest(SW4, java.awt.Color.WHITE); }

	/*
	 * This is a utility method which factors out the common code from all of the stack height test methods.
	 */
	private void stackColorTest(String positionName, java.awt.Color expected) {
		IPosition pos = _game.getPosition(positionName);
		IStackOfPieces s = pos.getStackOfPieces();
		boolean allExpectedColor = true;
		for(Color c : s.getStackColors()) {
			allExpectedColor = allExpectedColor && c.equals(expected);  // check that color is correct
		}
		assertTrue("I expected all the pieces in the stack on the position named \""+positionName+"\" to have color "+expected+" at the start of the game, but this wasn't true.",
				allExpectedColor);	
	}
	
	// END TESTS FOR START STACKS HAVING PIECES OF THE CORRECT COLORS
	
	/* BEGIN OTHER SET-UP TESTS
	 * 
	 * Here we test for:
	 * 
	 *   - the game should not be over
	 *   - the next player should be the player playing black
	 *   
	 */
	
	/*
	 * In this test, note the use of assertFalse rather than assertTrue.  We should have
	 * written this as:
	 * 
	 * public void testGameNotOver() {
	 *     boolean expected = false;
	 *     boolean actual = _game.gameOver();
	 *     assertTrue("...", expected == actual);
	 * }
	 * 
	 * Using assertFalse is a convenience, which perhaps communicates the intent of the test 
	 * better.
	 * 
	 */
	@Test
	public void testGameNotOver() {
		boolean actual = _game.gameOver();
		assertFalse("Game should not be over, but _game.gameOver() returns "+actual, actual);
	}
	
	@Test
	public void testBlackPlaysFirst() {
		Color expected = java.awt.Color.BLACK;
		IPlayer p = _game.getNextPlayer();
		Color actual = p.getColor();
		assertTrue("Black should play first, but the first player's color is "+actual, expected.equals(actual));
	}

	// END OTHER SET-UP TESTS
	
}
