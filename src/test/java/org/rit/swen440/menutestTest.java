package org.rit.swen440;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import org.rit.swen440.dataLayer.Product;

/**
 * Unit test for menutest Application.
 */
public class menutestTest
{
	private Product tester = new Product();
	private BigDecimal price = new BigDecimal("3.99");

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void canOrderValidNumber()
    {
        tester.setItemCount(7);
    	assertTrue(tester.canOrder(5));
    }
    
    @Test
    public void canOrderNonValidNumber()
    {
        tester.setItemCount(7);
    	assertFalse(tester.canOrder(9));
    }

	@Test
	public void canOrderNegativeNumber()
	{
        tester.setItemCount(7);
		assertFalse(tester.canOrder(-3));
	}
    
    @Test
    public void orderItemValidNumber()
    {
        tester.setItemCount(7);
    	tester.order(3);
    	assertEquals(tester.getItemCount(), 4);
    }

	@Test
	public void orderItemNegativeNumber()
	{
        tester.setItemCount(7);
		tester.order(-3);
		assertEquals(tester.getItemCount(), 7);
	}

    @Test
    public void orderItemNonValidNumber()
    {
        tester.setItemCount(7);
    	tester.order(9);
    	assertEquals(tester.getItemCount(), 7);
    }
}
