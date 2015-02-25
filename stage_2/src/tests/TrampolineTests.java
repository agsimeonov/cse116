
/*
 * Trampoline tests for lotus game
 * 
 * @author Yavuz Selim Yilmaz(yavuzsel@buffalo.edu)
 * @date 02/16/2010
 */
package tests;

import static interfaces.IBoardPositionNames.*;
import static org.junit.Assert.assertTrue;
import interfaces.IPiece;
import interfaces.IPosition;

import org.junit.Test;

public class TrampolineTests extends TestParent {

	@Test public void testTrampolineIfPieceAtL2WithAStackOfSize6MovesToM10() {
		
		//First lets create a stack of size 6 at L2
		startLeft(SB4); // BLACK -- to M1(1)
		startLeft(SW4); // WHITE -- to M1(2)
		startLeft(SB4); // BLACK -- to L3(1)
		startLeft(SW4); // WHITE -- to L3(2)
		startLeft(SB4); // BLACK -- to L2(1)
		startLeft(SW4); // WHITE -- to L2(2)
		startLeft(SB3); // BLACK -- to L3(3)
		startLeft(SW3); // WHITE -- to L3(4)
		startLeft(SB3); // BLACK -- to L2(3)
		startLeft(SW3); // WHITE -- to L2(4)
		startLeft(SB2); // BLACK -- to L2(5)
		startLeft(SW2); // WHITE -- to L2(6) -- should jump on trampoline

		startLeft(SB1); // BLACK -- to L1(1) -- to make it White's turn again
		
		//Lets test if the top piece jumps to M10 over the trampoline
		IPiece piece = getPiece(L2);
		movePiece(L2);
		IPosition expected = _game.getPosition(M10);
		IPosition actual = piece.getPosition();
		
		assertTrue("A stack of size 6 at L2 must have jump to M10 but it moved to " + actual, expected == actual);
	}
	
	@Test public void testTrampolineIfPieceAtL3WithAStackOfSize5MovesToM9() {
		//First lets create a stack of size 5 at L3
		startLeft(SB4); // BLACK -- to M1(1)
		startLeft(SW4); // WHITE -- to M1(2)
		startLeft(SB4); // BLACK -- to L3(1)
		startLeft(SW4); // WHITE -- to L3(2)
		startLeft(SB3); // BLACK -- to L3(3)
		startLeft(SW3); // WHITE -- to L3(4)
		startLeft(SB3); // BLACK -- to L2(1)
		startRight(SW3);// WHITE -- to R2(1)
		movePiece(L2);  // BLACK -- to L3(5) -- ready to jump
		startRight(SW1);// WHITE -- to R1(1)
		
		//Lets test if the top piece jumps to M9 over the trampoline
		IPiece piece = getPiece(L3);
		movePiece(L3);
		IPosition expected = _game.getPosition(M9);
		IPosition actual = piece.getPosition();
		
		assertTrue("A stack of size 5 at L3 must have jump to M9 but it moved to " + actual, expected == actual);
	}
	
	@Test public void testTrampolineIfPieceAtR2WithAStackOfSize6MovesToM10() {
		//First lets create a stack of size 6 at R2
		startRight(SB4); // BLACK -- to M1(1)
		startRight(SW4); // WHITE -- to M1(2)
		startRight(SB4); // BLACK -- to R3(1)
		startRight(SW4); // WHITE -- to R3(2)
		startRight(SB4); // BLACK -- to R2(1)
		startRight(SW4); // WHITE -- to R2(2)
		startRight(SB3); // BLACK -- to R3(3)
		startRight(SW3); // WHITE -- to R3(4)
		startRight(SB3); // BLACK -- to R2(3)
		startRight(SW3); // WHITE -- to R2(4)
		startRight(SB2); // BLACK -- to R2(5)
		startRight(SW2); // WHITE -- to R2(6) -- should jump on trampoline

		startLeft(SB1); // BLACK -- to L1(1) -- to make it White's turn again
		
		//Lets test if the top piece jumps to M10 over the trampoline
		IPiece piece = getPiece(R2);
		movePiece(R2);
		IPosition expected = _game.getPosition(M10);
		IPosition actual = piece.getPosition();
		
		assertTrue("A stack of size 6 at R2 must have jump to M10 but it moved to " + actual, expected == actual);
	}
	
