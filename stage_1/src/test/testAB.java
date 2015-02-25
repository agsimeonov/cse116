package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import code.GameBoard;
import code.Stack;

import static org.junit.Assert.assertTrue;

public class testAB {
	
	GameBoard _game;
	
	@Before
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	public void setUp() throws Exception {
		_game = new GameBoard();
	}
	
	@After
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	public void tearDown() throws Exception {
		_game = null;
	}
	
	@Test
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	/**
	 * Leaves the GameBoard empty
	 * Uses isEmpty() to check if the GameBoard is empty
	 */
	public void testEmptyGameBoardUsingIsEmpty() {
		boolean expected = true;
		boolean actual = _game.isEmpty();
		assertTrue("I expected the GameBoard to be empty, but it wasn't.", expected == actual);
	}
	
	@Test
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	/**
	 * Adds one Stack to the GameBoard
	 * Calls isEmpty to verify that the GameBoard is not empty
	 */
	public void testAddOneStackCheckGameBoardSizeUsingIsEmpty() {
		Stack st = new Stack();
		_game.add(st);
		boolean expected = false;
		boolean actual = _game.isEmpty();
		assertTrue("I expected the GameBoard to not be empty, but it was.", expected == actual);
	}
	
	@Test
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	/**
	 * Adds one Stack to the GameBoard
	 * Uses size() to verify that the GameBoard has exactly one object in it
	 */
	public void testAddOneStackCheckGameBoardSizeUsingSize() {
		Stack st = new Stack();
		_game.add(st);
		int expected = 1;
		int actual = _game.size();
		assertTrue("I expected the GameBoard to have exactly one object in it, but it didn't.", expected == actual);
	}
	
	@Test
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	/**
	 * Adds one Stack to the GameBoard
	 * Uses contains() to check that the Stack that was added is present.
	 */
	public void testAddOneStackCheckGameBoardUsingContains() {
		Stack st = new Stack();
		_game.add(st);
		boolean expected = true;
		boolean actual = _game.contains(st);
		assertTrue("I expected the GameBoard to contain the Stack that was added to it, but it didn't.", expected == actual);
	}
	@Test
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	/**
	 * Adds one Stack to the GameBoard and then removes it
	 * Uses isEmpty() to verify that the GameBoard is empty
	 */
	public void testAddOneStackRemoveOneStackCheckGameBoardUsingIsEmpty() {
		Stack st = new Stack();
		_game.add(st);
		_game.remove(st);
		boolean expected = true;
		boolean actual = _game.isEmpty();
		assertTrue("I expected the GameBoard to be empty, but it wasn't.", expected == actual);
	}
	
	@Test
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	/**
	 * Adds three Stacks to the GameBoard
	 * Uses size() to check if there are three objects in the GameBoard
	 */
	public void testAddThreeStacksCheckGameBoardUsingSize() {
		Stack st1 = new Stack();
		Stack st2 = new Stack();
		Stack st3 = new Stack();
		_game.add(st1);
		_game.add(st2);
		_game.add(st3);
		int expected = 3;
		int actual = _game.size();
		assertTrue("I expected the GameBoard to have three objects in it, but it didn't.", expected == actual);
	}
	
	@Test
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	/**
	 * Adds three Stacks to the GameBoard
	 * Uses contains() to check if all three are present
	 */
	public void testAddThreeStacksCheckGameBoardUsingContains() {
		Stack st1 = new Stack();
		Stack st2 = new Stack();
		Stack st3 = new Stack();
		_game.add(st1);
		_game.add(st2);
		_game.add(st3);
		boolean expected = true;
		boolean actual = (_game.contains(st1) && _game.contains(st2) && _game.contains(st3));
		assertTrue("I expected the GameBoard to contain all three Stacks that were added to it, but it didn't.", expected == actual);
	}
	
	@Test
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	/**
	 * Adds three Stacks to the GameBoard, then removes the second one
	 * Uses size() to check if there are two objects in the GameBoard
	 */
	public void testAddThreeStacksRemoveOneStackCheckGameBoardUsingSize() {
		Stack st1 = new Stack();
		Stack st2 = new Stack();
		Stack st3 = new Stack();
		_game.add(st1);
		_game.add(st2);
		_game.add(st3);
		_game.remove(st2);
		int expected = 2;
		int actual = _game.size();
		assertTrue("I expected the GameBoard to have two objects in it, but it didn't.", expected == actual);
	}
	
