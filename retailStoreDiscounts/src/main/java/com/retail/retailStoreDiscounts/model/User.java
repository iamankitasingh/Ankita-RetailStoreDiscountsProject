package com.retail.retailStoreDiscounts.model;

public class User {

	private String userType;
    private int customerDuration;

    public User(String userType, int customerDuration) {
        this.userType = userType;
        this.customerDuration = customerDuration;
    }

    public String getUserType() {
        return userType;
    }

    public int getCustomerDuration() {
        return customerDuration;
    }

}
