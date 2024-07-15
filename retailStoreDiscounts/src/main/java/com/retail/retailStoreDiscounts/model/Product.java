package com.retail.retailStoreDiscounts.model;

public class Product {

	private String productType;
    private double price;

    public Product(String productType, double price) {
        this.productType = productType;
        this.price = price;
    }

    public String getProductType() {
        return productType;
    }

    public double getPrice() {
        return price;
    }

}
