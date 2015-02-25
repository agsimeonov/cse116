package tests;

import org.junit.Before;
import org.junit.Test;

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
import static org.junit.Assert.assertTrue;

import interfaces.IGame;
import interfaces.IPiece;
import interfaces.IPlayer;
import interfaces.IPosition;
import interfaces.IStackOfPieces;


import code.Game;
/*
 * This suite of tests is designed to test the movement of pieces. It does this
 * by checking that:
 * 	-pieces moved from beginning stacks land on the proper positions
 * 	-pieces moved from a given position land on the proper position
 * 	-pieces are properly moved to the FINISH position 
 */
public class MovementTests extends TestParent {

/*
 * BEGIN TESTS FOR PIECES MOVED FROM BEGINNING STACKS
 */
	@Test public void testStartLeft_sb1_l1_(){startLeftTest(SB1, L1);}
	@Test public void testStartLeft_sb2_l1_(){startLeftTest(SB2, L2);}
	@Test public void testStartLeft_sb3_l1_(){startLeftTest(SB3, L3);}
	@Test public void testStartLeft_sb4_l1_(){startLeftTest(SB4, M1);}
	
	@Test public void testStartRight_sb1_l1_(){startRightTest(SB1, R1);}
	@Test public void testStartRight_sb2_l1_(){startRightTest(SB2, R2);}
	@Test public void testStartRight_sb3_l1_(){startRightTest(SB3, R3);}
	@Test public void testStartRight_sb4_l1_(){startRightTest(SB4, M1);}
	
	@Test public void testStartLeft_sw1_l1_(){
		startLeft(SB1);
		startLeftTest(SW1, L1);
		}
	@Test public void testStartLeft_sw2_l1_(){
		startLeft(SB1);
		startLeftTest(SW2, L2);
		}
	@Test public void testStartLeft_sw3_l1_(){
		startLeft(SB1);
		startLeftTest(SW3, L3);
		}
	@Test public void testStartLeft_sw4_l1_(){
		startLeft(SB1);
		startLeftTest(SW4, M1);
		}
	
	@Test public void testStartRight_sw1_l1_(){
		startLeft(SB1);
		startRightTest(SW1, R1);
		}
	@Test public void testStartRight_sw2_l1_(){
		startLeft(SB1);
		startRightTest(SW2, R2);
		}
	@Test public void testStartRight_sw3_l1_(){
		startLeft(SB1);
		startRightTest(SW3, R3);
		}
	@Test public void testStartRight_sw4_l1_(){
		startLeft(SB1);
		startRightTest(SW4, M1);
		}
	

	/*
	 * BEGIN TESTS FOR PIECES MOVED FROM A GIVEN POSITION
	 */
	@Test
	public void testMoveFromStackOfTwoAtPositionM1(){
		startLeft(SB4); //black move
		startLeft(SW4); //white move - white on top of stack of 2 on M1
		startLeft(SB1); //black move so that white moves next
		IPiece piece = getPiece(M1);
		movePiece(M1);			//moves white piece
		IPosition expected = _game.getPosition(M3);
		IPosition actual = piece.getPosition();
		assertTrue("I moved a piece from M1 expecting it to end up on position" +
				"M3 but instead it landed on " + actual, expected == actual);
	}
	@Test
	public void testMovefromStackOfFourAtPositionL3(){
		startLeft(SB4);	//black
		startLeft(SW4);	//white
		startLeft(SB4);	//black- this should move to L3
		startLeft(SW4);	//white- this should move to L3
		startLeft(SB3);	//black- this should move to L3
		startLeft(SW3);	//white- this should move to L3
		startLeft(SB1); //black move so that white moves next
		IPiece piece = getPiece(L3);
		movePiece(L3);			//moves white
		IPosition expected = _game.getPosition(M4);
		IPosition actual = piece.getPosition();
		assertTrue("I moved a piece from a stack of four at L3 expecting it to " +
				"end up on position M4 but instead it landed on "+ actual,
				expected==actual);
	}
	@Test
	public void testMoveFromStackOfFourAtPositionR2(){
		startRight(SB3);	//black
		startRight(SW3);	//white
		startRight(SB3);	//black- this should move to R2
		startRight(SW3);	//white- this should move to R2
		startRight(SB2);	//black- this should move to R2
		startRight(SW2);	//white- this should move to R2
		startLeft(SB1); //black move so that white moves next
		IPiece piece = getPiece(R2);
		movePiece(R2);			//moves white
		IPosition expected = _game.getPosition(M3);
		IPosition actual = piece.getPosition();
		assertTrue("I moved a piece from a stack of four at R2 expecting it to " +
				"end up on position M3 but instead it landed on " + actual
				,expected==actual);
	}
	@Test
	public void testMoveFromStackOfThreeAtPositionM3(){
		startRight(SB4);	//black- one piece on M1
		startRight(SW4);	//white- two pieces on M1
		startRight(SB4);	//black- one on R3
		startRight(SW4);	//white- two on R3
		startRight(SB3);	//black- three on R3
		startLeft(SW4);		//white- one on L2
		startLeft(SB4);		//black- two on L2
		startLeft(SW2);		//white- three on L2
		startLeft(SB3);		//black- four on L2
		movePiece(M1);				//white- first piece to land on M3
		movePiece(R3);				//black- second piece to land on M3
		startLeft(SW1); //white move so that black can move next
		movePiece(L2);				//black- third piece to land on M3
		startLeft(SW2); //white move so that black moves next
		IPiece piece = getPiece(M3);	//this piece was the top of stack at M3
		movePiece (M3);
		IPosition expected =_game.getPosition(M5);
		IPosition actual = piece.getPosition();
		assertTrue("I moved a piece from a stack of three at M3 expecting it to" +
				"end up on position M5 but instead it landed on " + actual
				, expected==actual);
		
	}
	/*
	 * UTILITY METHODS
	 */
	
	/*this is a utility method for the starting tests */
	private void startLeftTest(String startPosition, String endPosition){
		IPosition startPos = _game.getPosition(startPosition);
		IPiece actual = startPos.getStackOfPieces().getTopPiece();
		IPosition expectedEndPos = _game.getPosition(endPosition);
		startLeft(startPosition);
		IPosition actualEndPos = actual.getPosition();
		assertTrue("I moved a piece from "+startPosition +" expecting it to land on " +
				expectedEndPos + " but instead it landed on " + actualEndPos,
				expectedEndPos == actualEndPos);
	}
	/*this is a utility method for the starting tests */
	private void startRightTest(String startPosition, String endPosition){
		IPosition startPos = _game.getPosition(startPosition);
		IPiece actual = startPos.getStackOfPieces().getTopPiece();
		IPosition expectedEndPos = _game.getPosition(endPosition);
		startRight(startPosition);
		IPosition actualEndPos = actual.getPosition();
		assertTrue("I moved a piece from "+startPosition +" expecting it to land on " +
				expectedEndPos + " but instead it landed on " + actualEndPos,
				expectedEndPos == actualEndPos);
	}

	
}
