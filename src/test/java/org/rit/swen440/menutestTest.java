package org.rit.swen440;

import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import org.junit.Test;
import org.rit.swen440.dataLayer.Product;

/**
 * Unit test for menutest Application.
 */
public class menutestTest
{
    //Create a new product for testing
	private Product tester = new Product();

	//Test canOrder with a number that should return true
    @Test
    public void canOrderValidNumber()
    {
        tester.setItemCount(7);
    	assertTrue(tester.canOrder(5));
    }

    //Test canOrder with the same umber as the remaining stock
    @Test
    public void canOrderSameNumber()
    {
        tester.setItemCount(7);
        assertTrue(tester.canOrder(7));
    }

    //Test canOrder with a number larger than the remaining stock
    @Test
    public void canOrderNonValidNumber()
    {
        tester.setItemCount(7);
    	assertFalse(tester.canOrder(9));
    }

    //Test canOrder with a negative number
	@Test
	public void canOrderNegativeNumber()
	{
        tester.setItemCount(7);
		assertFalse(tester.canOrder(-3));
	}

	//Test order with a number smaller than the remaining stock
    @Test
    public void orderItemValidNumber()
    {
        tester.setItemCount(7);
    	tester.order(3);
    	assertEquals(tester.getItemCount(), 4);
    }

    //Test order with the same number as the remaining stock
    @Test
    public void orderItemSameNumber()
    {
        tester.setItemCount(7);
        tester.order(7);
        assertEquals(tester.getItemCount(), 0);
    }

    //Test order with a negative number
	@Test
	public void orderItemNegativeNumber()
	{
        tester.setItemCount(7);
		tester.order(-3);
		assertEquals(tester.getItemCount(), 7);
	}

	//Test order with a number larger than the remaining stock
    @Test
    public void orderItemNonValidNumber()
    {
        tester.setItemCount(7);
    	tester.order(9);
    	assertEquals(tester.getItemCount(), 7);
    }
}
