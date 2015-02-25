package test;

import static org.junit.Assert.assertTrue;
import code.*;

import org.junit.*;

public class testAC {
	private Stack _stack;
	private Token _token1;
	private Token _token2;
	private Token _token3;
	
	@Before
	//  driver: Alexaner Simeonov
    // navigator: Jeremy Fritton
	public void setUp() throws Exception {
		_stack = new Stack();
		_token1 = new Token();
		_token2 = new Token();
		_token3 = new Token();
	}
	
	@After
	//  driver: Alexaner Simeonov
    // navigator: Jeremy Fritton
	public void tearDown() throws Exception {
		_stack = null;
		_token1 = null;
		_token2 = null;
		_token3 = null;
	}
	
	@Test
	//  driver: Alexander Simeonov
    // navigator: Jeremy Fritton
	/**
	 * Adds one token to the Stack
	 * Looks at the Stack and affirms that the Size is 0
	 * Tests the removeToken Method
	 */
	public void testAddOneRemoveOneTokenToStackWithstackSize() {
		_stack.addToken(_token1);
		_stack.removeToken(_token1);
		int expected = 0;
		int actual = _stack.stackSize();
		assertTrue("I expected the stack size to not be 0, but it wasn't.", expected == actual);
	}
	
	@Test
	//  driver: Alexander Simeonov
    // navigator: Jeremy Fritton
	/**
	 * Adds two tokens to the Stack
	 * Looks at the Stack and affirms that the Size is 1
	 * Tests the removeToken Method
	 */
	public void testAddTwoRemoveOneTokenToStackWithstackSize() {
		_stack.addToken(_token1);
		_stack.addToken(_token2);
		_stack.removeToken(_token1);
		int expected = 1;
		int actual = _stack.stackSize();
		assertTrue("I expected the stack size to not be 1, but it wasn't.", expected == actual);
	}
	
	@Test
	//  driver: Alexander Simeonov
    // navigator: Jeremy Fritton
	/**
	 * Adds one token to the Stack
	 * Looks at the Stack and affirms that the Size is 1.
	 */
	public void testAddOneTokenToStackWithstackSize() {
		_stack.addToken(_token1);
		int expected = 1;
		int actual = _stack.stackSize();
		assertTrue("I expected the stack size to not be 1, but it wasn't.", expected == actual);
	}
	
	@Test
	//  driver: Alexander Simeonov
    // navigator: Jeremy Fritton
	/**
	 * Adds two tokens to the Stack
	 * Looks at the Stack and affirms that the Size is 2.
	 */
	public void testAddTwoTokensToStackWithstackSize() {
		_stack.addToken(_token1);
		_stack.addToken(_token2);
		int expected = 2;
		int actual = _stack.stackSize();
		assertTrue("I expected the stack size to not be 2, but it wasn't.", expected == actual);
	}
	
	@Test
	//  driver: Alexander Simeonov
    // navigator: Jeremy Fritton
	/**
	 * Adds three tokens to the Stack
	 * Looks at the Stack and affirms that the Size is 3.
	 */
	public void testAddThreeTokensToStackWithstackSize() {
		_stack.addToken(_token1);
		_stack.addToken(_token2);
		_stack.addToken(_token3);
		int expected = 3;
		int actual = _stack.stackSize();
		assertTrue("I expected the stack size to not be 3, but it wasn't.", expected == actual);
	}
	
	@Test
	//  driver: Alexander Simeonov
    // navigator: Jeremy Fritton
	/**
	 * Adds one token to the Stack
	 * Looks at the Stack and affirms that the same token was added.
	 */
	public void testAddOneTokenToStackWithreturnTokens() {
		_stack.addToken(_token1);
		Token expected = _token1;
		Token actual = _stack.returnTokens();
		assertTrue("I expected to find _token1 but I didn't.", expected == actual);
	}
	
	@Test
	//  driver: Alexander Simeonov
    // navigator: Jeremy Fritton
	/**
	 * Adds one token to the Stack
	 * Looks at the Stack and affirms that it contains the token added.
	 */
	public void testAddOneTokenToStackCheckWithContains() {
		_stack.addToken(_token1);
		boolean expected = true;
		boolean actual = _stack.contains(_token1);
		assertTrue("I expected to find _token1 but I didn't.", expected == actual);
	}
	
	@Test
	//  driver: Alexander Simeonov
    // navigator: Jeremy Fritton
	/**
	 * Adds two tokens to the Stack
	 * Looks at the Stack and affirms that it does not contains the removed token.
	 */
	public void testAddTwoRemoveOneTokenToStackCheckWithContains() {
		_stack.addToken(_token2);
		_stack.removeToken(_token2);
		boolean expected = false;
		boolean actual = _stack.contains(_token2);
		assertTrue("I expected to not find _token2 butI did.", expected == actual);
	}
	
	@Test
	//  driver: Alexander Simeonov
    // navigator: Jeremy Fritton
	/**
	 * Adds two tokens to the Stack
	 * Looks at the Stack and affirms that it contains the two tokens added.
	 */
	public void testAddTwoTokensToStackCheckWithContains() {
		_stack.addToken(_token1);
		_stack.addToken(_token2);
		boolean expected = true;
		boolean actual = _stack.contains(_token1) && _stack.contains(_token2);
		assertTrue("I expected to find _token1 and _token2 but I didn't.", expected == actual);
	}
	
	@Test
	//  driver: Alexander Simeonov
    // navigator: Jeremy Fritton
	/**
	 * Adds three tokens to the Stack
	 * Looks at the Stack and affirms that it contains the two tokens added.
	 */
	public void testAddThreeTokensToStackCheckWithContains() {
		_stack.addToken(_token1);
		_stack.addToken(_token2);
		_stack.addToken(_token3);
		boolean expected = true;
		boolean actual = _stack.contains(_token1) && _stack.contains(_token2) && _stack.contains(_token3);
		assertTrue("I expected to find _token1, _token2, _token3 but I didn't.", expected == actual);
	}
}