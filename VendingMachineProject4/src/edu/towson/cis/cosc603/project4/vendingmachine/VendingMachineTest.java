package edu.towson.cis.cosc603.project4.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
	/** Declaring necessary test objects for {@link VendingMachine} */
	private VendingMachine vendingMachine1,vendingMachine2;
	private VendingMachineItem item;
	private static final String[] code = {"A","B","C","D"};
	private static final double amount[] = {0,1,100000},
								threshold = 0.00001;
	
	/**
	 * Initializes the necessary test objects for the test cases to use.
	 *
	 */
	@Before
	public void setUp() {
		item = new VendingMachineItem(code[0],amount[1]);
		vendingMachine1 = new VendingMachine();
		vendingMachine2 = new VendingMachine();
		vendingMachine2.addItem(item, code[0]);
		vendingMachine2.insertMoney(amount[1]);
	}

	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * Tests postcondition
	 */
	@Test
	public void testAddItem() {
		vendingMachine1.addItem(item, code[0]);
		assertEquals(code[0],vendingMachine1.getItem(code[0]).getName());
		vendingMachine1.addItem(item, code[1]);
		assertEquals(code[0],vendingMachine1.getItem(code[1]).getName());
		vendingMachine1.addItem(item, code[2]);
		assertEquals(code[0],vendingMachine1.getItem(code[2]).getName());
		vendingMachine1.addItem(item, code[3]);
		assertEquals(code[0],vendingMachine1.getItem(code[3]).getName());
	}
	
	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * Tests null item
	 * @throws VendingMachineException
	 */
	@Test(expected = VendingMachineException.class)
	public void testNullAddItem() {
		vendingMachine1.addItem(null, code[0]);
	}

	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * Tests exception for duplicate entry at the same itemslot
	 * @throws VendingMachineException
	 */
	@Test(expected = VendingMachineException.class)
	public void testDuplicateSlotAddItem() {
		vendingMachine1.addItem( item, code[0]);
		vendingMachine1.addItem( item, code[0]);
	}
	
	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * tests name checker
	 * @throws VendingMachineException
	 */
	@Test(expected = VendingMachineException.class)
	public void testWrongNameAddItem() {
		vendingMachine1.addItem(item, "");
	}
	
	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * tests null for the item name
	 * @throws NullPointerException
	 */
	@Test(expected = NullPointerException.class)
	public void testNullNameAddItem() {
		vendingMachine1.addItem(item, null);
	}

	
	/**
	 * Test for the removeItem() method of the {@link VendingMachine} class.
	 * tests postcondition
	 */
	@Test
	public void testRemoveItem() {
		vendingMachine1.addItem( item, code[0]);
		vendingMachine1.removeItem(code[0]);
		assertNull(vendingMachine1.getItem(code[0]));
	}
	
	/**
	 * Test for the removeItem() method of the {@link VendingMachine} class.
	 * tests return
	 */
	@Test
	public void testReturnRemoveItem() {
		vendingMachine1.addItem( item, code[0]);
		vendingMachine1.addItem(vendingMachine1.removeItem(code[0]), code[0]);
		assertEquals(code[0],vendingMachine1.getItem(code[0]).getName());
	}
	
	/**
	 * Test for the removeItem() method of the {@link VendingMachine} class.
	 * tests removing an item that doesn't exist
	 * @throws VendingMachineException
	 */
	@Test(expected = VendingMachineException.class)
	public void testNullRemoveItem() {
		vendingMachine1.removeItem(code[0]);
	}

	/**
	 * Test for the insertMoney() method of the {@link VendingMachine} class.
	 * tests postcondition. 
	 * precondition is covered in the getBalance precondition test 
	 */
	@Test
	public void testPostconditionInsertMoney() {
		vendingMachine1.insertMoney(amount[0]);
		assertEquals(amount[0],vendingMachine1.getBalance(),threshold);
		vendingMachine1.insertMoney(amount[1]);
		assertEquals(amount[1],vendingMachine1.getBalance(),threshold);
		vendingMachine1.insertMoney(amount[2]);
		assertEquals(amount[2]+amount[1],vendingMachine1.getBalance(),threshold);
	}	
	
	/**
	 * Test for the insertMoney() method of the {@link VendingMachine} class.
	 * tests for amount < 0
	 * @throws VendingMachineException
	 */
	@Test(expected = VendingMachineException.class)
	public void testBreakInsertMoney() {
		vendingMachine1.insertMoney(-1);
	}
	
	/**
	 * Test for the getBalance() method of the {@link VendingMachine} class.
	 * tests precondition
	 */
	@Test
	public void testPreconditionGetBalance() {
		assertEquals(0,vendingMachine1.getBalance(),threshold);
	}
	
	/**
	 * Test for the getBalance() method of the {@link VendingMachine} class.
	 * tests postcondition
	 */
	@Test
	public void testPostconditionGetBalance() {
		vendingMachine1.getBalance();
		assertEquals(0,vendingMachine1.getBalance(),threshold);
	}
	/**
	 * Test for the getBalance() method of the {@link VendingMachine} class.
	 * tests for calling insertMoney
	 */
	@Test
	public void testGetBalance() {
		vendingMachine1.getBalance();
		vendingMachine1.insertMoney(amount[2]);
		assertEquals(amount[2],vendingMachine1.getBalance(),threshold);
	}
	
	/**
	 * Test for the makePurchase() method of the {@link VendingMachine} class.
	 * tests postcondition
	 */
	@Test
	public void testPostconditionMakePurchase() {
		vendingMachine2.makePurchase(code[0]);
		assertEquals(amount[0],vendingMachine2.getBalance(),threshold);
	}
	
	/**
	 * Test for the makePurchase() method of the {@link VendingMachine} class.
	 * tests the return value
	 */
	@Test
	public void testReturnMakePurchase() {
		assertFalse(vendingMachine1.makePurchase(code[0]));
		assertTrue(vendingMachine2.makePurchase(code[0]));
		assertFalse(vendingMachine2.makePurchase(code[0]));
		vendingMachine2.addItem(item, code[0]);
		assertFalse(vendingMachine2.makePurchase(code[0]));
	}
	
	/**
	 * Test for the returnChange() method of the {@link VendingMachine} class.
	 * tests postcondition
	 */
	@Test
	public void testReturnChange() {
		assertEquals(amount[0],vendingMachine1.returnChange(),threshold);
		assertEquals(amount[0],vendingMachine1.getBalance(),threshold);
		assertEquals(amount[1],vendingMachine2.returnChange(),threshold);
		assertEquals(amount[0],vendingMachine2.getBalance(),threshold);
	}
	
	/**
	 * Cleans up test objects after a test case is executed.
	 */
	@After
	public void tearDown(){
		item = null;
		vendingMachine1 = null;
		vendingMachine2 = null;
	}


}
