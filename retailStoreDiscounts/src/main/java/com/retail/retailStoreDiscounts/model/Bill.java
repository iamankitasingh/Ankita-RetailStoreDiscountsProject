package com.retail.retailStoreDiscounts.model;

import java.util.List;

public class Bill {

	 private User user;
	    private List<Product> products;

	    public Bill(User user, List<Product> products) {
	        this.user = user;
	        this.products = products;
	    }

	    public User getUser() {
	        return user;
	    }

	    public List<Product> getProducts() {
	        return products;
	    }

}
