package tests;

import static org.junit.Assert.assertTrue;
import interfaces.IBoardPositionNames;
import interfaces.IPiece;
import interfaces.IPosition;
import interfaces.IStackOfPieces;

import java.awt.Color;
import java.util.HashSet;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;


import static interfaces.IBoardPositionNames.*;

/*
 * This test suite checks for legal moves made by players 
 * 	- At the beginning of the game
 *	- At any time during the game when a player is taking a turn
 *	- other things?
 *
 *
 */

public class TestLegalMove extends TestParent {
	
	/*
	 * variable _boardPositions maintains a vector of position indices that correspond to the 
	 * position names given in BoardPositionNames.java  
	 */
	private Vector<String> _boardPositions;
	
	@Before
	public void setUp() {
		
		_boardPositions = new Vector<String>();
		//		index:		0											1							// <-- (0-3)
		_boardPositions.add(IBoardPositionNames.SB1); _boardPositions.add(IBoardPositionNames.SB2);	//		Initial stacks 
		//					2											3							//		of Blacks
		_boardPositions.add(IBoardPositionNames.SB3); _boardPositions.add(IBoardPositionNames.SB4);	// <--/
		//		index:		4											5							// <-- (4-7)
		_boardPositions.add(IBoardPositionNames.SW1); _boardPositions.add(IBoardPositionNames.SW2); 	//		Initial stacks
		//					6											7							//		of Whites
		_boardPositions.add(IBoardPositionNames.SW3); _boardPositions.add(IBoardPositionNames.SW4);	// <--/
		//		index:		8											9							// <-- (8-10)
		_boardPositions.add(IBoardPositionNames.L1); _boardPositions.add(IBoardPositionNames.L2); 	//		Left 3 board
		//					10																		//		positions
		_boardPositions.add(IBoardPositionNames.L3);													// <--/
		//		index:		11											12							// <-- (11-13)
		_boardPositions.add(IBoardPositionNames.R1); _boardPositions.add(IBoardPositionNames.R2); 	//		Right 3 board
		//					13																		//		positions
		_boardPositions.add(IBoardPositionNames.R3);													// <--/
		//		index:		14											15							// <-- (14-17)
		_boardPositions.add(IBoardPositionNames.M1); _boardPositions.add(IBoardPositionNames.M2);		//		First 4 middle
		//		index:		16											17							//		positions
		_boardPositions.add(IBoardPositionNames.M3); _boardPositions.add(IBoardPositionNames.M4);		// <--/
		//		index:		18																		// <-- (18) Trampoline				
		_boardPositions.add(IBoardPositionNames.TRAMPOLINE);											// <--/
		//		index:		19											20							// <-- (19-25)
		_boardPositions.add(IBoardPositionNames.M5); _boardPositions.add(IBoardPositionNames.M6);		//
		//		index:		21											22							//
		_boardPositions.add(IBoardPositionNames.M7); _boardPositions.add(IBoardPositionNames.M8);		//		Positions after
		//		index:		23											24							//		Trampoline to
		_boardPositions.add(IBoardPositionNames.M9); _boardPositions.add(IBoardPositionNames.M10);	//		Finish
		//		index:		25																		//
		_boardPositions.add(IBoardPositionNames.FINISH);												// <--/
	}
	
	/*
	 * Here we test for
	 * - Any player with black or white pieces had indeed put a single black or white piece in the right place in the board
	 */
	@Test public void testBlackPlaysFirstLegalMove() { startLeft(SB1); testPlayerPlaysFirstLegalMove(Color.BLACK);	}
	
	/*
	 * Auxiliary function for the above two tests
	 */
	public void testPlayerPlaysFirstLegalMove(Color pieceColor) {
		boolean legalPosition = false;
		
		
		// creating a temporary set positions where the black token might have been placed 
		HashSet<Integer> positionSet = new HashSet<Integer>();
		for (int i = 8; i <= 25; i++ ) {
			if ( isTokenColorOnStackTopMatching(_boardPositions.elementAt(i), pieceColor) )
				positionSet.add(i);
		}
		
		// we are only interested in positions where isTokenColorOnStackTopMatching() returned true 
		// first lets check the size of the set - it should be only one
		if ( positionSet.size() == 1 ) {
			legalPosition = true;
			// also check whether the only black or white piece is not on any position after M1 (i.e. position index 14)
			for ( int i = 15; i <= 25; i++ )
				if ( positionSet.contains(i) ) {
					legalPosition = false;
					break;
				}
		}
		/*
		 * Note: this is a brute force check on all positions in the board. Complexity = O(no. of board positions)
		 * 		The assumption here is that the IPlayer.move() has not been implemented at all.
		 * 
		 * 		For a much faster check, we could turn to the auxiliary method: 
		 * 			- boolean testAnyValidPlayerLegalMove(IPosition currentPosition, IPosition expectedPosition, Color color)
		 * 			which takes in any two positions and a color parameter and returns true if the move is valid, false otherwise
		 * 				But, then this assumes that IPlayer.move() has been implemented correctly (or not)
		 */
		boolean expected = true;
		assertTrue("Black should have made a legal first move and the expected outcome is "+legalPosition, (legalPosition == expected) );
	}

	/*
	 * This is an utility method that returns a boolean true or false given a board position name
	 * and an expected color. The method matches the expected color to the color of the token on the 
	 * top of the stack at that board position
	 */
	private boolean isTokenColorOnStackTopMatching(String positionName, Color expected) {
		
		return _game.getPosition(positionName).getStackOfPieces().getHeight()>0 &&expected.equals(_game.getPosition(positionName).getStackOfPieces().getStackColors().get(0));
	}
	
	@Test (expected=IllegalStateException.class) public void testBlackTriesMovingLeftSW1(){startLeft(SW1);}
	@Test (expected=IllegalStateException.class) public void testBlackTriesMovingLeftSW2(){startLeft(SW2);}
	@Test (expected=IllegalStateException.class) public void testBlackTriesMovingLeftSW3(){startLeft(SW3);}
	@Test (expected=IllegalStateException.class) public void testBlackTriesMovingLeftSW4(){startLeft(SW4);}

	@Test (expected=IllegalStateException.class) public void testBlackTriesMovingRightSW1(){startRight(SW1);}
	@Test (expected=IllegalStateException.class) public void testBlackTriesMovingRightSW2(){startRight(SW2);}
	@Test (expected=IllegalStateException.class) public void testBlackTriesMovingRightSW3(){startRight(SW3);}
	@Test (expected=IllegalStateException.class) public void testBlackTriesMovingRightSW4(){startRight(SW4);}
	
	@Test (expected=IllegalStateException.class) public void testBlackSkipFirst(){_game.getNextPlayer().skipTurn();}
	@Test (expected=IllegalStateException.class) public void testWhiteSkipFirst(){startRight(SB4); _game.getNextPlayer().skipTurn();}

	@Test (expected=IllegalStateException.class)
	public void testTryMovingNonTopPiece(){
		startLeft(SB1); // Black to L1
		IPiece first = getPiece(L1);
		startLeft(SW1); // first is now covered by a white piece
		first.move();
	}
	
}
