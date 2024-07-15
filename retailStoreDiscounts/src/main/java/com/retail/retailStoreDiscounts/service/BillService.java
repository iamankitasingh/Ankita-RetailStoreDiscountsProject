package com.retail.retailStoreDiscounts.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.retail.retailStoreDiscounts.model.Bill;


public class BillService {

	 private final DiscountService discountService;

	    @Autowired
	    public BillService(DiscountService discountService) {
	        this.discountService = discountService;
	    }

	    public double calculateNetPayableAmount(Bill bill) {
	        double total = calculateTotal(bill);
	        double discount = discountService.calculateDiscount(bill);
	        return total - discount;
	    }

	    private double calculateTotal(Bill bill) {
	        return bill.getProducts().stream().mapToDouble(product -> product.getPrice()).sum();
	    }
	}
