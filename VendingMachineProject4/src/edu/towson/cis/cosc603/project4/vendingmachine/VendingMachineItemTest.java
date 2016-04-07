package edu.towson.cis.cosc603.project4.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class VendingMachineItemTest {
	/** Declaring necessary test objects for {@link VendingMachineItem} */
	private VendingMachineItem item1,item2;
	private final String name = "name";
	private static final double price[] = {0,1,100000}; 
	
	/**
	 * Initializes the necessary test objects for the test cases to use.
	 *
	 */	
	@Before
	public void setUp() {

		item1 = null;
		item2 = new VendingMachineItem(name,price[0]);	
	}
	
	/**
	 * Test for the constructor of the {@link VendingMachineItem} class.
	 * Post conditions are satisfied when testGetName() & testGetPrice() pass.
	 * Range test
	 */
	@Test
	public void testRangeVendingMachineItem() {

		assertNotNull(item1 = new VendingMachineItem(name,price[0]));
		item1=null;
		assertNotNull(item1 = new VendingMachineItem(name,price[1]));
		item1=null;
		assertNotNull(item1 = new VendingMachineItem(name,price[2]));	
		
	}

	/**
	 * Test for the constructor of the {@link VendingMachineItem} class.
	 * tests price bellow zero
	 * @throws VendingMachineException
	 */
	@Test(expected = VendingMachineException.class)
	public void testLessThanZeroVendingMachineItem() {
		
		item1 = new VendingMachineItem(name,-1);	
	}

	/**
	 * Test for the constructor of the {@link VendingMachineItem} class.
	 * tests null name
	 * @throws VendingMachineException
	 */
	@Test(expected = VendingMachineException.class)
	public void testNullNameVendingMachineItem() {
		
		item1 = new VendingMachineItem(null,price[0]);	
	}
	
	/**
	 * Test for the getName() method of the {@link VendingMachineItem} class.
	 * tests postcondition
	 */
	@Test
	public void testGetName() {
		assertEquals(name , item2.getName());
	}
	
	/**
	 * Test for the getName() method of the {@link VendingMachineItem} class.
	 * tests name without declaring it
	 * @throws NullPointerException
	 */
	@Test(expected = NullPointerException.class)
	public void testNullGetName() {
		item1.getName();
	}

	/**
	 * Test for the getPrice() method of the {@link VendingMachineItem} class.
	 * tests postcondition
	 */
	
	@Test
	public void testGetPrice() {
		assertEquals(price[0] , item2.getPrice(),0.000001);
	}


	/**
	 * Test for the getPrice() method of the {@link VendingMachineItem} class.
	 * tests price without declaring it
	 * @throws NullPointerException
	 */
	
	@Test(expected = NullPointerException.class)
	public void testNullGetPrice() {
		item1.getPrice();
	}

	/**
	 * Cleans up test objects after a test case is executed.
	 */
	@After
	public void tearDown(){
		item2 = null;
	}
}
