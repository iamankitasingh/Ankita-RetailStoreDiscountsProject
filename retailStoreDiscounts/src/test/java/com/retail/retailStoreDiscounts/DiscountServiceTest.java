package com.retail.retailStoreDiscounts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.retail.retailStoreDiscounts.model.Bill;
import com.retail.retailStoreDiscounts.model.Product;
import com.retail.retailStoreDiscounts.model.User;
import com.retail.retailStoreDiscounts.service.DiscountService;

public class DiscountServiceTest {

	 private final DiscountService discountService = new DiscountService();

	    @Test
	    public void testCalculateDiscount_employee() {
	        User user = new User("EMPLOYEE", 3);
	        Product p1 = new Product("OTHER", 100);
	        Product p2 = new Product("GROCERY", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        double discount = discountService.calculateDiscount(bill);
	        assertEquals(35, discount);
	    }

	    @Test
	    public void testCalculateDiscount_affiliate() {
	        User user = new User("AFFILIATE", 1);
	        Product p1 = new Product("OTHER", 100);
	        Product p2 = new Product("GROCERY", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        double discount = discountService.calculateDiscount(bill);
	        assertEquals(15, discount);
	    }

	    @Test
	    public void testCalculateDiscount_loyalCustomer() {
	        User user = new User("CUSTOMER", 3);
	        Product p1 = new Product("OTHER", 100);
	        Product p2 = new Product("GROCERY", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        double discount = discountService.calculateDiscount(bill);
	        assertEquals(10, discount);
	    }

	    @Test
	    public void testCalculateDiscount_newCustomer() {
	        User user = new User("CUSTOMER", 1);
	        Product p1 = new Product("OTHER", 100);
	        Product p2 = new Product("GROCERY", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        double discount = discountService.calculateDiscount(bill);
	        assertEquals(5, discount);
	    }

	    @Test
	    public void testCalculateDiscount_allGrocery() {
	        User user = new User("EMPLOYEE", 3);
	        Product p1 = new Product("GROCERY", 100);
	        Product p2 = new Product("GROCERY", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        double discount = discountService.calculateDiscount(bill);
	        assertEquals(15, discount);
	    }

	    @Test
	    public void testCalculateDiscount_noGrocery() {
	        User user = new User("EMPLOYEE", 3);
	        Product p1 = new Product("OTHER", 100);
	        Product p2 = new Product("OTHER", 200);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2));

	        double discount = discountService.calculateDiscount(bill);
	        assertEquals(105, discount);
	    }

	    @Test
	    public void testCalculateDiscount_mixedProducts() {
	        User user = new User("AFFILIATE", 2);
	        Product p1 = new Product("GROCERY", 200);
	        Product p2 = new Product("OTHER", 500);
	        Product p3 = new Product("OTHER", 100);
	        Bill bill = new Bill(user, Arrays.asList(p1, p2, p3));

	        double discount = discountService.calculateDiscount(bill);
	        assertEquals(75, discount);
	    }

}
