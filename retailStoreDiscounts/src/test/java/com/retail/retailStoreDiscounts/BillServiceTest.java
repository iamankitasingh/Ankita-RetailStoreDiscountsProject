package com.retail.retailStoreDiscounts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.retail.retailStoreDiscounts.model.Bill;
import com.retail.retailStoreDiscounts.model.Product;
import com.retail.retailStoreDiscounts.model.User;
import com.retail.retailStoreDiscounts.service.BillService;
import com.retail.retailStoreDiscounts.service.DiscountService;

public class BillServiceTest {

	 @Mock
	    private DiscountService discountService;

	    @InjectMocks
	    private BillService billService;

	    public BillServiceTest() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testCalculateNetPayableAmount_employee() {
	        User user = new User("EMPLOYEE", 3);
	        Product p1 = new Product("OTHER", 100);
	        Product p2 = new Product("GROCERY", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        when(discountService.calculateDiscount(bill)).thenReturn(35.0);
	        double netPayableAmount = billService.calculateNetPayableAmount(bill);

	        assertEquals(265, netPayableAmount);
	    }

	    @Test
	    public void testCalculateNetPayableAmount_affiliate() {
	        User user = new User("AFFILIATE", 1);
	        Product p1 = new Product("OTHER", 100);
	        Product p2 = new Product("GROCERY", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        when(discountService.calculateDiscount(bill)).thenReturn(15.0);
	        double netPayableAmount = billService.calculateNetPayableAmount(bill);

	        assertEquals(285, netPayableAmount);
	    }

	    @Test
	    public void testCalculateNetPayableAmount_loyalCustomer() {
	        User user = new User("CUSTOMER", 3);
	        Product p1 = new Product("OTHER", 100);
	        Product p2 = new Product("GROCERY", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        when(discountService.calculateDiscount(bill)).thenReturn(10.0);
	        double netPayableAmount = billService.calculateNetPayableAmount(bill);

	        assertEquals(290, netPayableAmount);
	    }

	    @Test
	    public void testCalculateNetPayableAmount_newCustomer() {
	        User user = new User("CUSTOMER", 1);
	        Product p1 = new Product("OTHER", 100);
	        Product p2 = new Product("GROCERY", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        when(discountService.calculateDiscount(bill)).thenReturn(5.0);
	        double netPayableAmount = billService.calculateNetPayableAmount(bill);

	        assertEquals(295, netPayableAmount);
	    }

	    @Test
	    public void testCalculateNetPayableAmount_allGrocery() {
	        User user = new User("EMPLOYEE", 3);
	        Product p1 = new Product("GROCERY", 100);
	        Product p2 = new Product("GROCERY", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        when(discountService.calculateDiscount(bill)).thenReturn(15.0);
	        double netPayableAmount = billService.calculateNetPayableAmount(bill);

	        assertEquals(285, netPayableAmount);
	    }

	    @Test
	    public void testCalculateNetPayableAmount_noGrocery() {
	        User user = new User("EMPLOYEE", 3);
	        Product p1 = new Product("OTHER", 100);
	        Product p2 = new Product("OTHER", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        when(discountService.calculateDiscount(bill)).thenReturn(105.0);
	        double netPayableAmount = billService.calculateNetPayableAmount(bill);

	        assertEquals(195, netPayableAmount);
	    }

	    @Test
	    public void testCalculateNetPayableAmount_mixedProducts() {
	        User user = new User("AFFILIATE", 2);
	        Product p1 = new Product("GROCERY", 200);
	        Product p2 = new Product("OTHER", 500);
	        Product p3 = new Product("OTHER", 100);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2, p3));

	        when(discountService.calculateDiscount(bill)).thenReturn(75.0);
	        double netPayableAmount = billService.calculateNetPayableAmount(bill);

	        assertEquals(725, netPayableAmount);
	    }


}
