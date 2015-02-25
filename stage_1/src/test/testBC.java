package test;

import static org.junit.Assert.assertTrue;
import code.*;

import org.junit.*;

public class testBC {

	private Token _t1;
	private Token _t2;
	private BoardPosition _b;

	@Before
	//  driver: Joe McNamara
    // navigator: Alexander Simeonov
	public void setUp() throws Exception {
		_t1 = new Token();
		_t2 = new Token();
	}
	
	@After
	//  driver: Joe McNamara
    // navigator: Alexander Simeonov
	public void tearDown() throws Exception {
		_t1 = null;
		_t2 = null;
	}
	
	@Test
	//  driver: Joe McNamara
    // navigator: Alexander Simeonov
	/**
	 * This tests one token and its color
	 */
	public void testTokenColorUsinggetColor() {
		_t1.setColor("White");
		String expected = "White";
		String actual = _t1.getColor();
		assertTrue("I expected the token to be White, but it wasn't.", expected.equals(actual));
		
	}
	
	@Test
	//  driver: Joe McNamara
    // navigator: Alexander Simeonov
	/**
	 * This tests two tokens with
	 * two separate colors
	 */
	public void testTokenColorsUsinggetColor() {
		_t1.setColor("White");
		_t2.setColor("Black");
		String a = "White";
		String b = "Black";
	boolean expected = true;
	boolean actual = a.equals(_t1.getColor()) && b.equals(_t2.getColor());
	assertTrue("I expected the two tokens to be black and white, but they weren't.", expected == actual);
	}
	
	@Test
	//  driver: Joe McNamara
    // navigator: Alexander Simeonov
	/**
	 * This tests one token in one position
	 */
	public void testBoardPositionUsinggetPosition() {
		_b = new BoardPosition();
		_b.setPosition(_t1, 1);
		int expected = 1;
		int actual = _b.getPosition(_t1);
		assertTrue("I expected the token to be in position 1, but it wasn't.", expected == actual);
	}
	
	@Test
	//  driver: Joe McNamara
    // navigator: Alexander Simeonov
	/**
	 * This tests two tokens in the same position
	 */
	public void testBoardPositionWithTwoTokensUsinggetPosition() {
		_b = new BoardPosition();
		_b.setPosition(_t1, 1);
		_b.setPosition(_t2, 1);
		boolean expected = true;
		boolean actual = _b.getPosition(_t1) == _b.getPosition(_t1);
		assertTrue("I expected both tokens to be in position 1, but they weren't.", expected == actual);
		
	}
	
	@Test
	//  driver: Joe McNamara
    // navigator: Alexander Simeonov
	/**
	 * This tests two tokens in two different
	 * positions on the GameBoard
	 */
	public void testTwoBoardPositionsUsinggetPositon() {
		_b = new BoardPosition();
		_b.setPosition(_t1, 1);
		_b.setPosition(_t2, 2);
		boolean expected = false;
		boolean actual = _b.getPosition(_t1) == _b.getPosition(_t1);
		assertTrue("I expected both tokens to be in different positions, but they weren't.", expected == actual);
		
	}

	@Test
	//  driver: Joe McNamara
    // navigator: Alexander Simeonov
	/**
	 * This tests the jumping ability of the trampoline from position 1
	 * to position 18(finish)
	 */
	public void testTrampolineUsinggetPosition() {
		_b = new BoardPosition();
		_b.setPosition(_t1, 1);
		_b.trampoline(_t1, _b.getPosition(_t1));
		int expected = 18;
		int actual = _b.getPosition(_t1);
		assertTrue("I expected the token to be in the finised postion, but it wasn't.", expected == actual);
	}
}