	@Test public void testTrampolineIfPieceAtR3WithAStackOfSize5MovesToM9() {
		//First lets create a stack of size 5 at R3
		startRight(SB4); // BLACK -- to M1(1)
		startRight(SW4); // WHITE -- to M1(2)
		startRight(SB4); // BLACK -- to R3(1)
		startRight(SW4); // WHITE -- to R3(2)
		startRight(SB3); // BLACK -- to R3(3)
		startRight(SW3); // WHITE -- to R3(4)
		startRight(SB3); // BLACK -- to R2(1)
		startLeft(SW3);// WHITE -- to L2(1)
		movePiece(R2);  // BLACK -- to R3(5) -- ready to jump
		startLeft(SW1);// WHITE -- to L1(1)
		
		//Lets test if the top piece jumps to M9 over the trampoline
		IPiece piece = getPiece(R3);
		movePiece(R3);
		IPosition expected = _game.getPosition(M9);
		IPosition actual = piece.getPosition();
		
		assertTrue("A stack of size 5 at R3 must have jump to M9 but it moved to " + actual, expected == actual);
	}
	
	
	@Test public void testTrampolineIfPieceAtM1WithAStackOfSize4MovesToM8() {
		//First lets create a stack of size 4 at M1
		startLeft(SB4); // BLACK -- to M1(1)
		startLeft(SW4); // WHITE -- to M1(2)
		startLeft(SB3); // BLACK -- to L3(1)
		startRight(SW3);// WHITE -- to R3(1)
		movePiece(L3);  // BLACK -- to M1(3)
		movePiece(R3);  // WHITE -- to M1(4) -- ready to jump
		startLeft(SB1); // BLACK -- to L1(1) -- just to make it White's turn
		
		//Lets test if the top piece jumps to M8 over the trampoline
		IPiece piece = getPiece(M1);
		movePiece(M1);
		IPosition expected = _game.getPosition(M8);
		IPosition actual = piece.getPosition();
		
		assertTrue("A stack of size 4 at M1 must have jump to M8 but it moved to " + actual, expected == actual);
	}
	
	@Test public void testTrampolineIfPieceAtM2WithAStackOfSize3MovesToM7() {
		//First lets create a stack of size 3 at M2
		startLeft(SB4); // BLACK -- to M1(1)
		startLeft(SW3); // WHITE -- to L3(1)
		movePiece(M1);  // BLACK -- to M2(1)
		startLeft(SW3); // WHITE -- to L2(1)
		startLeft(SB2); // BLACK -- to L2(2)
		startLeft(SW2); // WHITE -- to L2(3)
		startRight(SB1);// BLACK -- to R1(1)
		movePiece(L2);  // WHITE -- to M2(2)
		movePiece(L2);  // BLACK -- to M1(1)
		startRight(SW1);// WHITE -- to R1(2)
		movePiece(M1);  // BLACK -- to M2(3) -- ready to jump
		movePiece(R1);  // WHITE -- to R3(1) -- Black's turn again 
		
		//Lets test if the top piece jumps to M10 over the trampoline
		IPiece piece = getPiece(M2);
		movePiece(M2);
		IPosition expected = _game.getPosition(M7);
		IPosition actual = piece.getPosition();
		
		assertTrue("A stack of size 3 at M2 must have jump to M7 but it moved to " + actual, expected == actual);
	}
	
	@Test public void testTrampolineIfPieceAtM3WithAStackOfSize2MovesToM6() {
		//First lets create a stack of size 2 at M3
		startLeft(SB4); // BLACK -- to M1(1)
		startLeft(SW4); // WHITE -- to M1(2)
		startLeft(SB3); // BLACK -- to L3(1)
		movePiece(M1);  // WHITE -- to M3(1)
		movePiece(L3);  // BLACK -- to M1(2)
		startLeft(SW1); // WHITE -- to L1(1)
		movePiece(M1);  // BLACK -- to M3(2) -- ready to jump
		movePiece(L1);  // WHITE -- to L2(1) -- Black's turn again
		
		//Lets test if the top piece jumps to M6 over the trampoline
		IPiece piece = getPiece(M3);
		movePiece(M3);
		IPosition expected = _game.getPosition(M6);
		IPosition actual = piece.getPosition();
		
		assertTrue("A stack of size 2 at M3 must have jump to M6 but it moved to " + actual, expected == actual);
	}
	
	@Test public void testTrampolineIfPieceAtM4WithAStackOfSize1MovesToM5() {
		//First lets create a stack of size 1 at M4
		startLeft(SB4); // BLACK -- to M1(1)
		startLeft(SW4); // WHITE -- to M1(2)
		startLeft(SB4); // BLACK -- to L3(1)
		startRight(SW4);// WHITE -- to R3(1)
		movePiece(L3);  // BLACK -- to M1(3)
		startLeft(SW1); // WHITE -- to L1(1)
		movePiece(M1);  // BLACK -- to M4(1) -- ready to jump
		startLeft(SW4); // WHITE -- to L2(1) -- Black's move again
		
		//Lets test if the top piece jumps to M10 over the trampoline
		IPiece piece = getPiece(M4);
		movePiece(M4);
		IPosition expected = _game.getPosition(M5);
		IPosition actual = piece.getPosition();
		
		assertTrue("A stack of size 1 at M4 must have jumped to M5 but instead it moved to " + actual, expected == actual);
	}
	
}
