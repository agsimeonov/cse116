package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import interfaces.IBoardPositionNames;
import interfaces.IPiece;
import interfaces.IPlayer;
import interfaces.IPosition;
import interfaces.IStackOfPieces;

import org.junit.Before;
import org.junit.Test;

import static interfaces.IBoardPositionNames.*;

public class EndGameTests extends TestParent {
	
	private IPosition _end;
	
	@Before
	public void setUp(){
		_end = _game.getPosition(IBoardPositionNames.FINISH);
	}
	
	/**
	 * This test makes sure that the game is not over when it first starts.
	 */
	@Test
	public void testGameNotOverInitially(){
		assertFalse("The game should not be over when it just started.",_game.gameOver());
	}
	
	/**
	 * This tests first clears out the starting positions (first player must be Black).
	 * It then moves all of the pieces off of the board, making Black the winner.
	 */
	@Test
	public void testGameOver(){
		
		/*
		 * The following sequence of 101 moves encompasses several factors of game play including moving
		 * an opponent's piece, the trampoline, and moving "beyond" the end of the board. This scenario
		 * was played out, so it is probably not the "fastest" way to end a game of Lotus, but it is one
		 * way that guarantees that Black should win.
		 * 
 		          +1              +2             +3              +4             +5              +6
		     BLACK           WHITE          BLACK           WHITE          BLACK           WHITE
		 0        S4,             S4,            S3L,            S3L,           S3L,            S3L,
		 6        S2L,            S2L,           S2L,            S2L,           S2L,            S2L,
		12        S1L,            S1L,           S1L,            S1L,           S1L,            S1L,
		18        S1L,            S1L,           L1,             M1,            M1,             L2, 
		24        L1,             L1,            L2,             L2,            L1,             L1, 
		30        L2,             L2,            L1,             L1,            L2,             M5,
		36        L1,             M6,            L2,             M7,            L3,             M8, 
		42        L3,             L3,            M1,             L3,            L3,             L3, 
		48        L3,             M2,            M1,             M1,            M3,             M2,
		54        M2,             M2,            M2,             M4,            M3,             M4,
		60        M3,             M5,            M3,             M5,            M3,             M3,
		66        M4,             M3,            M5,             M4,            M8,             M4,
		72        M5,             M5,            M5,             M6,            M5,             M8, 
		78        M6,             M8,            M6,             M6,            M6,             M8, 
		84        M7,             M10,           M7,             M7,            M7,             M7, 
		90        M7,             M9,            M8,             M10,           M8,             M10,
		96        M8,             M10,           M9,             M10,           M9
		
		*/
		
		startLeft(SB4);	startLeft(SW4);	startLeft(SB4);	startLeft(SW4);	startLeft(SB4);	startLeft(SW4);
		startLeft(SB4);	startLeft(SW4);	startLeft(SB3);	startLeft(SW3);	startLeft(SB3);	startLeft(SW3);
		startLeft(SB3);	startLeft(SW3);	startLeft(SB2);	startLeft(SW2);	startLeft(SB2);	startLeft(SW2);
		startLeft(SB1);	startLeft(SW1);	movePiece(L1);	movePiece(M1);	movePiece(M1);	movePiece(L2);
		movePiece(L1);	movePiece(L1);	movePiece(L2);	movePiece(L2);	movePiece(L1);	movePiece(L1);	
		movePiece(L2);	movePiece(L2);	movePiece(L1);	movePiece(L1);	movePiece(L2);	movePiece(M5);	
		movePiece(L1);	movePiece(M6);	movePiece(L2);	movePiece(M7);	movePiece(L3);	movePiece(M8);	
		movePiece(L3);	movePiece(L3);	movePiece(M1);	movePiece(L3);	movePiece(L3);	movePiece(L3);	
		movePiece(L3);	movePiece(M2);	movePiece(M1);	movePiece(M1);	movePiece(M3);	movePiece(M2);	
		movePiece(M2);	movePiece(M2);	movePiece(M2);	movePiece(M4);	movePiece(M3);	movePiece(M4);	
		movePiece(M3);	movePiece(M5);	movePiece(M3);	movePiece(M5);	movePiece(M3);	movePiece(M3);	
		movePiece(M4);	movePiece(M3);	movePiece(M5);	movePiece(M4);	movePiece(M8);	movePiece(M4);	
		movePiece(M5);	movePiece(M5);	movePiece(M5);	movePiece(M6);	movePiece(M5);	movePiece(M8);	
		movePiece(M6);	movePiece(M8);	movePiece(M6);	movePiece(M6);	movePiece(M6);	movePiece(M8);	
		movePiece(M7);	movePiece(M10);	movePiece(M7);	movePiece(M7);	movePiece(M7);	movePiece(M7);	
		movePiece(M7);	movePiece(M9);	movePiece(M8);	movePiece(M10);	movePiece(M8);	movePiece(M10);	
		movePiece(M8);	movePiece(M10);	movePiece(M9);	movePiece(M10);	movePiece(M9);	
		
		assertTrue("After moving all of Black's pieces off of the board, the game should be over, but it was not.",_game.gameOver());
	}
	
}