	@Test
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	/**
	 * Adds three Stacks to the GameBoard, then removes the second one
	 * Uses contains() to check if the first and third Stacks are present,
	 * but the second one is not
	 */
	public void testAddThreeStacksRemoveOneStackCheckGameBoardUsingContains() {
		Stack st1 = new Stack();
		Stack st2 = new Stack();
		Stack st3 = new Stack();
		_game.add(st1);
		_game.add(st2);
		_game.add(st3);
		_game.remove(st2);
		boolean expected = true;
		boolean actual = (_game.contains(st1) && !(_game.contains(st2)) && _game.contains(st3));
		assertTrue("I expected the GameBoard to contain the first and third Stacks added to it, but not the second.", expected == actual);
	}
	
	@Test
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	/**
	 * Adds seventeen stacks to the GameBoard
	 * (Seventeen is the number of Stacks to be present in a normal game)
	 * Uses Contains to check if all seventeen Stacks are present
	 */
	public void testAddSeventeenStacksCheckGameBoardUsingContains() {
		Stack st01 = new Stack();
		Stack st02 = new Stack();
		Stack st03 = new Stack();
		Stack st04 = new Stack();
		Stack st05 = new Stack();
		Stack st06 = new Stack();
		Stack st07 = new Stack();
		Stack st08 = new Stack();
		Stack st09 = new Stack();
		Stack st10 = new Stack();
		Stack st11 = new Stack();
		Stack st12 = new Stack();
		Stack st13 = new Stack();
		Stack st14 = new Stack();
		Stack st15 = new Stack();
		Stack st16 = new Stack();
		Stack st17 = new Stack();
		_game.add(st01);
		_game.add(st02);
		_game.add(st03);
		_game.add(st04);
		_game.add(st05);
		_game.add(st06);
		_game.add(st07);
		_game.add(st08);
		_game.add(st09);
		_game.add(st10);
		_game.add(st11);
		_game.add(st12);
		_game.add(st13);
		_game.add(st14);
		_game.add(st15);
		_game.add(st16);
		_game.add(st17);
		boolean expected = true;
		boolean actual = (_game.contains(st01) && _game.contains(st02) && _game.contains(st03)
				       && _game.contains(st04) && _game.contains(st05) && _game.contains(st06)
				       && _game.contains(st07) && _game.contains(st08) && _game.contains(st09)
				       && _game.contains(st10) && _game.contains(st11) && _game.contains(st12)
				       && _game.contains(st13) && _game.contains(st14) && _game.contains(st15)
				       && _game.contains(st16) && _game.contains(st17) );
		assertTrue("I expected all seventeen Stacks that were added to the GameBoard to be present, but they weren't.", expected == actual);
	}
	
	@Test
	//  driver: Jeremy Fritton
    // navigator: Joe McNamara
	/**
	 * Adds seventeen stacks to the GameBoard
	 * (Seventeen is the number of Stacks to be present in a normal game)
	 * Uses size() to check if there are seventeen objects in the GameBoard
	 */
	public void testAddSeventeenStacksCheckGameBoardUsingSize() {
		Stack st01 = new Stack();
		Stack st02 = new Stack();
		Stack st03 = new Stack();
		Stack st04 = new Stack();
		Stack st05 = new Stack();
		Stack st06 = new Stack();
		Stack st07 = new Stack();
		Stack st08 = new Stack();
		Stack st09 = new Stack();
		Stack st10 = new Stack();
		Stack st11 = new Stack();
		Stack st12 = new Stack();
		Stack st13 = new Stack();
		Stack st14 = new Stack();
		Stack st15 = new Stack();
		Stack st16 = new Stack();
		Stack st17 = new Stack();
		_game.add(st01);
		_game.add(st02);
		_game.add(st03);
		_game.add(st04);
		_game.add(st05);
		_game.add(st06);
		_game.add(st07);
		_game.add(st08);
		_game.add(st09);
		_game.add(st10);
		_game.add(st11);
		_game.add(st12);
		_game.add(st13);
		_game.add(st14);
		_game.add(st15);
		_game.add(st16);
		_game.add(st17);
		int expected = 17;
		int actual = _game.size();
		assertTrue("I expected _game to contain seventeen objects, but it didn't.", expected == actual);
	}
}
